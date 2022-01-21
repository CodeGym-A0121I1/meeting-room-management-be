package vn.codegym.meetingroommanagement.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import vn.codegym.meetingroommanagement.model.feedback.Feedback;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotEmpty(message = "Họ và tên không được để trống")
    @Size(min = 1, max = 30, message = "Họ và tên tối đa 30 kí tự")
    private String fullName;

    private String email;

    private String phone;

    @ManyToOne
    @JoinColumn
    private Department department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Account account;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<RegistrationHistory> registrationHistoryList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Feedback> feedbackList;
}