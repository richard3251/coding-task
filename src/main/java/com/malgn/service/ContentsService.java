package com.malgn.service;

import com.malgn.domain.entity.Contents;
import com.malgn.domain.enums.UserRole;
import com.malgn.domain.repository.ContentsRepository;
import com.malgn.dto.request.ContentsCreateRequest;
import com.malgn.dto.request.ContentsUpdateRequest;
import com.malgn.dto.response.ContentsResponse;
import com.malgn.exception.ContentNotFoundException;
import com.malgn.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContentsService {

    private final ContentsRepository contentsRepository;

    @Transactional
    public ContentsResponse createContents(ContentsCreateRequest request) {
        Contents contents = Contents.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .viewCount(0L)
            .build();

        Contents savedContents = contentsRepository.save(contents);
        log.info("Contents created: id={}, title={}", savedContents.getId(), savedContents.getTitle());

        return ContentsResponse.from(savedContents);
    }

    @Transactional(readOnly = true)
    public Page<ContentsResponse> getAllContents(Pageable pageable) {
        Page<Contents> contentsPage = contentsRepository.findAll(pageable);
        return contentsPage.map(ContentsResponse::from);
    }

    @Transactional
    public ContentsResponse getContentsById(Long id) {
        Contents contents = contentsRepository.findById(id)
            .orElseThrow(() -> new ContentNotFoundException(id));

        contents.incrementViewCount();
        contentsRepository.save(contents);

        log.info("Contents viewed: id={}, viewCount={}", contents.getId(), contents.getViewCount());

        return ContentsResponse.from(contents);
    }

    @Transactional
    public ContentsResponse updateContents(Long id, ContentsUpdateRequest request) {
        Contents contents = contentsRepository.findById(id)
            .orElseThrow(() -> new ContentNotFoundException(id));

        checkUpdatePermission(contents);

        contents.setTitle(request.getTitle());
        contents.setDescription(request.getDescription());

        Contents updatedContents = contentsRepository.save(contents);
        log.info("Contents updated: id={}, title={}", updatedContents.getId(), updatedContents.getTitle());

        return ContentsResponse.from(updatedContents);
    }

    @Transactional
    public void deleteContents(Long id) {
        Contents contents = contentsRepository.findById(id)
            .orElseThrow(() -> new ContentNotFoundException(id));

        checkUpdatePermission(contents);

        contentsRepository.delete(contents);
        log.info("Contents deleted: id={}", id);
    }

    private void checkUpdatePermission(Contents contents) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .anyMatch(role -> role.equals("ROLE_" + UserRole.ADMIN.name()));

        if (!isAdmin && !contents.getCreatedBy().equals(currentUsername)) {
            throw new UnauthorizedException("해당 콘텐츠를 수정/삭제할 권한이 없습니다");
        }
    }

}
