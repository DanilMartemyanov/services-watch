CREATE TABLE video
(
    id             uuid default uuid_generate_v4() primary key,
    uri            text not null,
    name           text not null,
    likes_amount   int  not null,
    dislikes_amount int  not null
);