package com.usc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usc.beans.Order;
import com.usc.beans.User;

public interface OrderDao extends JpaRepository<Order, Integer> {
	List<Order> findAllByUser(User user);
}
