package Email.EmailSender.Service;

import Email.EmailSender.domain.Email;

public interface IEmailService {
    public String sendSimpleEmail(Email email);
}
