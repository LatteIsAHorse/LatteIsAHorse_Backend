INSERT INTO USER VALUES (1, '서울대', 'seoul@naver.com', 2, '2021-11-10 18:35:21.632145', 0, '오늘저녁은치킨이닭','{bcrypt}$2a$10$qfRVPI30zDA2YAwjV2V/iONNjayz7Kf.j1Ca4Xwh/KpsPJ.p5jz1S','010-1234-5678','ROLE_USER',1);
INSERT INTO USER VALUES (2, '연세대', 'yeonse@naver.com', 3, '2021-11-11 13:12:11.32145', 0, '굳어버린시조새','{bcrypt}$2a$10$qfRVPI30zDA2YAwjV2V/iONNjayz7Kf.j1Ca4Xwh/KpsPJ.p5jz1S','010-2345-5678','ROLE_USER',1);

INSERT INTO QUIZ VALUES (1, '2021-11-10 19:00:00.342675', '2021-11-10 19:00:00.342675', 1, '서울대 부지의 너비는 여의도 면적의 약 몇배일까?', 2, '오늘저녁은치킨이닭', 1);
INSERT INTO QUIZ VALUES (2, '2021-11-11 13:15:00.123455', '2021-11-11 13:15:00.123455', 1, '우리학교에는 비건을 위한 식당이 있다?없다?', 1, '굳어버린시조새', 1);

INSERT INTO TAG VALUES (1, '서울대');
INSERT INTO TAG VALUES (2, '땅 면적');

INSERT INTO QUIZ_TAG VALUES (1, 1, 1);
INSERT INTO QUIZ_TAG VALUES (2, 1, 2);