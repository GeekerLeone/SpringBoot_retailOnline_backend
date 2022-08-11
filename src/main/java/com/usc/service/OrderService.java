package com.usc.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.usc.beans.Order;
import com.usc.beans.OrderProduct;
import com.usc.dao.OrderDao;
import com.usc.dao.OrderProductDao;
import com.usc.dao.ProductDao;
import com.usc.dao.UserDao;
import com.usc.http.Response;

@Service
@Transactional
public class OrderService {
	@Autowired
	OrderDao orderDao;

	@Autowired
	ProductDao productDao;

	@Autowired
	OrderProductDao orderProductDao;

	@Autowired
	OrderProductDao orderproductDao;
	
	@Autowired
	UserDao userDao;
	
	public List<Order> getOrders(Authentication authentication) {
		if (isAdmin(authentication.getAuthorities())) {
			return orderDao.findAll();
		} else {
			return orderDao.findAllByUser(userDao.findByUsername(authentication.getName()));
		}
	}
	
	public Response placeOrder(Order order, String name) {
		for (OrderProduct i: order.getPurchases()) {
			if (i.getQty() > productDao.findByProductname(i.getProduct().getName()).getStock()) {
				return new Response(false, "Not enough products in stock");
			}
			i.setProduct(productDao.findByProductname(i.getProduct().getName()));
			i.setOrder(order);
			i.setQty(i.getQty());
		productDao.findByProductname(i.getProduct().getName()).setStock(productDao.findByProductname(i.getProduct().getName()).getStock() - i.getQty());
		}
		order.setPurchase_date(order.getPurchase_date());
		order.setUser(userDao.findByUsername(name));
		System.out.println(order);
		orderDao.save(order);
		
		return new Response(true);
	}
	
	public Response updateOrder(Order order) {
		Order o = orderDao.findById(order.getId()).get();
		o.setPurchase_date(order.getPurchase_date());
		for (OrderProduct item: order.getPurchases()) {
  			if (item.getQty() > productDao.findByProductname(item.getProduct().getName()).getStock()) {
				return new Response(false, "Not enough products in stock");
			}
  			if (o.getPurchases().contains(item)) {
  				o.getPurchases().get(o.getPurchases().indexOf(item)).setQty(item.getQty());
  			} else {
  				o.getPurchases().add(item);
  			}
			
			
		}
		System.out.println(o);
		orderDao.save(o);
		return new Response(true);
	}
	
	public Response deleteOrder(int id, Authentication authentication) {
		if (orderDao.findById(id).get().getUser().getUsername().equals(authentication.getName()) || isAdmin(authentication.getAuthorities())) {
			if (orderDao.findById(id) != null) {
				orderDao.deleteById(id);
				return new Response(true);
				} 
			}
				return new Response(false, "order is not found");
	}
	
	public boolean isAdmin(Collection<? extends GrantedAuthority> profiles) {
		boolean isAdmin = false;
		for (GrantedAuthority profile : profiles) {
			if (profile.getAuthority().equals("ROLE_ADMIN")) {
				isAdmin = true;
			}
		}
		return isAdmin;
	}

}
