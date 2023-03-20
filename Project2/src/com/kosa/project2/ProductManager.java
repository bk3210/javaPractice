package com.kosa.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManager {
	Scanner scanner = new Scanner(System.in);
	List<ProductDTO> list = new ArrayList<ProductDTO>();
	
	public ProductManager() {
		list.add(new ProductDTO("1", "노트북", 1200000,  "2020-09-09", "삼성전자"));
		list.add(new ProductDTO("2", "갤럭시 S", 7500000,  "2022-07-08",   "삼성전자"));
		list.add(new ProductDTO("3", "노트북", 1200000,  "2020-09-09", "삼성전자"));
	}
	
	public void menu() {
		int a=0;
		while(true) {
			System.out.println("메뉴 \n1. 출력 \n2. 추가 \n3. 수정 \n4. 삭제 \n0. 종료");
			if(a==1) {
				print();
			}else if(a==2) {
				insert();
			}else if(a==3) {
				update();
			}else if(a==4) {
				delete();
			}else {
				break;
			}
		}
	}
	
	public void print() {
		for(ProductDTO dto : list) {
		System.out.println(dto);
		}
	}
	
	public void insert() {
		ProductDTO dto = new ProductDTO();
		System.out.println("제품관리번호");
		dto.setPrdNum(scanner.next());
		System.out.println("제품명");
		dto.setPrdName(scanner.next());
		System.out.println("제품가격");
		dto.setPrdPrice(scanner.nextInt());
		System.out.println("제품제조일");
		dto.setPrdDate(scanner.next());
		System.out.println("제품제조사");
		dto.setPrdMaker(scanner.next());
		list.add(dto);
	}
	
	public void update() {
		String a="";
		ProductDTO dto = null;
		System.out.println("수정할 번호 : ");
		a = scanner.next();
		for(ProductDTO productDTO : list) {
			if(a.equals(scanner.next())) {
				dto=productDTO;
				break;
			}
		}
		if(dto==null) {
			System.out.println("존재하지 않는 번호입니다.");
			return;
		}else {
			System.out.println("제품관리번호");
			dto.setPrdNum(scanner.next());
			System.out.println("제품명");
			dto.setPrdName(scanner.next());
			System.out.println("제품가격");
			dto.setPrdPrice(scanner.nextInt());
			System.out.println("제품제조일");
			dto.setPrdDate(scanner.next());
			System.out.println("제품제조사");
			dto.setPrdMaker(scanner.next());
			list.add(dto);
		}
	}
	
	public void delete() {
		String a="";
		System.out.println("삭제할 번호를 입력하세요.");
		a=scanner.next();
		for(ProductDTO productDTO : list) {
			if(a.equals(productDTO.getPrdNum())) {
				System.out.printf("%d번을 삭제하셨습니다.", a);
				break;
			}else {
				System.out.println("해당 번호가 존재하지 않습니다.");
			}
		}
	}
}
