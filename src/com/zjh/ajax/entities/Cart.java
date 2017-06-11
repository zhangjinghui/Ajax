package com.zjh.ajax.entities;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<String, Book> map = new HashMap<String, Book>();

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addToCart(String name, int price) {
		this.name = name;
		if (map.containsKey(name)) {
			Book book = map.get(name);
			book.setNumber(book.getNumber() + 1);
			book.setPrice(book.getPrice() + price);
		} else {
			Book book = new Book();
			book.setName(name);
			book.setNumber(1);
			book.setPrice(price);
			map.put(name, book);
		}
	}

	public int getTotalNumber() {
		int number = 0;
		for (Book book : map.values()) {
			number += book.getNumber();
		}
		return number;
	}

	public int getTotalPrice() {
		int price = 0;
		for (Book book : map.values()) {
			price += book.getPrice();
		}
		return price;
	}
}
