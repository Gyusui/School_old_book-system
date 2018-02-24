package com.SOB.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.SOB.bean.BookBean;

public class Cart {

	private ArrayList<BookBean> list_cart = null;
	
	public Cart (){}
	
	public void addProToCart(BookBean pro){
		if(list_cart == null){
			list_cart = new ArrayList<BookBean>();
		}
		Iterator<BookBean> it = list_cart.iterator();
		while(it.hasNext()){
			BookBean prod = it.next();
			if(prod.getBookName().equals(pro.getBookName())){
				return;
			}
		}
		list_cart.add(pro);
	}
	public void removeProFromCart(String id){
		if(list_cart == null){
			return;
		}
		Iterator<BookBean> it = list_cart.iterator();
		while(it.hasNext()){
			BookBean prod = it.next();
			int bookId = new Integer(id);
			if(prod.getId() == bookId){
				it.remove();
				return;
			}
		}
	}
	public Double getAllProductPrice(){
		Double totalValue = 0.00;
		if(list_cart == null){
			return 0.00;
		}
		Iterator<BookBean> it = list_cart.iterator();
		while(it.hasNext()){
			BookBean pro = it.next();
			totalValue +=new Double(pro.getPrice());
		}
		return totalValue;
	}
	public List<BookBean> getAllProductFromCart(){
		return list_cart;
	}
}
