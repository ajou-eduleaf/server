INSERT INTO academy (name) VALUES ('eduleaf');
INSERT INTO teacher (name, teach_id, teach_pw, academy_id) VALUES ('나선생', 'nah123', 'nah123!', 1);
INSERT INTO group_table (name, academy_id, teacher_id) VALUES ('math', 1, 1);
INSERT INTO student (age, boj_id, name, student_id, student_pw, academy_id, group_id) VALUES (12, 'sfg1234', '소학생', 'sosongha3', 'ss123!', 1, 1), (14, 'sdf12g4', '이학생', 'sshoon333', 'ss123!', 1, 1), (13, 'adfjk1', '김하이', 'shshs123', 'ss123!', 1, 1), (12, 'sfkdj134', '최하염', 'choi123', 'ss123!', 1, 1);
INSERT INTO problem (problem_no) VALUES (1),(2),(3),(4),(12345);
INSERT INTO student_problem (problem_id, student_id, solved_date) VALUES (1, 1, '2023-10-27 00:00:00.000000'),(2, 1, '2023-10-27 00:00:00.000000'),(3, 1, '2023-10-27 00:00:00.000000'),(4, 1, '2023-10-27 00:00:00.000000'),(12345, 1, '2023-10-27 00:00:00.000000'),(1, 2, '2023-10-27 00:00:00.000000'),(2, 2, '2023-10-27 00:00:00.000000'),(3, 2, '2023-10-27 00:00:00.000000'),(1, 3, '2023-10-27 00:00:00.000000'),(2, 3, '2023-10-27 00:00:00.000000'),(3, 3, '2023-10-27 00:00:00.000000'),(1, 4, '2023-10-27 00:00:00.000000'),(2, 4, '2023-10-27 00:00:00.000000');