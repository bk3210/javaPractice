-- RDBMS(Relational DB Management System) : 테이블간의 관계를 중심으로 데이터를 전개해나가는 시스템

-- 요식업 프렌차이즈 관리
-- 홍길동, 1999-01-01, 수원, 김밥천국, 강남점, 10테이블,
-- 									야채김밥, 식사류, 4000원
--										제육덮밥, 식사류, 7000원
-- 김길동, 2000-12-12, 안양, 우동천국, 구로, 8테이블,
--										어묵우동, 식사류, 5000원
--										소주, 주류, 4000원

-- 식당 : 메뉴 = 1 : n
-- 사장 : 식당 = m : n
-- 사장 : 메뉴 = 관계x
-- 사장, 가게, 메뉴 테이블에는 pk 먹일만한 속성이 없어서 no 지정
create table dec20_manager(
	m_no number(6) primary key,
	m_name varchar2(5 char) not null,
	m_birthday date not null,
	m_address varchar2(15 char) not null
);
create sequence dec20_seq;
insert into dec20_manager values(dec20_seq.nextval, '홍길동', to_date('19930203', 'YYYYMMDD'), '구로');
insert into dec20_manager values(dec20_seq.nextval, '김길동', to_date('19971003', 'YYYYMMDD'), '수원');
insert into dec20_manager values(dec20_seq.nextval, '박길동', to_date('20000515', 'YYYYMMDD'), '강남');
insert into dec20_manager values(dec20_seq.nextval, '최길동', to_date('19871216', 'YYYYMMDD'), '노원');

select * from DEC20_MANAGER;

create table dec20_restaurant(
	r_no number(6) primary key,
	r_name varchar2(15 char) not null,
	r_name varchar2(15 char) not null,
	r_address varchar2(15 char) not null,
	r_table_count number(3) not null
);

insert into dec20_restaurant values(dec20_seq.nextval, '김밥천국', '인천', 10);
insert into dec20_restaurant values(dec20_seq.nextval, '우동천국', '구로', 10);
insert into dec20_restaurant values(dec20_seq.nextval, '카페천국', '군자', 10);
insert into dec20_restaurant values(dec20_seq.nextval, '어디천국', '역삼', 10);

select * from DEC20_RESTAURANT;
drop table dec20_menu cascade constraint purge;
create table dec20_menu(
	m_no number(6) primary key,
	m_name varchar2(15 char) not null,
	m_category varchar2(15 char) not null,
	m_price number(6) not null,
	m_r_no number(6) not null
);

insert into dec20_menu values(dec20_seq.nextval, '라떼', '음료', 4500, 3);
insert into dec20_menu values(dec20_seq.nextval, '튀김우동', '식사', 7000, 2);
insert into dec20_menu values(dec20_seq.nextval, '아메리카노', '음료', 4000, 3);
insert into dec20_menu values(dec20_seq.nextval, '야채김밥', '식사', 4000, 3);
insert into dec20_menu values(dec20_seq.nextval, '삼각김밥', '식사', 1800, 1);
insert into dec20_menu values(dec20_seq.nextval, '치즈김밥', '식사', 4500, 2);
insert into dec20_menu values(dec20_seq.nextval, '딸기라떼', '음료', 5000, 4);
insert into dec20_menu values(dec20_seq.nextval, '수육', '식사', 15000, 4);
insert into dec20_menu values(dec20_seq.nextval, '백반', '식사', 9000, 4);

create sequence dec20_run_seq;
create table dec20_run(
	r_no number(6) primary key,
	r_m_no number(6) not null,
	r_r_no number(6) not null
);

insert into dec20_run values(dec20_run_seq.nextval, 1, 1);
insert into dec20_run values(dec20_run_seq.nextval, 2, 2);
insert into dec20_run values(dec20_run_seq.nextval, 3, 3);
insert into dec20_run values(dec20_run_seq.nextval, 4, 1);
insert into dec20_run values(dec20_run_seq.nextval, 4, 4);

select * from DEC20_MANAGER;
select * from DEC20_restaurant;
select * from DEC20_Menu;
select * from dec20_run;

-- 전체 메뉴명, 가격
select m_name, m_price from dec20_menu;

-- 메뉴 평균가
-- 소수점 자리수 : Java에서 처리하는 것이 바람직
select avg(m_price) from dec20_menu;

-- 가장 비싼 메뉴명
select m_name from dec20_menu
where m_price = (select max(m_price) from dec20_menu);

-- 강남에 있는 매장명, 테이블 수
select r_name, r_table_count from DEC20_RESTAURANT
where r_address = '구로';

-- 최연장자 사장님 이름, 집
select m_name, m_address from DEC20_MANAGER
where m_birthday = (select min(m_birthday) from dec20_manager);

-- 가게는 총 몇군데
select count(*) from DEC20_RESTAURANT;

-- 밥 시리즈 평균가
select avg(m_price) from dec20_menu
where m_name like '%밥%';

-- 구로에 가면 무엇을 먹을 수 있나
select m_name from dec20_menu
where m_r_no = (
	select r_no from dec20_restaurant
	where r_address = '구로'
);

-- subquery의 결과는 하나만 나올 수 있음
-- 제일 비싼 메뉴는 어디 가면 먹을 수 있나
select r_address from dec20_restaurant
where r_no = (select m_r_no from dec20_menu
							where m_price = (select max(m_price) from dec20_menu));


-- 홍길동씨네 가게 메뉴명, 가격
-- 동명이인이 있을 수 있으니/한 사람이 여러 가게를 운영중일 수 있으니 in
select m_name, m_price from dec20_menu
where m_r_no in (select r_r_no from dec20_run
								where r_m_no in (select m_no from dec20_manager
																where m_name = '홍길동'));

-- 최연장자 사장님네 메뉴 평균가
-- 생일이 같은 사장님이 있을 수 있으니/한 사람이 여러 가게를 운영중일 수 있으니 in
select avg(m_price) from DEC20_MENU
where m_r_no in (select r_r_no from DEC20_RUN
								where r_m_no in (select m_no from dec20_manager
															 where m_birthday = (select min(m_birthday) from DEC20_MANAGER)));
															 
