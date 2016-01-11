package com.qq.common.constants;

public enum BookStatusEnum {
	not_book("未订餐",0),
	already_booked("已订餐",1),
	received("已收到订餐",2);
	
	private String name;
	private int value;
	private BookStatusEnum(String name,int value) {
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

	public static String getNameByValue(int value) {
		for(BookStatusEnum bookStatus : BookStatusEnum.values()) {
			if(value == bookStatus.getValue()) {
				return bookStatus.getName();
			}
		}
		return "";
	}
	
}
