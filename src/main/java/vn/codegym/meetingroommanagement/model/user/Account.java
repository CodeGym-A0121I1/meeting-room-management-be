package vn.codegym.meetingroommanagement.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
//    @Size(min = 1, max = 30, message = "Tên đăng nhập tối đa 30 kí tự")
    private String username;

    //    @Size(min = 6, max = 30, message = "Mật khẩu tối thiểu 6 kí tự và tối đa 30 kí tự")
    private String password;

    @OneToOne(mappedBy = "account")
    @JsonIgnore
    private User user;

    @Enumerated(EnumType.STRING)
    private ERole role;

    public List<GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(role.name()));

        return grantedAuthorityList;
    }
}