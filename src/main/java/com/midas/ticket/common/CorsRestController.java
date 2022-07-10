package com.midas.ticket.common;

import static com.midas.ticket.common.ApiResponse.OK;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cors")
public class CorsRestController {

	@GetMapping
	public ApiResponse<String> get() {
		return OK("Accept Get Method!");
	}

	@PostMapping
	public ApiResponse<String> post() {
		return OK("Accept Post Method!");
	}

	@PatchMapping
	public ApiResponse<String> patch() {
		return OK("Accept Patch Method!");
	}

	@DeleteMapping
	public ApiResponse<String> delete() {
		return OK("Accept Delete Method!");
	}
}
