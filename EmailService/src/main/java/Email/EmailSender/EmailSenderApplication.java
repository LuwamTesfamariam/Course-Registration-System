package Email.EmailSender;

import Email.EmailSender.Integration.KafkaReceiver;
import Email.EmailSender.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class EmailSenderApplication {

	@Autowired
	KafkaReceiver	kafkaReceiver;
	@Autowired
	EmailService	emailService;
	public static void main(String[] args) {
		SpringApplication.run(EmailSenderApplication.class, args);
	}

}
