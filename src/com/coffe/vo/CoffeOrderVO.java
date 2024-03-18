package com.coffe.vo;

import java.util.List;

public class CoffeOrderVO {
private int coffeId;
private String flag;
private int coffeCnt;
  
public CoffeOrderVO() {
}
public CoffeOrderVO(int coffeId, String flag, int coffeCnt) {
	super();
	this.coffeId = coffeId;
	this.flag = flag;
	this.coffeCnt = coffeCnt;
}
public int getCoffeId() {
	return coffeId;
}
public void setCoffeId(int coffeId) {
	this.coffeId = coffeId;
}
public String getFlag() {
	return flag;
}
public void setFlag(String flag) {
	this.flag = flag;
}
public int getcoffeCnt() {
	return coffeCnt;
}
public void setcoffeCnt(int coffeCnt) {
	this.coffeCnt = coffeCnt;
} 

}
