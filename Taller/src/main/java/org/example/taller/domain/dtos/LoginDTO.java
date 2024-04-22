package org.example.taller.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDTO {
    @NotEmpty
    private String usernameoremail;
    @NotEmpty
    private String password;
}
