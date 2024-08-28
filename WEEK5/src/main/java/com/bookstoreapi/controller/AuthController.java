package com.bookstoreapi.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstoreapi.dto.LoginRequest;
import com.bookstoreapi.dto.LoginResponse;
import com.bookstoreapi.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/token")
	public LoginResponse login(@RequestBody @Validated LoginRequest request) {
		return authService.attemptLogin(request.getEmail(), request.getPassword());
	}

}
