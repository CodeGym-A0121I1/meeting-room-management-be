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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userId;
    private String fullName;
    @ManyToOne
    @JoinColumn(name="departmentId",referencedColumnName = "departmentId")
    private Department department;

    @OneToOne
    @JoinColumn(name = "accountId",referencedColumnName = "accountId")
    private Account account;

}