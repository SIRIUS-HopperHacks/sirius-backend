CREATE TABLE tb_user (
    user_code SERIAL PRIMARY KEY,
    user_name VARCHAR(30) NOT NULL,
    user_id VARCHAR(30) NOT NULL UNIQUE,
    user_password VARCHAR(50) NOT NULL,
    user_age INTEGER,
    user_phone INTEGER,
    user_gender VARCHAR(50),
	user_addr1 VARCHAR(100),
    user_addr2 VARCHAR(100),
    user_addr3 VARCHAR(100),
    user_zipcode INTEGER
);

CREATE TABLE tb_authority (
    auth_code SERIAL PRIMARY KEY,
    auth_name VARCHAR(10)
	ON CONFLICT (auth_name) DO NOTHING
);

CREATE TABLE tb_user_authority_conn (
    user_code INTEGER, 
    auth_code INTEGER,
    CONSTRAINT fk_user_conn_user FOREIGN KEY (user_code) REFERENCES tb_user (user_code),
    CONSTRAINT fk_user_conn_auth FOREIGN KEY (auth_code) REFERENCES tb_authority (auth_code)
);

CREATE TABLE tb_board (
    board_code SERIAL PRIMARY KEY,
    board_writer INTEGER, 
    board_title VARCHAR(300) NOT NULL,
    board_content TEXT,
    board_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    board_like INTEGER DEFAULT 0,
    board_dislike INTEGER DEFAULT 0,
    CONSTRAINT fk_board_writer FOREIGN KEY (board_writer) REFERENCES tb_user (user_code)
);

CREATE TABLE tb_comment (
    comment_code SERIAL PRIMARY KEY,
    comment_writer INTEGER,
    comment_board INTEGER,
    comment_content TEXT,
    comment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    comment_like INTEGER DEFAULT 0, 
    comment_dislike INTEGER DEFAULT 0,
    CONSTRAINT fk_comment_board FOREIGN KEY (comment_board) REFERENCES tb_board (board_code),
    CONSTRAINT fk_comment_writer FOREIGN KEY (comment_writer) REFERENCES tb_user (user_code)
);

CREATE TABLE tb_item(
    item_code SERIAL PRIMARY KEY,
    item_name VARCHAR(100) NOT NULL,
    item_description VARCHAR(300),
    item_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    item_info VARCHAR(500)
);

-- 데이터 조회
SELECT * FROM tb_user;
SELECT * FROM tb_authority;
SELECT * FROM tb_user_authority_conn;
SELECT * FROM tb_board;
SELECT * FROM tb_comment;
SELECT * FROM tb_item;

-- 테이블 삭제 (삭제 순서 중요: FK 제약 조건 고려)
DROP TABLE tb_comment;
DROP TABLE tb_board;
DROP TABLE tb_user_authority_conn;
DROP TABLE tb_authority;
DROP TABLE tb_user;
DROP TABLE tb_item;

-- 데이터 삽입
INSERT INTO tb_authority (auth_type) VALUES 
('ADMIN'), 
('MEMBER');

INSERT INTO tb_user (user_name, user_id, user_password, user_addr1, user_addr2, user_addr3, user_zipcode) VALUES 
('ADMIN', 'admin0', 'admin0', '', '', '', 0),
('sample_user1', 'sample1', '1111','서울특별시', '서초구' ,'서초동', 22222),
('sample_user2', 'sample2', '2222','인천광역시', '남동구', '논현동', 33333),
('sample_user3', 'sample3', '3333', '', '', '' ,0);

INSERT INTO tb_user_authority_conn VALUES 
(1,1),
(2,2),
(3,2),
(4,2);

INSERT INTO tb_board (board_writer, board_title, board_content) VALUES
(2, '샘플제목1', '샘플내용1'),
(2, '샘플제목2', '샘플내용2'),
(2, '샘플제목3', '샘플내용3'),
(3, '샘플제목4', '샘플내용4'),
(3, '샘플제목5', '샘플내용5'),
(4, '샘플제목6', '샘플내용6'),
(4, '샘플제목7', '샘플내용7'),
(4, '샘플제목8', '샘플내용8');

INSERT INTO tb_comment (comment_writer, comment_board, comment_content) VALUES
(2,1,'샘플댓글1'),
(2,1,'샘플댓글2'),
(2,2,'샘플댓글3'),
(3,2,'샘플댓글4'),
(3,2,'샘플댓글5'),
(3,3,'샘플댓글6'),
(4,3,'샘플댓글7'),
(4,4,'샘플댓글8'),
(4,5,'샘플댓글9'),
(2,5,'샘플댓글10'),
(2,6,'샘플댓글11'),
(3,7,'샘플댓글12'),
(2,8,'샘플댓글13');

INSERT INTO tb_item (item_name, item_description, item_info) VALUES
('샘플아이템1','샘플아이템1 의 설명', '샘플인포1'),
('샘플아이템2','샘플아이템2 의 설명', '샘플인포2'),
('샘플아이템3','샘플아이템3 의 설명', '샘플인포3'),
('샘플아이템4','샘플아이템4 의 설명', '샘플인포4'),
('샘플아이템5','샘플아이템5 의 설명', '샘플인포5');
