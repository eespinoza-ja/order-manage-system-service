package com.fractal.omss.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.fractal.omss.model.OrderDTO;

@Repository
public interface IOrderDAO extends MongoRepository<OrderDTO, String>{

}
