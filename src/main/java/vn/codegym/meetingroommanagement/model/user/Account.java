package vn.codegym.meetingroommanagement.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    @Size(min = 1, max = 30, message = "Tên đăng nhập tối đa 30 kí tự")
    private String username;

    @Size(min = 6, max = 30, message = "Mật khẩu tối thiểu 6 kí tự và tối đa 30 kí tự")
    private String password;

    @Enumerated(EnumType.STRING)
    private ERole role;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private User user;

}
