CREATE TABLE video
(
    id              uuid default uuid_generate_v4() primary key,
    uri             text not null,
    name            text not null,
    likes_amount    int  not null,
    dislikes_amount int  not null
);

INSERT INTO video (uri, name, likes_amount, dislikes_amount)
VALUES ('https://www.youtube.com/watch?v=KhX3T_NYndo&list=PLaxxU3ZabospOFUVjRWofD-mYOQfCxpzw',
        'Серёга пират - солевар',4500, 100);

INSERT INTO video (uri, name, likes_amount, dislikes_amount)
VALUES ('https://www.youtube.com/watch?v=HxyfAFiLAQg&list=PLaxxU3ZabospOFUVjRWofD-mYOQfCxpzw&index=2',
        'Серёга пират - АМ ФП',4100, 120);

INSERT INTO video (uri, name, likes_amount, dislikes_amount)
VALUES ('https://www.youtube.com/watch?v=p1ozvP4GitY&list=PLaxxU3ZabospOFUVjRWofD-mYOQfCxpzw&index=3',
        'серёга пират - команда виновата',4100, 120);


INSERT INTO video (uri, name, likes_amount, dislikes_amount)
VALUES ('https://www.youtube.com/watch?v=p1ozvP4GitY&list=PLaxxU3ZabospOFUVjRWofD-mYOQfCxpzw&index=4',
        'серёга пират - трек 4',4100, 120);

INSERT INTO video (uri, name, likes_amount, dislikes_amount)
VALUES ('https://www.youtube.com/watch?v=p1ozvP4GitY&list=PLaxxU3ZabospOFUVjRWofD-mYOQfCxpzw&index=5',
        'серёга пират - трек 5',4100, 120);

INSERT INTO video (uri, name, likes_amount, dislikes_amount)
VALUES ('https://www.youtube.com/watch?v=p1ozvP4GitY&list=PLaxxU3ZabospOFUVjRWofD-mYOQfCxpzw&index=6',
        'серёга пират - трек 6',3452, 345);

INSERT INTO video (uri, name, likes_amount, dislikes_amount)
VALUES ('https://www.youtube.com/watch?v=p1ozvP4GitY&list=PLaxxU3ZabospOFUVjRWofD-mYOQfCxpzw&index=7',
        'серёга пират - трек 7',87600, 432);

INSERT INTO video (uri, name, likes_amount, dislikes_amount)
VALUES ('https://www.youtube.com/watch?v=p1ozvP4GitY&list=PLaxxU3ZabospOFUVjRWofD-mYOQfCxpzw&index=8',
        'серёга пират - трек 8',34512, 324);

INSERT INTO video (uri, name, likes_amount, dislikes_amount)
VALUES ('https://www.youtube.com/watch?v=_v-ah9tyXHs',
        'EXILE, STOPBAN, DILBLIN - MiMiMaMaMu (Клип, Бебра 2 Premium)',375001, 9653);

INSERT INTO video (uri, name, likes_amount, dislikes_amount)
VALUES ('https://www.youtube.com/watch?v=bhesOQ9vu5c',
        'BUSTER – Я ТУПОЙ *БЕБРА 2* ft. LIXXX, FRAME TAMER, ROSTIK (ПРЕМЬЕРА КЛИПА!)',798543, 9651);
INSERT INTO video (uri, name, likes_amount, dislikes_amount)
VALUES ('https://www.youtube.com/watch?v=qSKkMHuQd88',
        'Я ВЛЮБИЛСЯ В ТВОЮ МАМУ',54321, 1233);