-- 지점 : 책 = m : n 관계
-- 교보문고, 강남점, 서초구143, 평수, 책이름, 분류, 가격, 출판일, 재고

create table dec22_bookstore(
	bs_name varchar2(20 char) primary key,
	bs_address varchar2(10 char) not null,
	bs_size number(6) not null
);
drop table dec22_bookstore cascade constraint purge;

insert into dec22_bookstore values('교보문고강남점', '서초구143', 200);
insert into dec22_bookstore values('영풍문고강남점', '테헤란로143', 300);
insert into dec22_bookstore values('알라딘용산점', '용산구143', 150);

create table dec22_book(
	b_no number(4) primary key,
	b_name varchar2(20 char) not null,
	b_price number(6) not null, 
	b_category varchar2(15 char) not null,
	b_pd date not null
);
create sequence dec22_book_seq;
select * from dec22_book;

insert into dec22_book values(1, '이것이자바다', 30000, 'IT', to_date('20210101', 'YYYYMMDD'));
insert into dec22_book values(2, '저것이자바다', 24000, '교양', to_date('20200601', 'YYYYMMDD'));
insert into dec22_book values(3, '요것이자바다', 3000, '학술', to_date('20210920', 'YYYYMMDD'));
insert into dec22_book values(4, '그것이자바다', 75000, '수필', to_date('19981201', 'YYYYMMDD'));

create table dec22_order(
	o_no number(4) primary key,
	o_b_no number(4) not null,
	o_bs_name varchar2(20 char) not null,
	o_stock number(4) not null,
	constraint bs_c
		foreign key (o_bs_name) references dec22_bookstore(bs_name)
		on delete cascade,
	constraint book_c
		foreign key (o_b_no) references dec22_book(b_no)
		on delete cascade
);
create sequence dec22_order_seq;

insert into dec22_order values(dec22_order_seq.nextval, 4, '교보문고강남점', 3);
insert into dec22_order values(dec22_order_seq.nextval, 1, '영풍문고강남점', 5);
insert into dec22_order values(dec22_order_seq.nextval, 2, '알라딘용산점', 6);
insert into dec22_order values(dec22_order_seq.nextval, 3, '교보문고강남점', 2);

select * from dec22_order;

-- 크기가 5평 이상인 지점 지점별, 카테고리별 평균가
-- 평균가가 1000원 미만인 건 제외
select o_bs_name, b_category, avg(b_price)
	from dec22_order, dec22_book
	where o_b_no = b_no and o_bs_name in (select bs_name from dec22_bookstore where bs_size >=5)
	group by o_bs_name, b_category
	having avg(b_price) > 1000
order by o_bs_name, b_category;

select * from DEC22_BOOKSTORE;

select * from dec22_book
where b_no in (select o_b_no from dec22_order);

SELECT * FROM DEC22_BOOK WHERE B_NO IN (SELECT O_B_NO FROM DEC22_ORDER WHERE O_BS_NAME IN (SELECT BS_NAME FROM DEC22_BOOKSTORE));
SELECT B_NO, B_NAME, B_PRICE, B_CATEGORY, B_PD FROM DEC22_BOOK WHERE B_NO IN (SELECT O_B_NO FROM DEC22_ORDER WHERE O_BS_NAME IN (SELECT BS_NAME FROM DEC22_BOOKSTORE WHERE BS_NAME LIKE '%강남%'));
SELECT B_NO, B_NAME, B_PRICE, B_CATEGORY, B_PD FROM DEC22_BOOK WHERE B_NO IN (SELECT O_B_NO FROM DEC22_ORDER WHERE O_BS_NAME IN (SELECT BS_NAME FROM DEC22_BOOKSTORE WHERE BS_NAME LIKE '%강남%'));

SELECT B_CATEGORY, avg(b_price)
FROM DEC22_BOOK
WHERE B_NO IN 
(SELECT O_B_NO FROM DEC22_ORDER 
WHERE O_BS_NAME IN 
(SELECT BS_NAME FROM DEC22_BOOKSTORE
WHERE BS_NAME LIKE '%강남%'))
group by b_category
order by b_category;