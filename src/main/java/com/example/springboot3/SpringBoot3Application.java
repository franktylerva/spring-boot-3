package com.example.springboot3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class SpringBoot3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot3Application.class, args);
	}
}

@RestController
class GreetingController {

	@GetMapping("/greetings/{name}")
	Greeting greet(@PathVariable String name) {

		var valid = (StringUtils.hasText(name) && Character.isUpperCase(name.charAt(0)));

		if(!valid) {
			throw new IllegalArgumentException("Name must start with a capital letter.");
		}
		return new Greeting(name);
	}

}

@ControllerAdvice
class ProblemDetailErrorHandlingControllerAdvice {

	@ExceptionHandler
	public ProblemDetail onException(IllegalArgumentException exception) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
	}

}