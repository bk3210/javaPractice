package com.kosa.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManager {
	private Scanner scanner = new Scanner(System.in);
	List<ProductDto> list = new ArrayList<ProductDto>();
	
	public ProductManager() {
		list.add(new ProductDto("1", "노트북", 1200000, "2020-09-09", "삼성전자"));
		list.add(new ProductDto("2", "갤럭시S", 7500000, "2020-07-08", "삼성전자"));
		list.add(new ProductDto("3", "노트북", 1200000, "2022-02-07", "엘지전자"));
		list.add(new ProductDto("4", "파우치", 30000, "2023-01-01", "무신사"));
	}

	public void start() {
		int a=0;
		while (true) {
			System.out.println("메뉴 \n1. 출력	\n2. 추가\n3. 수정	\n4. 삭제\n0. 종료");
			a = scanner.nextInt();
			if (a == 1) {
				view();
			} else if (a == 2) {
				insert();
			} else if (a == 3) {
				update();
			} else if (a == 4) {
				delete();
			} else {
				break;
			}
		}
	}

	public void view() {
		for (ProductDto dto : list) {
			System.out.println(dto);
		}
	}

	public void insert() {
		ProductDto dto = new ProductDto();
		System.out.println("제품식별번호 : ");
		dto.setPrdNum(scanner.next());
		System.out.println("제품명 : ");
		dto.setPrdName(scanner.next());
		System.out.println("가격 : ");
		dto.setPrdPrice(scanner.nextInt());
		System.out.println("생산일 : ");
		dto.setPrdDate(scanner.next());
		System.out.println("제조사 : ");
		dto.setPrdMaker(scanner.next());
		list.add(dto);
	}

	public void update() {
		String wnum = "";
		ProductDto dto = null;
		System.out.println("수정할 번호를 입력하세요");
		wnum = scanner.next();
		for (ProductDto productDto : list) {
			if (wnum.equals(productDto.getPrdNum())) {
				dto = productDto;
				// System.out.println(dto);
				break;
			}
		}
		if (dto == null) {
			System.out.println("존재하지 않는 상품입니다.");
			return;
		} else {
			System.out.println("제품식별번호 : ");
			dto.setPrdNum(scanner.next());
			System.out.println("제품명 : ");
			dto.setPrdName(scanner.next());
			System.out.println("가격 : ");
			dto.setPrdPrice(scanner.nextInt());
			System.out.println("생산일 : ");
			dto.setPrdDate(scanner.next());
			System.out.println("제조사 : ");
			dto.setPrdMaker(scanner.next());
			list.add(dto);
		}

	}

	public void delete() {
		String wnum = "";
		System.out.println("삭제할 번호를 입력하세요 : ");
		wnum = scanner.next();
		for (ProductDto productDto : list) {
			if (wnum.equals(productDto.getPrdNum())) {
				list.remove(productDto);
				System.out.printf("%d번을 삭제하셨습니다.", wnum);
				break;
			} else {
				System.out.println("해당 번호가 존재하지 않습니다.");
			}
		}
	}
}
