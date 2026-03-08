package com.malgn.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "로그인 요청")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    @Schema(description = "사용자명", example = "admin")
    @NotBlank(message = "사용자명은 필수입니다")
    private String username;

    @Schema(description = "비밀번호", example = "password123")
    @NotBlank(message = "비밀번호는 필수입니다")
    private String password;

}
