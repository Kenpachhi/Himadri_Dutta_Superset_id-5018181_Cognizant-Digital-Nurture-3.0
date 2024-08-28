package com.bookstoreapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookstoreapi.dto.CustomerDTO;
import com.bookstoreapi.model.Customer;
import com.bookstoreapi.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateCustomer() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setName("John Doe");
		customerDTO.setEmail("john.doe@example.com");
		customerDTO.setPhone("1234567890");

		Customer customer = new Customer();
		customer.setName(customerDTO.getName());
		customer.setEmail(customerDTO.getEmail());
		customer.setPhone(customerDTO.getPhone());

		when(customerRepository.save(any(Customer.class))).thenReturn(customer);

		CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);

		assertNotNull(createdCustomer);
		assertEquals(customerDTO.getName(), createdCustomer.getName());
		assertEquals(customerDTO.getEmail(), createdCustomer.getEmail());
		assertEquals(customerDTO.getPhone(), createdCustomer.getPhone());
		verify(customerRepository).save(any(Customer.class));
	}

	@Test
	public void testGetCustomerById_Found() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(1L);
		customerDTO.setName("John Doe");
		customerDTO.setEmail("john.doe@example.com");
		customerDTO.setPhone("1234567890");

		Customer customer = new Customer();
		customer.setId(1L);
		customer.setName(customerDTO.getName());
		customer.setEmail(customerDTO.getEmail());
		customer.setPhone(customerDTO.getPhone());

		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
		Optional<CustomerDTO> result = customerService.getCustomerById(1L);
		assertTrue(result.isPresent());
		assertEquals(customerDTO.getId(), result.get().getId());
		assertEquals(customerDTO.getName(), result.get().getName());
		assertEquals(customerDTO.getEmail(), result.get().getEmail());
		assertEquals(customerDTO.getPhone(), result.get().getPhone());
	}

	@Test
	public void testGetCustomerById_NotFound() {
		when(customerRepository.findById(1L)).thenReturn(Optional.empty());
		Optional<CustomerDTO> result = customerService.getCustomerById(1L);
		assertFalse(result.isPresent());
	}

	@Test
	public void testGetAllCustomers() {
		CustomerDTO customerDTO1 = new CustomerDTO();
		customerDTO1.setId(1L);
		customerDTO1.setName("John Doe");
		customerDTO1.setEmail("john.doe@example.com");
		customerDTO1.setPhone("1234567890");

		CustomerDTO customerDTO2 = new CustomerDTO();
		customerDTO2.setId(2L);
		customerDTO2.setName("Jane Smith");
		customerDTO2.setEmail("jane.smith@example.com");
		customerDTO2.setPhone("0987654321");

		List<CustomerDTO> customerDTOs = Arrays.asList(customerDTO1, customerDTO2);

		Customer customer1 = new Customer();
		customer1.setId(1L);
		customer1.setName("John Doe");
		customer1.setEmail("john.doe@example.com");
		customer1.setPhone("1234567890");

		Customer customer2 = new Customer();
		customer2.setId(2L);
		customer2.setName("Jane Smith");
		customer2.setEmail("jane.smith@example.com");
		customer2.setPhone("0987654321");

		List<Customer> customers = Arrays.asList(customer1, customer2);

		when(customerRepository.findAll()).thenReturn(customers);
		List<CustomerDTO> result = customerService.getAllCustomers();
		assertEquals(customerDTOs.size(), result.size());
		assertEquals(customerDTOs.get(1).getName(), result.get(1).getName());
		assertEquals(customerDTOs.get(1).getEmail(), result.get(1).getEmail());
		assertEquals(customerDTOs.get(1).getPhone(), result.get(1).getPhone());
	}

	@Test
	public void testUpdateCustomer() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(1L);
		customerDTO.setName("Updated Name");
		customerDTO.setEmail("updated.email@example.com");
		customerDTO.setPhone("1112223333");

		Customer customer = new Customer();
		customer.setId(1L);
		customer.setName("Old Name");
		customer.setEmail("old.email@example.com");
		customer.setPhone("0000000000");

		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
		when(customerRepository.save(any(Customer.class))).thenReturn(customer);

		CustomerDTO updatedCustomer = customerService.updateCustomer(1L, customerDTO);

		assertNotNull(updatedCustomer);
		assertEquals(customerDTO.getName(), updatedCustomer.getName());
		assertEquals(customerDTO.getEmail(), updatedCustomer.getEmail());
		assertEquals(customerDTO.getPhone(), updatedCustomer.getPhone());
	}

	@Test
	public void testUpdateCustomer_NotFound() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(1L);
		customerDTO.setName("Non-existent");
		customerDTO.setEmail("nonexistent@example.com");
		customerDTO.setPhone("9999999999");

		when(customerRepository.findById(1L)).thenReturn(Optional.empty());
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			customerService.updateCustomer(1L, customerDTO);
		});
		assertEquals("Customer not found", exception.getMessage());
	}

	@Test
	public void testDeleteCustomer() {
		doNothing().when(customerRepository).deleteById(1L);
		customerService.deleteCustomer(1L);
		verify(customerRepository).deleteById(1L);
	}
}
