-- 커피 테이블
--			메뉴명				가격
--			아아					4000
--			라떼					4500

create table dec20_cafe(
	c_name varchar2(20 char),
	c_price number(5)
);

insert into DEC20_CAFE(c_name, c_price) values('아이스아메리카노', 4000);
insert into DEC20_CAFE(c_name, c_price) values('라떼', 4500);
insert into DEC20_CAFE(c_price, c_name) values(5000, '딸기스무디');		-- 필드 순서 꼭 입력된 그대로 안 쳐도 됨

insert into DEC20_cafe(c_name) values('고구마라떼');			-- 꼭 모든 필드값을 다 채워야 하는 건 아님( c_price는 null)


-- 데이터 조회 : R
select * from dec20_cafe;
--=================================================

drop table dec20_cafe cascade constraint purge;

--=================================================
-- 테이블 생성 옵션
--					not nll : insert시 반드시 데이터값을 넣어야 한다는 뜻(null 넣으면 연산오류뜸)
--					primary key : 고유키, not null + 중복 데이터를 넣을 수 없도록 하는 것
--											그 데이터의 대표값이 되어버림
--					autoincrement : 숫자를 자동으로 증가시키는 옵션(OracleDB에는 없음!!!)

--=================================================

create table dec20_cafe(
	c_name varchar2(10 char) primary key,
	c_price number(5) not null
);

insert into dec20_cafe values('아이스아메리카노', 3000);
insert into dec20_cafe values('라떼', 4000);
insert into dec20_cafe values('딸기스무디', 5000);
insert into dec20_cafe values('아인슈페너', 5000);
insert into dec20_cafe values('핫초코', 4500);

-- 아아는 얼마인가요? primary key로 접근
-- 4500원짜리는 어떤 건가요?

--=================================================



-- 테이블 수정은 어떻게? 신경 안 써도 된다(실전에는 없을 작업)
insert into dec20_student values(1, '홍길동', 100, 90, 80);
insert into dec20_student values(2, '김길동', 80, 60, 72);
insert into dec20_student values(3, '홍길동', 60, 20, 90);
insert into dec20_student values(4, '김길동', 98, 67, 75);
insert into dec20_student values(5, '박길동', 45, 95, 100);
insert into dec20_student values(6, '최길동', 88, 70, 85);

select * from dec20_student;

-- 학생 번호 : 실질적으로 유효한 정식 데이터는 아님(PK가 필요해서 억지로 만든 것)
-- 사람이 신경쓰기 애매 -> 기계가 자동으로 counting number 해주면 안되나...(오라클에는 autoincrement가 없으니까)

-- sequence : 테이블과는 무관한 독립적인 존재지만 용도상 테이블과 함께 씀
-- -> sequence 생성 : create sequence 시퀀스명;
--  					insert에 실패해도 다음 count는 계속 올라가는 특징이 있음, 앞 데이터가 삭제된다고 다음 데이터들 숫자가 이동되는 것도 아님
-- 					즉, 행번호의 개념이 아님
--						같은 sequence를 여러 table이 같이 쓸 수 있음
create sequence dec20_student_seq;

drop table dec20_student cascade constraint purge;
create table dec20_student(
	s_no number(4) primary key,
	s_name varchar2(10 char) not null,
	s_kor number(3) not null,
	s_eng number(3) not null,
	s_mat number(3) not null
);

-- 시퀀스 사용: 해당위치에 시퀀스명.nextval
insert into dec20_student values(dec20_student_seq.nextval, '홍길동', 100, 90, 80);
insert into dec20_student values(dec20_student_seq.nextval, '김길동', 80, 60, 72);
insert into dec20_student values(dec20_student_seq.nextval, '홍길동', 60, 20, 90);
insert into dec20_student values(dec20_student_seq.nextval, '김길동', 98, 67, 75);
insert into dec20_student values(dec20_student_seq.nextval, '박길동', 45, 95, 100);
insert into dec20_student values(dec20_student_seq.nextval, '최길동', 88, 70, 85);

select * from dec20_student;

--=================================================
create table dec20_snack(
	s_name varchar2(15 char) primary key,
	s_date date not null,
	s_weight number(3) not null,
	s_price number(4) not null
);

insert into dec20_snack values('새우깡', to_char(20230101), 100, 3000);

select * from dec20_snack;

--=================================================
drop table dec20_snack cascade constraint purge;
create table dec20_snack(
	s_no number(3) primary key,
	s_name varchar2(15 char) not null,
	s_date date not null,
	s_weight number(3) not null,
	s_price number(5) not null
);
create sequence dec20_snack_seq;

insert into dec20_snack values(dec20_snack_seq.nextval, '새우깡', sysdate, 100, 3000);
insert into dec20_snack values(dec20_snack_seq.nextval, '감자깡', to_date('20230101', 'YYMMDD') , 150, 1500);
insert into dec20_snack values(dec20_snack_seq.nextval, '옥수수깡', to_date('20230301', 'YYMMDD') , 300, 2000);
insert into dec20_snack values(dec20_snack_seq.nextval, '스윙칩', to_date('20230501', 'YYMMDD') , 150, 3000);
insert into dec20_snack values(dec20_snack_seq.nextval, '초코파이', to_date('20230701', 'YYMMDD') , 300, 7000);
insert into dec20_snack values(dec20_snack_seq.nextval, '칙촉', to_date('20230701 15:00', 'YYMMDD HH24:MI') , 300, 5000);
insert into dec20_snack values(dec20_snack_seq.nextval, '새콤달콤', to_date('20230901 15:00', 'YYMMDD HH24:MI') , 80, 1000);

-- 날짜 처리
--					sysdate : DB서버의 현재시간날짜 입력
--					to_date(' ', '패턴') : 문자열 -> 날짜로 바꿔주는 함수(특정날짜를 입력할 때)
--								YYYY, MM, DD, HH, HH24, MI, SS

select * from dec20_snack;