package com.fractal.omss.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.fractal.omss.model.DatabaseSequence;

@Service
public class SequenceGeneratorServiceImpl implements ISequenceGeneratorService {
	private MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorServiceImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
	
	@Override
	public long generateSequence(String sequenceName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(sequenceName));
	    
		DatabaseSequence counter = mongoOperations.findAndModify(query, new Update().inc("seq",1), options().returnNew(true).upsert(true), DatabaseSequence.class);
	    return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}

}
