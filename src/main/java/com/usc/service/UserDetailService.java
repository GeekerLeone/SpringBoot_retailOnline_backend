package com.usc.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usc.beans.UserDetail;
import com.usc.dao.UserDetailDao;
import com.usc.http.Response;

@Service
@Transactional
public class UserDetailService {
	
	@Autowired
	UserDetailDao userdetailDao;
	
	public Response addUserDetail(UserDetail userdetail) {
		System.out.println(userdetail);
		userdetailDao.save(userdetail);
		return new Response(true);
	}
	
	public Response updateUserDetail(UserDetail userdetail) {
		UserDetail u = userdetailDao.findById(userdetail.getId()).get();
		u.setAddress(userdetail.getAddress());
		u.setCity(userdetail.getCity());
		u.setEmail(userdetail.getEmail());
		u.setName(userdetail.getName());
		u.setPhone(userdetail.getPhone());
		u.setState(userdetail.getState());
		u.setUser(userdetail.getUser());
		u.setZip(userdetail.getZip());
		userdetailDao.save(u);
		return new Response(true);
		
	}
	
	public Response deleteUser(int id) {
		if (userdetailDao.findById(id) != null) {
			userdetailDao.deleteById(id);
			return new Response(true);
		} else {
			return new Response(false, "User is not found!");
		}
	}
	
}
