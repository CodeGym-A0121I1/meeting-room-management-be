package vn.codegym.meetingroommanagement.model.user;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String accountId;
    private String username;
    private String password;
    @OneToOne(mappedBy = "account")
    private User user;
    @ManyToOne
    @JoinColumn(name="roleId",referencedColumnName = "roleId")
    private Role role;


}