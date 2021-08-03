package com.fractal.omss.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="products")
public class OrderDTO {
	@Transient
    public static final String SEQUENCE_NAME = "order_sequence";
	
	@Id
	private long _id;
	private String status;
	private String date;
	private String customer;
	private double city_tax;
	private double country_tax;
	private double state_tax;
	private double federal_tax;
	private double total_taxes;
	private double total_amount;
	private List<ItemOrder> items;
	
	public long get_id() {
		return _id;
	}
	public void set_id(long _id) {
		this._id = _id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public double getCity_tax() {
		return city_tax;
	}
	public void setCity_tax(double city_tax) {
		this.city_tax = city_tax;
	}
	public double getCountry_tax() {
		return country_tax;
	}
	public void setCountry_tax(double country_tax) {
		this.country_tax = country_tax;
	}
	public double getState_tax() {
		return state_tax;
	}
	public void setState_tax(double state_tax) {
		this.state_tax = state_tax;
	}
	public double getFederal_tax() {
		return federal_tax;
	}
	public void setFederal_tax(double federal_tax) {
		this.federal_tax = federal_tax;
	}
	public double getTotal_taxes() {
		return total_taxes;
	}
	public void setTotal_taxes(double total_taxes) {
		this.total_taxes = total_taxes;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	public List<ItemOrder> getItems() {
		return items;
	}
	public void setItems(List<ItemOrder> items) {
		this.items = items;
	}
	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}
	
}