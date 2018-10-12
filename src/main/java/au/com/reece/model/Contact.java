package au.com.reece.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class Contact {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^\\d{10}$",message = "Phone number should be a 10 digit number")
    private String phoneNumber;
}
