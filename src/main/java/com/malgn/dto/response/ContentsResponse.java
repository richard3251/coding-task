package com.malgn.dto.response;

import com.malgn.domain.entity.Contents;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "콘텐츠 응답")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentsResponse {

    @Schema(description = "콘텐츠 ID", example = "1")
    private Long id;
    
    @Schema(description = "콘텐츠 제목", example = "첫 번째 게시글")
    private String title;
    
    @Schema(description = "콘텐츠 내용", example = "이것은 게시글의 내용입니다.")
    private String description;
    
    @Schema(description = "조회수", example = "10")
    private Long viewCount;
    
    @Schema(description = "생성일시", example = "2026-03-05T10:00:00")
    private LocalDateTime createdDate;
    
    @Schema(description = "생성자", example = "admin")
    private String createdBy;
    
    @Schema(description = "최종 수정일시", example = "2026-03-05T11:00:00")
    private LocalDateTime lastModifiedDate;
    
    @Schema(description = "최종 수정자", example = "admin")
    private String lastModifiedBy;

    public static ContentsResponse from(Contents contents) {
        return ContentsResponse.builder()
            .id(contents.getId())
            .title(contents.getTitle())
            .description(contents.getDescription())
            .viewCount(contents.getViewCount())
            .createdDate(contents.getCreatedDate())
            .createdBy(contents.getCreatedBy())
            .lastModifiedDate(contents.getLastModifiedDate())
            .lastModifiedBy(contents.getLastModifiedBy())
            .build();
    }

}

