package Email.EmailSender.Service;

import Email.EmailSender.domain.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService{
    @Autowired
    JavaMailSender  javaMailSender;
    @Value("${spring.mail.username}")
    private String  sendFrom;
    @Override
    public String sendSimpleEmail(Email email) {
        try {
            SimpleMailMessage   mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sendFrom);
            mailMessage.setSubject(email.getSubject());
            mailMessage.setTo(email.getToEmail());
            mailMessage.setText(email.getBody());
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }catch (Exception e){
            return "Something went wrong while sending mail";

        }
    }
}
