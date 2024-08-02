package bg.softuni.website.models.dtos;

import bg.softuni.website.util.validation.MatchRegisterPasswords;
import bg.softuni.website.util.validation.UniqueRegisterEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MatchRegisterPasswords(message = "{passwords.dont.match}")
public class RegisterDto {
    
    @NotBlank(message = "{first.name.not.blank}")
    @Pattern(regexp = "[A-Za-z ]*", message = "{first.name.must.contain.only.letters}")
    @Size(min = 2, message = "{first.name.too.short}")
    @Size(max = 19, message = "{first.name.too.long}")
    private String firstName;
    
    @NotBlank(message = "{last.name.not.blank}")
    @Pattern(regexp = "[A-Za-z ]*", message = "{last.name.must.contain.only.letters}")
    @Size(min = 2, message = "{last.name.too.short}")
    @Size(max = 20, message = "{last.name.too.long}")
    private String lastName;
    
    @Pattern(regexp = "^\\w{2,}+@\\w{2,}\\.\\w{2,}$", message = "{email.invalid.pattern}")
    @UniqueRegisterEmail(message = "{email.already.taken}")
    private String email;
    
    @Pattern(regexp = "^\\S+$", message = "{password.cannot.contain.whitespaces}")
    @Size(min = 4, message = "{password.too.short}")
    @Size(max = 30, message = "{password.too.long}")
    private String password;
    
    private String confirmPassword;
    
}
