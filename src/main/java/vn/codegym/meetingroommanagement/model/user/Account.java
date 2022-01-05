package vn.codegym.meetingroommanagement.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    private String username;

    private String password;

    @OneToOne(mappedBy = "account")
    private User user;

    @Enumerated(EnumType.STRING)
    private ERole role;


}