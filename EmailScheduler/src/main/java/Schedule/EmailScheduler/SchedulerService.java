package Schedule.EmailScheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class SchedulerService {
    @Autowired
    RegistrationEventFeignClient    registrationEventFeignClient;
    public void sendEmail(long id){
        registrationEventFeignClient.send(id);
    }

    @FeignClient(name = "registrationEvent-service", url = "http://localhost:8080")
    interface RegistrationEventFeignClient {
        @RequestMapping("/registration-events/sendEmail/{id}")
        public void send(@PathVariable("id") long id); }
}
