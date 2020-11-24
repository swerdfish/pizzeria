package com.pizzeria.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzeria.training.models.Order;
import com.pizzeria.training.service.OrderService;

@TestExecutionListeners(MockitoTestExecutionListener.class)	//include this line or else mockbeans will be null in test class
@AutoConfigureMockMvc
@SpringBootTest
public class OrderControllerIntegrationTest extends AbstractTestNGSpringContextTests /*extend this to start Spring context for test*/ {
	
	@MockBean private OrderService pizzaServ;
	
	@Autowired private MockMvc mvc;
	
	@Autowired private ObjectMapper objMap;
	
	private Order testOrder;
}
