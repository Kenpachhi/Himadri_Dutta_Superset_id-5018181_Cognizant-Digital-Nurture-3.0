package com.bookstoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bookstoreapi.dto.CustomerDTO;
import com.bookstoreapi.service.CustomerService;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

	@Mock
	private CustomerService customerService;

	@InjectMocks
	private CustomerController customerController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateCustomer() {
		CustomerDTO customerDTO = new CustomerDTO();
		when(customerService.createCustomer(any(CustomerDTO.class))).thenReturn(customerDTO);
		ResponseEntity<CustomerDTO> response = customerController.createCustomer(customerDTO);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(customerDTO, response.getBody());
	}

	@Test
	public void testGetCustomerById() {
		CustomerDTO customerDTO = new CustomerDTO();
		when(customerService.getCustomerById(1L)).thenReturn(Optional.of(customerDTO));
		ResponseEntity<CustomerDTO> response = customerController.getCustomerById(1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(customerDTO, response.getBody());
	}

	@Test
	public void testGetCustomerByIdNotFound() {
		when(customerService.getCustomerById(1L)).thenReturn(Optional.empty());
		ResponseEntity<CustomerDTO> response = customerController.getCustomerById(1L);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody());
	}

	@Test
	public void testGetAllCustomers() {
		List<CustomerDTO> customerList = new ArrayList<>();
		when(customerService.getAllCustomers()).thenReturn(customerList );
		ResponseEntity<List<CustomerDTO>> response = customerController.getAllCustomers();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(customerList, response.getBody());
	}

	@Test
	public void testUpdateCustomer() {
		CustomerDTO customerDTO = new CustomerDTO();
		when(customerService.updateCustomer(eq(1L), any(CustomerDTO.class))).thenReturn(customerDTO);
		ResponseEntity<CustomerDTO> response = customerController.updateCustomer(1L, customerDTO);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(customerDTO, response.getBody());
	}

	@Test
	public void testDeleteCustomer() {
		doNothing().when(customerService).deleteCustomer(1L);
		ResponseEntity<Void> response = customerController.deleteCustomer(1L);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

}
