package com.example.demo.model.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.domain.TestDB;
import com.example.demo.model.repository.TestRepository;
import lombok.RequiredArgsConstructor;
@Service // 서비스계층명시스프링내부자동등록됨
@RequiredArgsConstructor // 생성자생성
public class TestService{
    @Autowired // 객체주입자동화
    private TestRepository testRepository;

    public TestDB findByName(String name) {
    return (TestDB) testRepository.findByName(name);
    }
}