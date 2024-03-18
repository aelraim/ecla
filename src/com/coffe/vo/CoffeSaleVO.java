package com.coffe.vo;

import java.util.Date;

public class CoffeSaleVO extends CoffeMenuVO {
	int price;
	int saleCnt;
	Date saleDate;
	String priceDate;

	
	public CoffeSaleVO() {
	}

	
	public CoffeSaleVO(int price, int saleCnt, Date saleDate, String priceDate) {
		super();
		this.price = price;
		this.saleCnt = saleCnt;
		this.saleDate = saleDate;
		this.priceDate = priceDate;
	}


	public CoffeSaleVO(int coffeId, String coffeNm, int price, int saleCnt, Date saleDate, String priceDate) {
		super.setCoffeId(coffeId);
		super.setCoffeNm(coffeNm);
		this.price = price;
		this.saleCnt = saleCnt;
		this.saleDate = saleDate;
		this.priceDate = priceDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSaleCnt() {
		return saleCnt;
	}

	public void setSaleCnt(int saleCnt) {
		this.saleCnt = saleCnt;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public String getPriceDate() {
		return priceDate;
	}

	public void setPriceDate(String priceDate) {
		this.priceDate = priceDate;
	}
}
