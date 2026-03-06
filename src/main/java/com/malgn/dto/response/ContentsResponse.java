package com.malgn.dto.response;

import com.malgn.domain.entity.Contents;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentsResponse {

    private Long id;
    private String title;
    private String description;
    private Long viewCount;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastModifiedDate;
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

