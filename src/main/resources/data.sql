INSERT INTO USER VALUES (1, '서울대', 'seoul@naver.com', 2, '2021-11-10 18:35:21.632145', 0, '오늘저녁은치킨이닭','{bcrypt}$2a$10$qfRVPI30zDA2YAwjV2V/iONNjayz7Kf.j1Ca4Xwh/KpsPJ.p5jz1S','010-1234-5678','ROLE_USER',1);
INSERT INTO USER VALUES (2, '연세대', 'yeonse@naver.com', 3, '2021-11-11 13:12:11.32145', 0, '굳어버린시조새','{bcrypt}$2a$10$qfRVPI30zDA2YAwjV2V/iONNjayz7Kf.j1Ca4Xwh/KpsPJ.p5jz1S','010-2345-5678','ROLE_USER',1);
INSERT INTO USER VALUES (3, '연세대', 'yeonse2@naver.com', 3, '2021-11-09 12:11:11.73435', 0, '뚱인데요','{bcrypt}$2a$10$qfRVPI30zDA2YAwjV2V/iONNjayz7Kf.j1Ca4Xwh/KpsPJ.p5jz1S','010-4444-9999','ROLE_USER',1);
INSERT INTO USER VALUES (4, '연세대', 'yeonse3@naver.com', 3, '2021-11-06 06:22:44.24465', 0, '연세우유','{bcrypt}$2a$10$qfRVPI30zDA2YAwjV2V/iONNjayz7Kf.j1Ca4Xwh/KpsPJ.p5jz1S','010-5438-2123','ROLE_USER',1);



INSERT INTO QUIZ VALUES (1, '2021-11-10 19:00:00.342675', '2021-11-10 19:00:00.342675', 1, '서울대 부지의 너비는 여의도 면적의 약 몇배일까?', 2, '오늘저녁은치킨이닭', 1);
INSERT INTO QUIZ VALUES (2, '2021-11-11 13:15:00.123455', '2021-11-11 13:15:00.123455', 1, '우리학교에는 비건을 위한 식당이 있다?없다?', 1, '굳어버린시조새', 2);

INSERT INTO ANSWER VALUES (1, '같다', 1);
INSERT INTO ANSWER VALUES (2, '2배', 1);
INSERT INTO ANSWER VALUES (3, '5배', 1);
INSERT INTO ANSWER VALUES (4, '7배', 1);
INSERT INTO ANSWER VALUES (5, '있다', 2);
INSERT INTO ANSWER VALUES (6, '없다', 2);

INSERT INTO TAG VALUES (1, '서울대');
INSERT INTO TAG VALUES (2, '땅 면적');

INSERT INTO QUIZ_TAG VALUES (1, 1, 1);
INSERT INTO QUIZ_TAG VALUES (2, 1, 2);

INSERT INTO COMMENT VALUES (1, '2021-11-11 13:56:12.185','2021-11-11 13:56:12.185','연세대에 비건 식당이 있나 궁금해서 함 찾아봤는데 진짜 있네ㄷㄷ', '오늘저녁은치킨이닭', null, 2, 1);
INSERT INTO COMMENT VALUES (2, '2021-11-11 13:59:10.111','2021-11-11 13:59:10.111','나 연대생 맞음? 저기 왜 한번도 본적이 없냐?', '뚱인데요', null, 2, 3);

INSERT INTO REPLY VALUES (1, '2021-11-11 14:01:07.234', '2021-11-11 14:01:07.234', '저기 국제 학사 뒤편에 있음ㅋㅋ 그쪽은 사람들이 잘 안다녀서리 모르는 사람이 더 많을듯', '연세우유', 2, 4);
INSERT INTO REPLY VALUES (2, '2021-11-11 18:04:52.341', '2021-11-11 18:04:52.341', '1번 댓글에 대한 답글 테스트', '오늘저녁은치킨이닭', 1, 1);
INSERT INTO REPLY VALUES (3, '2021-11-11 18:05:28.965', '2021-11-11 18:05:28.965', '2번 댓글에 대한 답글 테스트', '오늘저녁은치킨이닭', 2, 1);

