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

import com.usc.beans.UserDetail;
import com.usc.dao.UserDetailDao;
import com.usc.http.Response;
import com.usc.service.UserDetailService;

@RestController()
@RequestMapping("/user_detail")
public class UserDetailController {
	@Autowired
	UserDetailDao userdetailDao;
	
	@Autowired
	UserDetailService userdetailService;

	@GetMapping
	public List<UserDetail> getUserDetailAll() {
		return userdetailDao.findAll();
	}
	
	@GetMapping("/{id}")
	public UserDetail getUserDetail(@PathVariable int id) {
		return userdetailDao.findById(id).get();
	}
	
	@PostMapping
	public Response addUserDetail(@RequestBody UserDetail userdetail) {
		return userdetailService.addUserDetail(userdetail);
	}
	
	@PutMapping
	public Response updateOrder(@RequestBody UserDetail order) {
		return userdetailService.updateUserDetail(order);
	}
	
	@DeleteMapping("/{id}")
	public Response deleteProduct(@PathVariable int id) {
		return userdetailService.deleteUser(id);
	}

}
