package com.app.eventOrganizer.repository;

import com.app.eventOrganizer.model.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TestRepository extends JpaRepository<TestModel, Long> {

}


