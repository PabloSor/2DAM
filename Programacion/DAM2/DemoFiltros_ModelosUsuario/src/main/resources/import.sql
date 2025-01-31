insert into user_entity (username, password, created_at) values ('Pablo', '$2y$10$1KiFIm0/jdj0JgbhISnEAuJrYAPPb90vn7vVfI30cNItM7Lo7bbuq', '2024-01-21 14:57:21')
insert into user_entity (username, password, created_at) values ('Admin', '$2y$10$1KiFIm0/jdj0JgbhISnEAuJrYAPPb90vn7vVfI30cNItM7Lo7bbuq', '2023-01-21 12:21:01')

insert into user_entity_roles (user_entity_id, roles) values (1, 'USER')
insert into user_entity_roles (user_entity_id, roles) values (2, 'ADMIN')