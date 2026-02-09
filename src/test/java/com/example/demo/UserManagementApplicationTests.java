package com.example.demo;

import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserManagementApplicationTests {

    @Mock
    UserService service;

	@Test
	void contextLoads() {


	}

}
