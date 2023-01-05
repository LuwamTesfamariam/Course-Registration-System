package registration.registrationsystem.Integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import registration.registrationsystem.domain.RegistrationEvent;
import registration.registrationsystem.domain.Student;

import java.util.List;

@Service
public class Sender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public void send(String topic, String event){
        kafkaTemplate.send(topic, event);
    }


}
