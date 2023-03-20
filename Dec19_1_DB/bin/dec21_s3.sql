-- CRUD
-- Update
-- 나이키가 1층으로 이전됨
update DEC21_STORE
set s_floor = 1
where s_name = '나이키';


-- 다이소 있는 건물이 345번지로 이전
update dec21_outlet
set o_address = '345'
where o_name = (select s_o_name from dec21_store where s_name = '아디다스');

select * from DEC21_STORE;

-- 345번지에 있는 가게들 물건 반값
update dec21_product
set p_price = p_price * 0.5
where p_s_name in (select s_name from dec21_store where s_o_name = (select o_name from dec21_outlet where o_address = '345'))

select * from dec21_product
where p_s_name in (select s_name from dec21_store where s_o_name = (select o_name from dec21_outlet where o_address = '345'))


-- Delete

-- 김밥천국 인천점 폐업
-- delete할 때 어느 테이블의 어느 row를 삭제할지 미리 고려해야 한다
delete from dec20_restaurant
where r_name = '김밥천국' and r_address = '인천';

