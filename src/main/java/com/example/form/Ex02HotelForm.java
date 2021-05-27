package com.example.form;

import javax.validation.constraints.Pattern;

public class Ex02HotelForm {
	/** 入力された金額 */
	@Pattern(regexp = "^(\\s*|[0-9]{1,6})$", message = "金額が正しくありません")
	private String price;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Ex02HotelForm [price=" + price + "]";
	}

}
