-- 여러 줄 실행 : 블록지정, alt + x
create table dec19_product2(
	s_name varchar2(1),
	s_price number
);

-- 한 줄 실행 : alt + s
select * from dec19_product;

-- eclipse에서는 DBA쪽 기능이 불가능함(계정 생성, 권한부여 등은 sqlplus에서 해야만 함)
-- SQL/sqlplus 명령어가 서로 다른데 해당 프로그램에서만 사용 가능함
-- 대소문자 구별이 없음 -> 모두 대문자로 인식
---------------------------------------------------------------------------------------------------
-- CRUD(Create Read Update Delete)
-- 테이블 만들기
create table dec19_student(
	s_name varchar2(5 char),
	s_age number(3),
	s_kor number(3),
	s_eng number(3),
	s_mat number(3)
);

-- 데이터 삽입
insert into table dec19_student values('홍길동', 20, 100, 100, 100);
	
insert into table dec19_student values('김길동', 25, 30, 50, 30);

-- 결과 도출
select * from dec19_student;

-- 평균 구하기
select avg(s_kor + s_eng + s_mat) / 3
from dec19_student;

-- 테이블 삭제(casecade~는 휴지통에 임시저장x 바로 삭제)
drop table dec19_product2 cascade constraint purge;
drop table dec19_student;
drop table dec19_product cascade constraint purge;

create table dec19_student(
	s_name varchar2(5 char),
	s_age number(3),
	s_kor number(3),
	s_eng number(3),
	s_mat number(3)
);

insert into dec19_student values('홍길동', 20, 100, 100, 100);
insert into dec19_student values('김길동', 25, 85, 90, 25);
select * from dec19_student;
select avg(s_kor + s_eng + s_mat) / 3 from dec19_student;