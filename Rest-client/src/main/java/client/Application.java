package client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

	RestTemplate restTemplate = new RestTemplate();
	private String serverUrl = "http://localhost:8080/students";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// get student
		Student student = restTemplate.getForObject(serverUrl+"/{id}", Student.class, 1);
		System.out.println(student);

		// add student
		restTemplate.postForLocation(serverUrl, new Student(111,"Doe", "jdoe@acme.com"));
		System.out.println("saved");

		// get student
		student = restTemplate.getForObject(serverUrl+"/{id}", Student.class, 111);
		System.out.println(student);

		// delete mary
		restTemplate.delete(serverUrl+"/{id}", 111);
		System.out.println("deleted");



	}
}
