package com.example.demo;

import com.example.demo.enums.Status;
import com.example.demo.model.TODO;
import com.example.demo.repository.TODORepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/*@Bean
	CommandLineRunner initDatabase(TODORepository repository){
		return args -> {
			repository.deleteAll();

			for(int i = 0; i < 5; i++) {

				TODO todo = new TODO();
				todo.setContent("Angular com Spring " + i);
				todo.setStatus(Status.ACTIVE);

				repository.save(todo);
			}
		};
	}*/

}
