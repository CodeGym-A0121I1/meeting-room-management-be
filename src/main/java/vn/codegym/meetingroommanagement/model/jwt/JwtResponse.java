package vn.codegym.meetingroommanagement.model.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.codegym.meetingroommanagement.model.user.Account;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String jwtToken;
    private Optional<Account> account;
    private String status;
}
