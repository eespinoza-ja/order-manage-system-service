package com.fractal.omss.service;

import org.springframework.stereotype.Service;

import com.fractal.omss.model.OrderDTO;
import com.fractal.omss.model.OrderItem;

@Service
public class TaxesOrderImpl implements ITaxesOrder {

	@Override
	public OrderDTO computeTaxesOrder(OrderDTO order) {
		double subtotal = 0;
		for(OrderItem item : order.getItems()) {
			subtotal += item.getPrice();
		};
		order.setCity_tax(subtotal * 0.1);
		order.setCounty_tax(subtotal * 0.05);
		order.setState_tax(subtotal * 0.08);
		order.setFederal_tax(subtotal * 0.02);
		order.setTotal_taxes(order.getCity_tax() + order.getCounty_tax() + order.getState_tax() + order.getFederal_tax());
		order.setTotal_amount(subtotal + order.getTotal_taxes());
		return order;
	}

}
