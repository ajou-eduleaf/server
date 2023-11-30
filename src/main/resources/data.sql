INSERT INTO academy (name)
VALUES ('academy'),
       ('eduleaf'),
       ('ajoucoding');

INSERT INTO teacher (name, teach_id, teach_pw, academy_id)
VALUES ('나선생', 'nah123', 'nah123!', 1), -- 1 (teacher_id)
       ('안정섭', 'ajs', '1234', 1),       -- 2
       ('김상훈', 'shk', '1234', 1), -- 3
       ('강경란', 'kang', 'kang1234', 2), -- 4
       ('정현지', 'jhz', 'jhz0011', 2), -- 5
       ('장원영', 'jwy', 'jwy1234', 3), -- 6
       ('김민주', 'kmj', 'kmj1234', 3); -- 7

INSERT INTO group_table (name, academy_id, teacher_id)
VALUES ('코뿔소', 1, 1), -- 1 (group_id)
       ('병아리', 1, 2), -- 2
       ('불사조', 1, 3), -- 3
       ('초등A반', 2, 4), -- 4
       ('초등B반', 2, 5), -- 5
       ('중등A반', 3, 6), -- 6
       ('중등B반', 3, 7); -- 7


INSERT INTO student (age, boj_id, name, student_id, student_pw, academy_id, group_id)
VALUES (12, 'sfg1234', '소학생', 'sosongha3', 'ss123!', 1, 3), -- 1  (student_id)
       (14, 'sdf12g4', '이학생', 'sshoon333', 'ss123!', 1, 3), -- 2
       (13, 'adfjk1', '김하이', 'shshs123', 'ss123!', 1, 3),   -- 3
       (12, 'sfkdj134', '최하염', 'choi123', 'ss123!', 1, 3),  -- 4
       (14, 'sangjuntest', '박상준', 'sangjun', '1234', 1, 2), -- 5
       (15, 'jhw8571', '김나박', 'kimnapark', '1234', 1, 2); -- 6



INSERT INTO parent (name, parent_id, parent_pw, relation, student_id, phone_number)
VALUES ('학생맘', 'studentmom', '1234', '모', 1, '010-1111-222'),
       ('상준맘', 'sangjunmom', '1234', '모', 5, '010-1234-5678'),
       ('하염아빠', 'hayeomdad', '1234', '부', 4, '010-3333-4444'),
       ('나박맘', 'naparkmom', '1234', '모', 6, '010-0000-1111');

INSERT INTO problem (problem_no)
VALUES (1000),
       (1001),
       (1002),
       (1003),
       (1004),
       (1005),
       (1006),
       (1007),
       (9498),
       (1330),
       (10172),
       (10171),
       (11382),
       (2588),
       (2557);


INSERT INTO lesson (content, date, group_id)
VALUES ('파이썬', '2023-12-01', 2), -- 1 (lesson_id)
       ('파이썬', '2023-12-08', 2), -- 2
       ('파이썬', '2023-12-15', 2), -- 3
       ('파이썬', '2023-12-22', 2), -- 4
       ('C언어', '2023-12-02', 3), -- 5
       ('C언어', '2023-12-09', 3), -- 6
       ('C언어', '2023-12-16', 3), -- 7
       ('C언어', '2023-12-23', 3), -- 8
       ('JAVA', '2023-12-03', 1), -- 9
       ('JAVA', '2023-12-10', 1), -- 10
       ('JAVA', '2023-12-17', 1), -- 11
       ('JAVA', '2023-12-24', 1); -- 12

INSERT INTO lesson_problem (lesson_id, problem_id)
VALUES (1, 1000),
       (1, 1001),
       (1, 1002),
       (1, 1003),
       (1, 1004),
       (2, 2557),
       (2, 1005),
       (2, 1006),
       (2, 1007);

INSERT INTO student_lesson (lesson_id, student_id, attendance)
VALUES (1, 5, true), (1, 6, true),
       (2, 5, false), (2, 6, false),
       (3, 5, false), (3, 6, false),
       (4, 5, false), (4, 6, false),
       (5, 5, false), (5, 6, false),
       (6, 5, false), (6, 6, false),
       (7, 5, false), (7, 6, false),
       (8, 5, false), (8, 6, false);
