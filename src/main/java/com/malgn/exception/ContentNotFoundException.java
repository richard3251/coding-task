package com.malgn.exception;

public class ContentNotFoundException extends RuntimeException {

    public ContentNotFoundException(String message) {
        super(message);
    }

    public ContentNotFoundException(Long id) {
        super("콘텐츠를 찾을 수 없습니다. ID: " + id);
    }

}
