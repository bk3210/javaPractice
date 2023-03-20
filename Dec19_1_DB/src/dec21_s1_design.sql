-- 마리오 아울렛
-- 1관, 123번지, 10층짜리, 나이키 매장, 5층, 매장 크기 10평, 에어맥스123, 신발, 100000
-- 2관, 234번지, 8층짜리, 다이소, 7층, 10평, 종이컵, 잡화, 1000
-- 3관, 567번지, 5층짜리, 아디다스, 3층, 5평, 츄리닝세트213, 의류, 70000

-- 주소가 숫자라고 무조건 number주는거 아님
create table dec21_outlet(
	o_name varchar2(4) primary key,
	o_address varchar2(10) not null,
	o_floor number(2) not null
);
drop table dec21_outlet cascade constraint purge;
drop table dec21_store cascade constraint purge;
drop table dec21_product cascade constraint purge;

insert into dec21_outlet values('1관', '132', 10);
insert into dec21_outlet values('2관', '234', 3);
insert into dec21_outlet values('3관','567', 13);

select * from DEC21_OUTLET;

create table dec21_store(
	s_name varchar2(20) primary key,
	s_o_name varchar2(4) not null,
	s_floor number(2) not null,
	s_size number(6, 2) not null
);

insert into dec21_store values('나이키', '1관', 5, 100);
insert into dec21_store values('MLB', '3관', 4, 100);
insert into dec21_store values('H&M', '3관', 2, 250);
insert into dec21_store values('K2', '2관', 1, 150);
insert into dec21_store values('볼빅', '2관', 2, 140);
insert into dec21_store values('아디다스', '2관', 3, 150);
insert into dec21_store values('금강제화', '1관', 2, 100);
insert into dec21_store values('스타벅스', '3관', 1, 120);

insert into dec21_store values('세븐일레븐', '3관', 1, 50);
insert into dec21_store values('미니골드', '3관', 1, 80);
insert into dec21_store values('맥도날드', '1관', 1, 200);

create table dec21_product(
	p_no number(6) primary key,
	p_name varchar2(30) not null,
	p_category varchar2(10) not null,
	p_price number(7) not null,
	p_s_name varchar2(20) not null
);
create sequence dec21_product_seq;

insert into dec21_product values(dec21_product_seq.nextval, '나이키롱패딩', '의류', 500000, '나이키');
insert into dec21_product values(dec21_product_seq.nextval, '여성단화', '신발', 120000, '금강제화');
insert into dec21_product values(dec21_product_seq.nextval, '아이스라떼', '음료', 5000, '스타벅스');
insert into dec21_product values(dec21_product_seq.nextval, '오즈위고', '신발', 100000, '아디다스');
insert into dec21_product values(dec21_product_seq.nextval, '골프채', '잡화', 500000, '볼빅');
insert into dec21_product values(dec21_product_seq.nextval, 'MLB모자', '잡화', 25000, 'MLB');
insert into dec21_product values(dec21_product_seq.nextval, '나이키숏패딩', '의류', 400000, '나이키');
insert into dec21_product values(dec21_product_seq.nextval, 'H&M스웨터', '의류', 50000, 'H&M');
insert into dec21_product values(dec21_product_seq.nextval, 'K2롱패딩', '의류', 450000, 'K2');
insert into dec21_product values(dec21_product_seq.nextval, '아디다스츄리닝', '의류', 70000, '아디다스');
insert into dec21_product values(dec21_product_seq.nextval, '남성단화', '신발', 110000, '금강제화');
insert into dec21_product values(dec21_product_seq.nextval, '에어맥스123', '신발', 120000, '나이키');
insert into dec21_product values(dec21_product_seq.nextval, '에어포스', '신발', 150000, '나이키');
insert into dec21_product values(dec21_product_seq.nextval, '귀걸이', '잡화', 250000, '미니골드');
insert into dec21_product values(dec21_product_seq.nextval, '볼빅골프공', '잡화', 200000, '볼빅');
insert into dec21_product values(dec21_product_seq.nextval, '빅맥', '식품', 5500, '맥도날드');
insert into dec21_product values(dec21_product_seq.nextval, '새콤달콤', '식품', 1500, '세븐일레븐');
insert into dec21_product values(dec21_product_seq.nextval, 'H&M양말', '잡화', 2000, 'H&M');
insert into dec21_product values(dec21_product_seq.nextval, '캔음료', '식품', 1500, '세븐일레븐');
insert into dec21_product values(dec21_product_seq.nextval, 'MLB가방', '잡화', 70000, 'MLB');

select * from dec21_product;
select * from dec21_outlet;
select * from dec21_product;

create table dec21_run(
	r_no number(3) primary key,
	r_s_name varchar2(10) not null,
	r_p_no number(6) not null
);

-- 1관에 가면 살 수 있는 것 품명, 가격
select p_name, p_price from dec21_product
where p_s_name in (select s_name from dec21_store where s_o_name = '1관');

-- 평균가보다 비싼 물건을 어디서 파는지
select o_address from dec21_outlet
where o_name in (select s_o_name from dec21_store
where s_name in (select p_s_name from dec21_product
where p_price > (select avg(p_price) from dec21_product)));

-- 매장명, 층, 상품명, 가격
-- 매장명, 층 : store 테이블
-- 상품명, 가격 : product 테이블
-- 을 한 테이블로 표현?