package com.malgn.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "콘텐츠 생성 요청")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentsCreateRequest {

    @Schema(description = "콘텐츠 제목 (최대 100자)", example = "새로운 게시글")
    @NotBlank(message = "제목은 필수입니다")
    @Size(max = 100, message = "제목은 100자 이내로 입력해주세요")
    private String title;

    @Schema(description = "콘텐츠 내용", example = "이것은 게시글의 상세 내용입니다.")
    private String description;

}
