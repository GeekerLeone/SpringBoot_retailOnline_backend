package com.usc.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "usc_product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PRODUCT_SEQ")
	@SequenceGenerator(name = "PRODUCT_SEQ", sequenceName = "USC_PRODUCT_SEQ", allocationSize = 1)
	private int id;
	@Column
	@NotEmpty
	private String productname;
	@Column
	@NotEmpty
	private String brand;
	@Column
	@NotNull
	@Positive
	private int price;
	@Column
	@NotNull
	@PositiveOrZero
	private int stock;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Product(@NotEmpty String name, @NotEmpty String brand, @NotNull @PositiveOrZero int stock) {
		super();
		this.productname = name;
		this.brand = brand;
		this.stock = stock;
	}

	public Product(@NotEmpty String name, @NotNull @Positive int price) {
		super();
		this.productname = name;
		this.price = price;
	}

	public Product(@NotEmpty String name, @NotEmpty String brand, @NotNull @Positive int price,
			@NotNull @PositiveOrZero int stock) {
		super();
		this.productname = name;
		this.brand = brand;
		this.price = price;
		this.stock = stock;
	}

	public Product(int id, @NotNull @PositiveOrZero int stock) {
		super();
		this.id = id;
		this.stock = stock;
	}

	public Product(int id, @NotEmpty String name, @NotNull @PositiveOrZero int stock) {
		super();
		this.id = id;
		this.productname = name;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return productname;
	}
	public void setName(String name) {
		this.productname = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}



	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + productname + ", brand=" + brand + ", price=" + price + ", stock=" + stock
				+ "]";
	}

}
