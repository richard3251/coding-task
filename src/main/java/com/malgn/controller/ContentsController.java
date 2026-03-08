package com.malgn.controller;

import com.malgn.dto.request.ContentsCreateRequest;
import com.malgn.dto.request.ContentsUpdateRequest;
import com.malgn.dto.response.ApiResponse;
import com.malgn.dto.response.ContentsResponse;
import com.malgn.service.ContentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "콘텐츠", description = "콘텐츠 관리 API")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contents")
public class ContentsController {

    private final ContentsService contentsService;

    @Operation(summary = "콘텐츠 생성", description = "새로운 콘텐츠를 생성합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "콘텐츠 생성 성공",
                    content = @Content(schema = @Schema(implementation = ContentsResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "입력값 검증 실패"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "인증 실패"
            )
    })
    @PostMapping
    public ResponseEntity<ApiResponse<ContentsResponse>> createContents(
            @Valid @RequestBody ContentsCreateRequest request) {
        ContentsResponse response = contentsService.createContents(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("콘텐츠가 생성되었습니다", response));
    }

    @Operation(summary = "콘텐츠 목록 조회", description = "페이징된 콘텐츠 목록을 조회합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "조회 성공"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "인증 실패"
            )
    })
    @GetMapping
    public ResponseEntity<ApiResponse<Page<ContentsResponse>>> getAllContents(
            @Parameter(description = "페이지 번호 (0부터 시작), 크기, 정렬 기준")
            @PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ContentsResponse> response = contentsService.getAllContents(pageable);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(summary = "콘텐츠 상세 조회", description = "특정 콘텐츠를 조회합니다. 조회 시 조회수가 1 증가합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ContentsResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "인증 실패"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "콘텐츠를 찾을 수 없음"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ContentsResponse>> getContentsById(
            @Parameter(description = "콘텐츠 ID", required = true)
            @PathVariable Long id) {
        ContentsResponse response = contentsService.getContentsById(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(summary = "콘텐츠 수정", description = "콘텐츠를 수정합니다. 생성자 본인 또는 ADMIN만 수정 가능합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "수정 성공",
                    content = @Content(schema = @Schema(implementation = ContentsResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "입력값 검증 실패"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "인증 실패"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "수정 권한 없음"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "콘텐츠를 찾을 수 없음"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ContentsResponse>> updateContents(
            @Parameter(description = "콘텐츠 ID", required = true)
            @PathVariable Long id,
            @Valid @RequestBody ContentsUpdateRequest request) {
        ContentsResponse response = contentsService.updateContents(id, request);
        return ResponseEntity.ok(ApiResponse.success("콘텐츠가 수정되었습니다", response));
    }

    @Operation(summary = "콘텐츠 삭제", description = "콘텐츠를 삭제합니다. 생성자 본인 또는 ADMIN만 삭제 가능합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "삭제 성공"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "인증 실패"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "삭제 권한 없음"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "콘텐츠를 찾을 수 없음"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteContents(
            @Parameter(description = "콘텐츠 ID", required = true)
            @PathVariable Long id) {
        contentsService.deleteContents(id);
        return ResponseEntity.ok(ApiResponse.success("콘텐츠가 삭제되었습니다", null));
    }

}
