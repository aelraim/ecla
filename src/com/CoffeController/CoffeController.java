package com.CoffeController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.coffe.dao.CoffeDAO;
import com.coffe.vo.CoffeOrderVO;
import com.coffe.vo.CoffeSaleVO;

public class CoffeController {
	CoffeDAO cd = new CoffeDAO();
	List<HashMap<String, Object>> orderlist = new ArrayList<HashMap<String, Object>>();
	Scanner sc = new Scanner(System.in);

	public void getCoffeList() {
		// 커피 메뉴 출력
		List<Integer> num = new ArrayList<Integer>();
		List<Integer> num1 = new ArrayList<Integer>();

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		list = cd.getCoffeList();

		boolean run = true;
		while (run) {
			
			HashMap<String, Object> cov = new HashMap<String, Object>();
			System.out.println("------------------------------");
			System.out.println("메뉴를 선택해주세요");
			for (HashMap<String, Object> map : list) {
				int coffeId = (int) map.get("coffeId");
				String coffeNm = (String) map.get("coffeNm");
				int price = (int) map.get("price");
				System.out.println("  " + (coffeId + " | " + coffeNm + " | " + price + " | "));
			}
			System.out.println("------------------------------");

			System.out.println("메뉴를 선택해주세요");
			int coffeId = sc.nextInt();
			cov.put("coffeId", coffeId);
			num.add(coffeId);
// 2 3 4 5
			System.out.println("잔 수를 입력해주세요");
			int coffeCnt = sc.nextInt();
			cov.put("coffeCnt", coffeCnt);
			num1.add(coffeCnt);

			System.out.println("계속 주문 하시겠습니까? Y/N");
			String orderRun = sc.next();
			if (orderRun.equals("N")) {
				cov.put("falg", "N");
				int total = 0;
				int totalCnt = 0;

				for (int i = 0; i < num1.size(); i++) {
					HashMap<String, Object> map = list.get(num.get(i) - 1);
					//1234-
					
					String coffeNm = (String) map.get("coffeNm");
					int price = (int) map.get("price");
					int cofCnt = num1.get(i);

					System.out.println(coffeNm + " " + cofCnt + "잔 주문하셨습니다");
					total += cofCnt * price;
					totalCnt += cofCnt;
				}
				System.out.println("총 " + totalCnt + "잔 주문하셨으며");
				System.out.println("총 금액은 " + total + "원 입니다");
				run = false;
			} else {
				cov.put("falg", "Y");
				;
			}
			setOrderMenu(cov);
		}

	}

	public void setOrderMenu(HashMap<String, Object> cov) {
		// 커피 주문서 출력

		orderlist.add(cov);
		if (cov.get("falg").equals("N")) {
			cd.setOrderMenu(orderlist);
		}

	}

	public void getDailySales() {
		// 오늘 매상 출력

		for (HashMap<String, Object> cv : cd.getDailySales()) {

			int coffeId = (int) cv.get("coffeId");
			String coffeNm = (String) cv.get("coffeNm");
			int price = (int) cv.get("price");
			int saleCnt = (int) cv.get("saleCnt");
			Date saleDate = (Date) cv.get("saleDate");

			System.out.println("  " + (coffeId + " | " + coffeNm + " | " + price + " | " + saleCnt + " | " + saleDate));

		}

		int total = 0;
		for (HashMap<String, Object> cv : cd.getDailySales()) {
			int price = (int) cv.get("price");
			int saleCnt = (int) cv.get("saleCnt");
			Date saleDate = (Date) cv.get("saleDate");
			String coffeNm = (String) cv.get("coffeNm");
			int saletotal = price * saleCnt;
			total += saletotal;
			System.out.println(saleDate + " " + coffeNm + " 판매액 " + saletotal + "원");
		}
		System.out.println("총 합계액 " + total + "원");
	}
	public void setCoffeMenuAdd(){
		HashMap<String, Object> orderlistadd = new HashMap<String, Object>();
		System.out.println("메뉴명을 입력하세요");
		String COFFE_NM = sc.next();
		orderlistadd.put("COFFE_NM", COFFE_NM);
		cd.setOrderMenuAdd(orderlistadd);
		
		System.out.println("가격을 입력하세요");
		int PRICE = sc.nextInt();
		orderlistadd.put("PRICE", PRICE);
		cd.setOrderpriceAdd(orderlistadd);
		
	}
}
