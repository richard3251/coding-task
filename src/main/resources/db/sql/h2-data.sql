-- 사용자 데이터 삽입 (비밀번호: password123)
-- BCrypt 해시 값: $2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi
INSERT INTO users (username, password, role) VALUES
('admin', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi', 'ADMIN'),
('user1', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi', 'USER'),
('user2', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi', 'USER');

-- 샘플 콘텐츠 데이터 삽입
INSERT INTO contents (title, description, view_count, created_date, created_by, last_modified_date, last_modified_by) VALUES
('첫 번째 게시글', '이것은 첫 번째 게시글의 내용입니다.', 0, CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin'),
('두 번째 게시글', '이것은 두 번째 게시글의 내용입니다.', 0, CURRENT_TIMESTAMP(), 'user1', CURRENT_TIMESTAMP(), 'user1'),
('세 번째 게시글', '이것은 세 번째 게시글의 내용입니다.', 0, CURRENT_TIMESTAMP(), 'user1', CURRENT_TIMESTAMP(), 'user1'),
('네 번째 게시글', '이것은 네 번째 게시글의 내용입니다.', 0, CURRENT_TIMESTAMP(), 'user2', CURRENT_TIMESTAMP(), 'user2'),
('다섯 번째 게시글', '이것은 다섯 번째 게시글의 내용입니다.', 0, CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin');
