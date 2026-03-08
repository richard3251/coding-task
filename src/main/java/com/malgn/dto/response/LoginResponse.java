package com.malgn.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "로그인 응답")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    @Schema(description = "JWT 액세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String accessToken;
    
    @Schema(description = "토큰 타입", example = "Bearer")
    private String tokenType;
    
    @Schema(description = "사용자명", example = "admin")
    private String username;
    
    @Schema(description = "권한", example = "ADMIN")
    private String role;

}
