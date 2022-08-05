INSERT INTO users (id, email, filename ,name, password,  roles)
VALUES (1,'admin@mail.com', '','admin', '$2a$10$E7mA4hfnlb/C5hMNSXvDwu1t7cBNX68/txVC5BOSChINp8olQFP2O', 'ADMIN');

ALTER SEQUENCE users_seq RESTART WITH 2;