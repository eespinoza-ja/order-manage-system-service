package com.fractal.omss.controller;
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

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api/orders/")
public class OrderController {
	@Autowired
	private IOrderDAO orderDAO;
	
	@Autowired
	private ISequenceGeneratorService sequenceGenerator;
	
	@PostMapping("/add")
	public OrderDTO create(@Validated @RequestBody OrderDTO product){	
		product.set_id(String.valueOf(sequenceGenerator.generateSequence("order_sequence")));
		return orderDAO.insert(product);
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
