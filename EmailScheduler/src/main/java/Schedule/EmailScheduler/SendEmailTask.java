package Schedule.EmailScheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SendEmailTask {
    @Autowired
    SchedulerService    schedulerService;
    @Scheduled(fixedRate = 30000)
    public void sendEmail(){
        schedulerService.sendEmail(1);
        System.out.println("Email sent ....");
    }
}
