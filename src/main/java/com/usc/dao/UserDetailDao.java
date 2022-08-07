package com.usc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usc.beans.UserDetail;

@Repository
public interface UserDetailDao extends JpaRepository<UserDetail, Integer> {
	UserDetail findByname(String name);
}
