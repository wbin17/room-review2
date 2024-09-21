package capstone.capstonetest.web.login;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {
    @NotEmpty
    private String memberId;

    @NotEmpty
    private String memberPass;
}
