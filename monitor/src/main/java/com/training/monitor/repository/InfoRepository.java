package com.training.monitor.repository;

import com.training.monitor.models.Info;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends MongoRepository<Info, String> {
}
