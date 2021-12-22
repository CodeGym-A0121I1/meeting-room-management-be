package vn.codegym.meetingroommanagement.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private String departmentId;
    private String departmentName;
    @OneToMany(mappedBy = "department")
    List<User> userList;
}