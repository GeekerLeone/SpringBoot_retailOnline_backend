package com.usc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usc.beans.Order;
import com.usc.dao.OrderDao;
import com.usc.http.Response;
import com.usc.service.OrderService;

@RestController()
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	OrderService orderService;

	@GetMapping
	public List<Order> getAllOrders() {
		return orderDao.findAll();
	}
	
	@GetMapping("/{id}")
	public Order getOrder(@PathVariable int id) {
		return orderDao.findById(id).get();
	}
	
	@PostMapping
	public Response placeOrder(@RequestBody Order order) {
		return orderService.registerOrder(order);
	}
	
	@PutMapping
	public Response updateOrder(@RequestBody Order order) {
		return orderService.updateOrder(order);
	}
	
	@DeleteMapping("/{id}")
	public Response deleteProduct(@PathVariable int id) {
		return orderService.deleteProduct(id);
	}
}
