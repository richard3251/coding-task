package com.malgn.controller;

import com.malgn.dto.request.ContentsCreateRequest;
import com.malgn.dto.request.ContentsUpdateRequest;
import com.malgn.dto.response.ApiResponse;
import com.malgn.dto.response.ContentsResponse;
import com.malgn.service.ContentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contents")
public class ContentsController {

    private final ContentsService contentsService;

    @PostMapping
    public ResponseEntity<ApiResponse<ContentsResponse>> createContents(
        @Valid @RequestBody ContentsCreateRequest request) {
        ContentsResponse response = contentsService.createContents(request);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ApiResponse.success("콘텐츠가 생성되었습니다", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ContentsResponse>>> getAllContents(
        @PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ContentsResponse> response = contentsService.getAllContents(pageable);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ContentsResponse>> getContentsById(@PathVariable Long id) {
        ContentsResponse response = contentsService.getContentsById(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ContentsResponse>> updateContents(
        @PathVariable Long id,
        @Valid @RequestBody ContentsUpdateRequest request) {
        ContentsResponse response = contentsService.updateContents(id, request);
        return ResponseEntity.ok(ApiResponse.success("콘텐츠가 수정되었습니다", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteContents(@PathVariable Long id) {
        contentsService.deleteContents(id);
        return ResponseEntity.ok(ApiResponse.success("콘텐츠가 삭제되었습니다", null));
    }

}
