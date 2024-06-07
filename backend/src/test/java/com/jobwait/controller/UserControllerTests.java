package com.jobwait.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import com.jobwait.controllers.UserController;

import reactor.core.publisher.Flux;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@GraphQlTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void shouldGetUsers() {
        
        //TEST THAT GETTING USERS WORKS HERE

        // when(this.userRepository.findAll())
        //         .thenReturn(Flux.just(new User(
        //                 "1234",
        //                 "private",
        //                 "12345678",
        //                 "UK",
        //                 LocalDate.of(2020, 1, 1),
        //                 0,
        //                 "Test Company",
        //                 "active")));

        // this.graphQlTester
        //         .documentName("companyList")
        //         .variable("page", 0)
        //         .execute()
        //         .path("companyList")
        //         .matchesJson("""
        //             [{
        //                 "id": null,
        //                 "SIC": "1234",
        //                 "name": "Test Company",
        //                 "status": "active",
        //                 "category": "private",
        //                 "companyNumber": "12345678",
        //                 "countryOfOrigin": "UK"
        //             }]
        //         """);
    }
}
