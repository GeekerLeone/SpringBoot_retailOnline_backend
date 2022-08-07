package com.usc.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usc.beans.Product;
import com.usc.dao.ProductDao;
import com.usc.http.Response;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	ProductDao productDao;
	
	public Response registerProduct(Product product) {
		System.out.println(product);
		productDao.save(product);
		return new Response(true);
	}
	
	public Response updateProduct(Product product) {
		Product p = productDao.findByProductname(product.getName());
		p.setBrand(p.getBrand());
		p.setName(p.getName());
		p.setPrice(p.getPrice());
		p.setStock(p.getStock());
		productDao.save(p);
		return new Response(true);
		
	}
	
	public Response deleteProduct(int id) {
		if (productDao.findById(id) != null) {
			productDao.deleteById(id);
			return new Response(true);
		} else {
			return new Response(false, "product is not found");
		}
	}
}
