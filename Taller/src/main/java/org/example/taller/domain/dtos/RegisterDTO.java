package org.example.taller.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterDTO {
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9\\s]{5,20}$")
    private String username;
    @NotEmpty
    @Pattern(regexp = "^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$")
    private String email;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9\\s]{5,20}$")
    private String password;
}
