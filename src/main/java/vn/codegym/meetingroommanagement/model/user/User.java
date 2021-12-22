package vn.codegym.meetingroommanagement.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(generator = "userIdGen")
    @GenericGenerator(
            name = "userIdGen",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "U"),
            strategy = "vn.codegym.meetingroommanagement.utils.IdGenerator"
    )
    private String id;

    private String fullName;

    @ManyToOne
    @JoinColumn
    private Department department;

    @OneToOne
    @JoinColumn
    private Account account;

    @OneToMany(mappedBy = "user")
    List<RegistrationHistory> registrationHistoryList;
}