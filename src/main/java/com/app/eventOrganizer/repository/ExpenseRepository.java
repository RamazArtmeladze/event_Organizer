package com.app.eventOrganizer.repository;

import com.app.eventOrganizer.model.ExpenseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseModel, Long> {

}
