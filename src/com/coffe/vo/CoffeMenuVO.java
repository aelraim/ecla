package com.coffe.vo;

public class CoffeMenuVO {
	private int coffeId;
	private String coffeNm;

	public CoffeMenuVO() {
	}

	public CoffeMenuVO(int coffeId, String coffeNm) {
		super();
		this.coffeId = coffeId;
		this.coffeNm = coffeNm;
	}

	public int getCoffeId() {
		return coffeId;
	}

	public void setCoffeId(int coffeId) {
		this.coffeId = coffeId;
	}

	public String getCoffeNm() {
		return coffeNm;
	}

	public void setCoffeNm(String coffeNm) {
		this.coffeNm = coffeNm;
	}

}
