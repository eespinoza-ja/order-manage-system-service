package com.fractal.omss.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fractal.omss.model.OrderDTO;
import com.fractal.omss.repository.IOrderDAO;
import com.fractal.omss.service.ISequenceGeneratorService;
import com.fractal.omss.service.ITaxesOrder;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api/orders/")
public class OrderController {
	@Autowired
	private IOrderDAO orderDAO;
	
	@Autowired
	private ISequenceGeneratorService sequenceGenerator;
	
	@Autowired
	private ITaxesOrder taxesOrder;
	
	@PostMapping("/add")
	public OrderDTO create(@Validated @RequestBody OrderDTO order){	
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		order.set_id(String.valueOf(sequenceGenerator.generateSequence("order_sequence")));
		order.setDate(formatter.format(new Date()));
		order = taxesOrder.computeTaxesOrder(order);
		return orderDAO.insert(order);
	}
	
	@GetMapping("/all")
	public List<OrderDTO> listAll(){
		return orderDAO.findAll();
	}
	
	@GetMapping("/findproduct/{id}")
	public OrderDTO findProduct(@PathVariable String id){
		return orderDAO.findById(id).get();
	}
	
	@PutMapping("/update/{id}")
	public OrderDTO update(@PathVariable String id, @Validated @RequestBody OrderDTO product) {
		return orderDAO.save(product);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable String id) {
		orderDAO.deleteById(id);
	}
}
