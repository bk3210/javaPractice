create table dec23_mythic(
	m_card varchar2(20 char) primary key,
	m_name varchar2(10 char) not null,
	m_da number(5) not null,
	m_vo number(5) not null,
	m_pf number(5) not null
);

create table dec23_legendary(
	l_card varchar2(20 char) primary key,
	l_name varchar2(10 char) not null,
	l_da number(5) not null,
	l_vo number(5) not null,
	l_pf number(5) not null
);
insert into dec23_legendary values();
insert into dec23_legendary values();
insert into dec23_legendary values();

create table dec23_epic(
	e_card varchar2(20 char) primary key,
	e_name varchar2(10 char) not null,
	e_da number(5) not null,
	e_vo number(5) not null,
	e_pf number(5) not null
);

create table dec23_basic(
	b_card varchar2(20 char) primary key,
	b_name varchar2(10 char) not null,
	b_da number(5) not null,
	b_vo number(5) not null,
	b_pf number(5) not null
);

create table dec23_user(
	u_id varchar2(10 char) primary key,
	u_lv number(4) not null,
	u_exp number(6) not null
);

--==================================

create table dec23_inventory(
	i_no number(5) primary key,
	i_u_id varchar2(10 char) not null,
	i_u_card varchar2(20 char) not null,
	i_count number(5) not null
	constraint card_in_storage
		foreign key (i_u_id) references dec23_user(u_id)
		on delete cascade
);

create sequence dec23_inven_seq;

select * from dec23_mythic;

insert into DEC23_MYTHIC values('만뢰의 연회장', '미케지마 마다라', 63200, 24053, 23045);
insert into DEC23_MYTHIC values('축제의 리더', '미케지마 마다라', 63200, 24053, 23045);
insert into DEC23_MYTHIC values('마물의 리더', '사쿠마 레이', 63200, 24053, 23045);
insert into DEC23_MYTHIC values('계승 시합', '사쿠마 레이', 63200, 24053, 23045);
insert into DEC23_MYTHIC values('혼돈과 사랑의 선도자', '사쿠마 레이', 63200, 24053, 23045);
insert into DEC23_MYTHIC values('단죄의 사신', '사쿠마 레이', 63200, 24053, 23045);
insert into DEC23_MYTHIC values('지난날의 방랑객', '키류 쿠로', 63200, 24053, 23045);
insert into DEC23_MYTHIC values('욕망의 보석상', '키류 쿠로', 63200, 24053, 23045);
insert into DEC23_MYTHIC values('헤매면서 나아가는 한 걸음', '키류 쿠로', 63200, 24053, 23045);
insert into DEC23_MYTHIC values('주먹에 깃드는 화염', '키류 쿠로', 63200, 24053, 23045);
insert into DEC23_MYTHIC values('녹안의 징계인', '미케지마 마다라', 63200, 24053, 23045);
insert into DEC23_MYTHIC values('혼성의 색채', '미케지마 마다라', 63200, 24053, 23045);
insert into DEC23_MYTHIC values('즐겁게 춤추는 화염', '미케지마 마다라', 63200, 24053, 23045);

insert into DEC23_MYTHIC values('미래의 길안내', '니토 나즈나', 63200, 24053, 23045);

select m_name, m_card from dec23_mythic where m_name like '%니토%';

select * from dec23_mythic order by rownum;
select * from dec23_mythic;
select * from (select rownum as rn, m_card, m_name, m_da, m_vo, m_pf from (select * from dec23_mythic order by m_card, m_name)) where rn >= 1 and rn <= 2;