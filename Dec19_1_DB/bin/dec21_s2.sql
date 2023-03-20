-- 매장명, 층, 품명, 가격
select s_name, s_floor, p_name, p_price
from dec21_store, dec21_product
where s_name = p_s_name;

--만약에 테이블들 필드명이 겹치면 테이블명.필드명으로 구분하기(ex. dec21_product.p_name)
-- 빌딩명, 위치, 매장명, 층
select o_name, o_address, s_name, s_floor
from dec21_outlet, dec21_store
where s_o_name = o_name;

-- 빌딩명, 매장명, 층, 품명, 가격
select o_name, s_name, s_floor, p_name, p_price
from dec21_outlet, dec21_store, dec21_product
where s_o_name = o_name and s_name = p_s_name;

-- !!! join은 용량문제 발생하므로 신중하게 써야 함, 가급적 subquery로..... !!!
-- 132번지에 가면 살 수 있는 것 품명, 가격
select p_name, p_price
from dec21_outlet, dec21_store, DEC21_PRODUCT
where o_name = s_o_name and s_name = p_s_name and s_o_name = '1관';

-- 무슨관, 층, 상품, 카테고리, 가격을 건물명 순으로 정렬하기
-- 만약 같은 건물이면 가격 내림차순으로
select s_o_name, s_floor, p_name, p_category, p_price
from dec21_store, dec21_product
where s_name = p_s_name
order by s_o_name, p_price desc;

-- 평균가 이상의 상품
-- 무슨관, 위치, 매장명, 몇층에
-- 상품명, 카테고리, 가격
-- 상품명이 같으면 가격 내림차순으로
select o_name, o_address, s_name, s_floor, p_name, p_category, p_price
from dec21_store, dec21_outlet, dec21_product
where s_o_name = o_name and s_name = p_s_name and p_price >= (select avg(p_price) from dec21_product)
order by p_name, p_price desc;

-- 상품테이블 전부 다(가져오려면 너무 많다!!)
-- 		1페이지당 5개씩 데이터를 가져오는 걸로
-- 		데이터 번호를 1번부터 센다 치고 클라이언트가 3페이지를 보고싶어할 때,
-- sequence는 행번호 기능을 하지 않음(그냥 자동으로 번호 들어가는 것 뿐)
-- 6~10번까지 정보를 가져오기 : sequence 처리된 p_no 말고, 진짜 행번호
-- 매장명 -> 상품명

-- rownum
--				 select할 때마다 자동으로 부여되는 가상필드. 행번호
--				 단, *과는 함께 쓸 수 없다(다른 필드를 모두 넣어줘야 함)
-- 			 order by보다 먼저 발동됨
--				 항상 1번부터 조회해야 결과를 보여줌(11~15는 불가능)
--				 1부터 차례로 카운트한다(0 아님)
select *
from (
	select rownum as rn, p_no, p_name, p_category, p_price, p_s_name
	from (
		select *
		from dec21_product
		order by p_s_name, p_name
	)
	where rn >= 11 and rn <=15
);
-- 위 식은 rownum이 총 세번 생성됨(select절을 세 번 썼으니까)
-- rn은 최종 query문 입장에서는 rownum이 아니다
-- subquery는 반드시 where절에만 들어가야 하는 건 아니다

-- 제일 높은 건물에서 살 수 있는 상품
-- 건물명, 위치, 매장명, 층, 상품명, 가격
-- 건물명 -> 가격 내림차순 -> 상품명순
-- 3~5번까지 결과 도출
select *
from (
	select rownum as rn, o_name, o_address, s_name, s_floor, p_name, p_price
	from (			
			select o_name, o_address, s_name, s_floor, p_name, p_price
			from dec21_outlet, dec21_product, dec21_store
			where o_name = s_o_name and s_name = p_s_name and o_floor = (select max(o_floor) from dec21_outlet)
			order by o_name, p_price desc, p_name
		)
	)
where rn > 2 and rn <6;

--=================================================
-- 평균가
select avg(p_price)
from dec21_product;

-- group by
-- 카테고리별 평균가(그룹을 지어서 평균 내기)
select p_category, avg(p_price)
from dec21_product
group by p_category;

-- 매장별, 카테고리별 상품 갯수
select p_s_name, p_category, count(*)
from dec21_product
group by p_s_name, p_category
order by p_s_name, p_category;

-- 건물별, 카테고리별, 최저가
select s_o_name, p_category, min(p_price)
from dec21_store, dec21_product
where s_name = p_s_name
group by s_o_name, p_category
order by s_o_name, p_category;

-- 주소별, 카테고리별, 평균가
-- 가격이 10000원 이상인 것만 평균가에 포함
select o_address, p_category, avg(p_price)
from dec21_store, dec21_product, dec21_outlet
where o_name = s_o_name and s_name = p_s_name and p_price >= 10000
group by o_address, p_category
order by o_address, p_category;

-- 주소별, 카테고리별로 산출한 평균가
-- 평균가가 10000원 이상인 것만
select o_address, p_category, avg(p_price)
from dec21_store, dec21_product, dec21_outlet
where o_name = s_o_name and s_name = p_s_name
group by o_address, p_category
having avg(p_price) >= 10000
order by o_address, p_category;

-- 1관, 2관에서 살 수 있는 상품
-- 매장별, 카테고리별로 산출한 최고가
-- 400000원 미만만
select s_name, p_category, max(p_price)
from dec21_store, dec21_product
where s_name = p_s_name and (s_o_name = '1관' or s_o_name = '2관')
group by s_name, p_category
having max(p_price) < 400000
order by s_name, p_category;
-- 괄호 안넣어주면 논리연산자결과가 이상해진다(K2에서 식품 판매...)