package Email.EmailSender.Integration;

import Email.EmailSender.Service.EmailService;
import Email.EmailSender.domain.Email;
import Email.EmailSender.domain.EmailInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiver {
    @Autowired
    EmailService    emailService;
    @KafkaListener(topics = "topicA")
    public void receiveRegistrationEvent(@Payload String receivingString){
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("message received ");
        try {
            System.out.println("message received ");
            EmailInfo emailInfo = objectMapper.readValue(receivingString, EmailInfo.class);
            System.out.println("message received " + emailInfo);
            for (int i = 0; i < emailInfo.getEmails().size(); i++){
                String  body = "Dear " + emailInfo.getNames().get(i) + "\n The registration period will open on " + emailInfo.getStartDate()
                        + " and it will continue until " + emailInfo.getEndDate() + " select all your choices and submit them before due date";
                Email   email = new Email(emailInfo.getEmails().get(i), "Registration event open", body);
                System.out.println("message received " + email);
                emailService.sendSimpleEmail(email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
