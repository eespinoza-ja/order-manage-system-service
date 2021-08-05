package com.fractal.omss.service;

import org.springframework.stereotype.Service;

import com.fractal.omss.model.OrderDTO;

@Service
public interface ITaxesOrder {
	public OrderDTO computeTaxesOrder(OrderDTO order);
}
