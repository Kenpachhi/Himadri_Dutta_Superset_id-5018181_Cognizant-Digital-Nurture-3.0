package com.bookstoreapi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bookstoreapi.dto.UserEntity;

@Service
public class UserService {
	private static final String ADMIN_EMAIL = "admin@test.com";
	private static final String OTHER_EMAIL = "test@test.com";

	public Optional<UserEntity> findByEmail(String email) {
		if (ADMIN_EMAIL.equalsIgnoreCase(email)) {
			var user = new UserEntity();
			user.setId(1L);
			user.setEmail(ADMIN_EMAIL);
			user.setPassword("$2a$12$OBnerD3ZrnkqY/ofkaxune1jnpUscFhTGCcuVA9x5lgAGAtr6Bss2"); 
			user.setRole("ROLE_ADMIN");
			user.setExtraInfo("ADMIN_USER");
			return Optional.of(user);
		} else if (OTHER_EMAIL.equalsIgnoreCase(email)) {
			var user = new UserEntity();
			user.setId(99L);
			user.setEmail(OTHER_EMAIL);
			user.setPassword("$2a$12$OBnerD3ZrnkqY/ofkaxune1jnpUscFhTGCcuVA9x5lgAGAtr6Bss2"); 
			user.setRole("ROLE_USER");
			user.setExtraInfo("GENERAL_USER");
			return Optional.of(user);
		} else {
			return Optional.empty();
		}
	}
}
