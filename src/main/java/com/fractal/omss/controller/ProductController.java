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

import com.fractal.omss.model.ProductDTO;
import com.fractal.omss.repository.IProductDAO;
import com.fractal.omss.service.ISequenceGeneratorService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api/products/")
public class ProductController {
	
	@Autowired
	private IProductDAO productDAO;
	
	@Autowired
	private ISequenceGeneratorService sequenceGenerator;
	
	@PostMapping("/add")
	public ProductDTO create(@Validated @RequestBody ProductDTO product){	
		product.set_id(String.valueOf(sequenceGenerator.generateSequence("product_sequence")));
		return productDAO.insert(product);
	}
	
	@GetMapping("/all")
	public List<ProductDTO> listAll(){
		return productDAO.findAll();
	}
	
	@GetMapping("/findproduct/{id}")
	public ProductDTO findProduct(@PathVariable String id){
		return productDAO.findById(id).get();
	}
	
	@PutMapping("/update/{id}")
	public ProductDTO update(@PathVariable String id, @Validated @RequestBody ProductDTO product) {
		product.set_id(id);
		return productDAO.save(product);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable String id) {
		productDAO.deleteById(id);
	}
}
