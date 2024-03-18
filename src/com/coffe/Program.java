package com.coffe;

import java.util.Scanner;

import com.CoffeController.CoffeController;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CoffeController cct = new CoffeController();
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		while (run) {
			System.out.println();
			System.out.println("__________________________");;
			System.out.println("________커피 키오스크________");
			System.out.println();
			System.out.println("1. 사용자 2.관리자 3.사용종료");
			int sel = sc.nextInt();
			if (sel == 1) {
				
				cct.getCoffeList();
				
			} else if (sel == 2) {
				System.out.println("1.매상확인 2.메뉴추가");
				
				int adsel = sc.nextInt();
				
				if(adsel==1) {
					
				cct.getDailySales();
				
				} else if(adsel ==2) {
					
					cct.setCoffeMenuAdd();
					
				}
			} else if (sel == 3) {
				run = false;
			}
		}
	}
}
