package com.usc.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usc.beans.Order;
import com.usc.beans.OrderProduct;
import com.usc.dao.OrderDao;
import com.usc.http.Response;

@Service
@Transactional
public class OrderService {
	@Autowired
	OrderDao orderDao;
	
	public Response registerOrder(Order order) {
		order.setPurchase_date(order.getPurchase_date());
		for (OrderProduct i: order.getPurchases()) {
			if (i.getQty() > i.getProduct().getStock()) {
				return new Response(false, "Not enough products in stock");
			}
		}
		System.out.println(order);
		orderDao.save(order);
		return new Response(true);
	}
	
	public Response updateOrder(Order order) {
		Order o = orderDao.findById(order.getId()).get();
		o.setPurchase_date(o.getPurchase_date());
		for (OrderProduct i: o.getPurchases()) {
			if (i.getQty() > i.getProduct().getStock()) {
				return new Response(false, "Not enough products in stock");
			}
		}
		o.setPurchases(o.getPurchases());
		orderDao.save(o);
		return new Response(true);
		
	}
	
	public Response deleteProduct(int id) {
		if (orderDao.findById(id) != null) {
			orderDao.deleteById(id);
			return new Response(true);
		} else {
			return new Response(false, "order is not found");
		}
	}
}
