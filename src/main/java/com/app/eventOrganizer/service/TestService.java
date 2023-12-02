package com.app.eventOrganizer.service;

import com.app.eventOrganizer.model.TestModel;
import com.app.eventOrganizer.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestService {

    @Autowired
    public TestRepository testRepository;

    public TestModel saveToDB(Long ID, String name) {
        TestModel testModel = new TestModel(ID, name);
        testRepository.save(testModel);
        return testModel;
    }

    public List<TestModel> getInfo() {
        return testRepository.findAll();
    }
}
