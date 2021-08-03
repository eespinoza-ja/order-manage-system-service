package com.fractal.omss.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.fractal.omss.model.ProductDTO;

@Repository
public interface IProductDAO extends MongoRepository<ProductDTO, String>{

}
