package Email.EmailSender.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailInfo {
    private List<String> emails;
    private List<String> names;
    private String   startDate;
    private String   endDate;




}
