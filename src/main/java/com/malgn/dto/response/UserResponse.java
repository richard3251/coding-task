package com.malgn.dto.response;

import com.malgn.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "사용자 정보 응답")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    @Schema(description = "사용자 ID", example = "1")
    private Long id;
    
    @Schema(description = "사용자명", example = "newuser")
    private String username;
    
    @Schema(description = "권한", example = "USER")
    private String role;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }

}
