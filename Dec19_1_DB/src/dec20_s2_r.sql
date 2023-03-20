-- 데이터 조회 R

-- snack 테이블 전체 조회
select * from dec20_snack;

-- 이름, 가격만 조회
--			1. 일단 전체 가져와서, Java/Python에서 조건문 + 반복문으로 구하기 - DB용량 규모가 어마어마해서 현실적으로 불가능
--			2. 필요한 것만 분석을 통해 가져와서, Java/Python으로 약간의 처리를 함 - O
-- select 필드명, 필드명, ... from 테이블명;
select s_name, s_price from dec20_snack;

-- 이름, 유통기한 조회
-- as xx를 붙이면 필드명 대신 닉네임이
select s_name as 상품명, s_date as date_date from dec20_snack;

-- 이름, 가격, 부가세(가격의 10%) - 연산자 사용가능
select s_name, s_price, (s_price * 0.1) as vat from dec20_snack;

-- 이름, 가격, g당 가격
select s_name, s_price, (s_price / s_weight) from dec20_snack;

-- 오라클에는 통계함수 존재
-- sum(), avg(), max(), min(), count()
-- 평균가
select avg(s_price) from dec20_snack;

-- 총 과자 몇 종류, g당 가격의 평균
select count(*), avg(s_price / s_weight) from dec20_snack;

-- 조건식 : where;
-- sql문에서는 같다가 =임
-- 3000원 이하인 과자의 이름, 유통기한, 가격
select s_name, s_date, s_price from dec20_snack
where s_price <= 3000;

-- 새우깡 가격
select s_price from dec20_snack
where s_name = '새우깡';

-- 1000원 미만 몇종류
select count(*) from dec20_snack
where s_price < 1000;

-- 문자열 포함 검색 : 필드명 like 패턴; 
-- '초코' 시리즈 이름, 가격
select s_name, s_price from dec20_snack
where s_name like '%초코%';

-- 내일 먹으면 죽는 과자
-- 이렇게 치면 날짜 같고 아직 시간은 이른 데이터의 결과가 걸러지지 않음
select s_name, s_date, s_price from dec20_snack
-- where s_date = to_date(20221220, 'YYMMDD');
where s_date <= sysdate;

insert into DEC20_SNACK values(dec20_snack_seq.nextval, '후레쉬파이', to_date('20221220 20:00', 'YYMMDD HH24:MI') , 500, 4500);

-- sysdate : 현재시간 + 날짜
-- to_date('문자열', '패턴') : 문자열 -> 날짜
-- to_char('날짜', '패턴') : 날짜 -> 문자열
-- concat('문자열', '문자열') : 문자열끼리 서로 붙여줌
select s_name, s_date, s_price from dec20_snack
where s_date <= to_date(concat(to_char(sysdate, 'YYYYMMDD'), '235959'), 'YYYYMMDDHH24MISS');

-- '깡' 시리즈거나, 가격이 2000원 이상인 것
-- 이름, 가격
select s_name, s_price from dec20_snack
where s_name like '%깡%' or s_price >=2000;

-- 1000 < 가격 < 3000인 것
-- 이름, 유통기한, 가격
select s_name, s_date, s_price from dec20_snack
where s_price > 1000 and s_price < 3000;

-- 유통기한이 오늘 아침 9시 ~ 오늘 저녁 9시 범위 내인 것
-- 이름, 유통기한, 가격
select s_name, s_date, s_price from dec20_snack
where s_date < to_date(concat(to_char(sysdate, 'YYYYMMDD'), '210000'), 'YYYYMMDDHH24MISS')
and s_date > to_date(concat(to_char(sysdate, 'YYYYMMDD'), '090000'), 'YYYYMMDDHH24MISS');

-- 통계함수는 where절에 사용할 수 없다 - subquery
-- 최고가
-- 이름, 가격
select s_name, s_price from dec20_snack
where s_price = (select max(s_price) from dec20_snack);

-- 유통기한 제일 길게 남은 것 이름, 유통기한, 가격
select s_name, s_date, s_price from dec20_snack
where to_char(s_date, 'YYYYMMDD') = (select max(to_char(s_date, 'YYYYMMDD')) from dec20_snack);

-- 평균보다 싼 것 이름, 가격
select s_name, s_price from dec20_snack
where s_price < (select avg(s_price) from dec20_snack);