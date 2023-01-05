package registration.registrationsystem.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationGroup {

    @Id
//    @GeneratedValue
    private long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Student> students;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "groupId")
    private List<AcademicBlock> academicBlocks;

}
