-- 웹사이트
-- id : hong, pw : 123, 이름 : 홍길동
-- 게시판에 ㅋㅋㅋ라는 글을 지금 남김

-- 회원 : 글 = 1 : n 관계

create table dec21_user(
	u_id varchar2(15 char) primary key,
	u_pw varchar2(15 char) not null,
	u_name varchar2(15 char) not null
);

-- constraint : 제약조건명
create table dec21_board(
	b_no number(10) primary key,
	b_title varchar2(10 char) not null,
	b_date date not null,
	b_u_id varchar2(15 char) not null,
	constraint board_writer
		foreign key (b_u_id) references dec21_user(u_id)
		on delete cascade
);
create sequence dec21_board_no_seq;
select * from dec21_board;

drop table dec21_board cascade constraint purge;
drop sequence dec21_board_no_seq;
insert into dec21_user values('hong', '123', '홍길동');
insert into dec21_board values(dec21_board_no_seq.nextval, 'ㅋㅋㅋ', sysdate, 'hong');

-- hong이 탈퇴함
-- 			탈퇴하면 hong이 쓴 글, 댓글, 대댓글도 지워지는 걸로 결정 -> 할 일이 너무 많다
--				탈퇴하면 작성글이 자동으로 지워지게 + 

-- Foreing key : 외래키(FK)

delete from dec21_member where m_id = 'hong'