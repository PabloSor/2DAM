insert into user_entity (username, password, created_at) values ('Pablo', '$2y$10$DU4b3eG4b49L/vDZfwawdO2OIz4nhTDXb5iWnKHsflh4cjKs4c6e2', '2024-01-21 14:57:21')
insert into user_entity (username, password, created_at) values ('Admin', '$2y$10$YzOtlQeurueBc3W1oxvPWuu5bu4xh8eLSjTbiP4ml.FUUSSprZvK2', '2023-01-21 12:21:01')

insert into user_entity_roles (user_entity_id, roles) values (1, 'USER')
insert into user_entity_roles (user_entity_id, roles) values (2, 'ADMIN')