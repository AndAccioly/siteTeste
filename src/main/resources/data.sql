CREATE TABLE AUTHORITIES (
    USERNAME VARCHAR2(128) NOT NULL,
    AUTHORITY VARCHAR2(128) NOT NULL
);

ALTER TABLE AUTHORITIES ADD CONSTRAINT AUTHORITIES_UNIQUE UNIQUE (USERNAME, AUTHORITY);

ALTER TABLE AUTHORITIES ADD FOREIGN KEY (USERNAME) REFERENCES USERS (USERNAME);

INSERT INTO USERS(email, username, password, id, enabled, total_horas_jogadas) VALUES ('adm@adm.com', 'admin', '$2a$10$EpklFQLmX4RY/IPQC1kZZOh5vQyTbqdyH2KARHJhZRBA8SB4RrZU2', 1, true, 94);
INSERT INTO AUTHORITIES(username, authority) VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO USERS(email, username, password, id, enabled, total_horas_jogadas) VALUES ('andre@hotmail.com', 'andre', '$2a$10$EpklFQLmX4RY/IPQC1kZZOh5vQyTbqdyH2KARHJhZRBA8SB4RrZU2', 2, true, 0);
INSERT INTO AUTHORITIES(username, authority) VALUES ('andre', 'ROLE_USER');

INSERT INTO USERS(email, username, password, id, enabled, total_horas_jogadas) VALUES ('red@red.com', 'red', '$2a$10$EpklFQLmX4RY/IPQC1kZZOh5vQyTbqdyH2KARHJhZRBA8SB4RrZU2', 3, true, 0);
INSERT INTO AUTHORITIES(username, authority) VALUES ('red', 'ROLE_REDATOR');

INSERT INTO JOGO(nome, descricao, nota, publisher, dt_lancamento, horas_zeramento) VALUES ('Uncharted 1', 'Corre e atira menos', 6.0, 'Sony', '2010-10-11', 15);
INSERT INTO JOGO(nome, descricao, nota, publisher, dt_lancamento, horas_zeramento) VALUES ('Uncharted 2', 'Corre e atira mais', 7.00, 'Sony', '2010-11-10', 10);
INSERT INTO JOGO(nome, descricao, nota, publisher, dt_lancamento, horas_zeramento) VALUES ('Uncharted 3', 'Corre e atira muito', 8.00, 'Sony', '2010-10-10', 12);
INSERT INTO JOGO(nome, descricao, nota, publisher, dt_lancamento, horas_zeramento) VALUES ('Uncharted 4', 'Corre e atira muito mais', 10.00, 'Sony', '2004-10-10', 18);
INSERT INTO JOGO(nome, descricao, nota, publisher, dt_lancamento, horas_zeramento) VALUES ('Resident Evil 7', 'Mata zumbi', 8.00, 'Capcom', '2005-10-10', 14);
INSERT INTO JOGO(nome, descricao, nota, publisher, dt_lancamento, horas_zeramento) VALUES ('Fifa 57', 'Mais do mesmo', 0.00, 'EA Games', '2010-10-10', 50);
INSERT INTO JOGO(nome, descricao, nota, publisher, dt_lancamento, horas_zeramento) VALUES ('Counter Strike', 'Mais Tiro', 0.00, 'Valve', '2010-10-10', 50);
INSERT INTO JOGO(nome, descricao, nota, publisher, dt_lancamento, horas_zeramento) VALUES ('Resident Evil 8', 'Um jogo novo focado em não matar zumbi, agora é preciso caçar lobisomens e bruxas.', 10.00, 'Capcom', '2021-07-21', 10);
INSERT INTO JOGO(nome, descricao, nota, publisher, dt_lancamento, horas_zeramento) VALUES ('Resident Evil 4', 'Mata zumbi bom', 9.80, 'Capcom', '2005-10-10', 14);
INSERT INTO JOGO(nome, descricao, nota, publisher, dt_lancamento, horas_zeramento) VALUES ('Super Mario', 'Plataforma salva princesa', 7.00, 'Nintendo', '2015-12-25', 6);
INSERT INTO JOGO(nome, descricao, nota, publisher, dt_lancamento, horas_zeramento) VALUES ('God of War 4', 'Bom da guerra contra os nórdicos', 9.65, 'Santa Monica', '2018-09-14', 22);
INSERT INTO JOGO(nome, descricao, nota, publisher, dt_lancamento, horas_zeramento) VALUES ('Warzone', 'Jogo de tiro e de guerra', 6.50, 'Activision', '2011-05-30', 35);

INSERT INTO CATEGORIA(nome) VALUES ('Terror');--1
INSERT INTO CATEGORIA(nome) VALUES ('Acao');--2
INSERT INTO CATEGORIA(nome) VALUES ('Aventura');--3
INSERT INTO CATEGORIA(nome) VALUES ('Esporte');--4
INSERT INTO CATEGORIA(nome) VALUES ('Tiro'); --5
INSERT INTO CATEGORIA(nome) VALUES ('Simulador');--6
INSERT INTO CATEGORIA(nome) VALUES ('MMO');--7
INSERT INTO CATEGORIA(nome) VALUES ('RPG');--8
INSERT INTO CATEGORIA(nome) VALUES ('Singleplayer');--9
INSERT INTO CATEGORIA(nome) VALUES ('Multiplayer'); --10
INSERT INTO CATEGORIA(nome) VALUES ('Cooperativo');--11
INSERT INTO CATEGORIA(nome) VALUES ('Plataforma');--12

INSERT INTO PLATAFORMA(nome) VALUES ('PC');--1
INSERT INTO PLATAFORMA(nome) VALUES ('Nintendo');--2
INSERT INTO PLATAFORMA(nome) VALUES ('Xbox');--3
INSERT INTO PLATAFORMA(nome) VALUES ('Playstation');--4
INSERT INTO PLATAFORMA(nome) VALUES ('Mobile');--5
INSERT INTO PLATAFORMA(nome) VALUES ('Tudo');--6

INSERT INTO CONSOLE(nome, plataforma_id) VALUES ('PC', 1);
INSERT INTO CONSOLE(nome, plataforma_id) VALUES ('Ps2', 4);
INSERT INTO CONSOLE(nome, plataforma_id) VALUES ('Ps3', 4);
INSERT INTO CONSOLE(nome, plataforma_id) VALUES ('Ps4', 4);
INSERT INTO CONSOLE(nome, plataforma_id) VALUES ('Series S', 3);
INSERT INTO CONSOLE(nome, plataforma_id) VALUES ('Series X', 3);
INSERT INTO CONSOLE(nome, plataforma_id) VALUES ('Switch', 2);
INSERT INTO CONSOLE(nome, plataforma_id) VALUES ('3ds', 2);
INSERT INTO CONSOLE(nome, plataforma_id) VALUES ('Android', 5);
INSERT INTO CONSOLE(nome, plataforma_id) VALUES ('IOs', 5);

INSERT INTO USUARIO_JOGOS(usuario_id, jogos_id) VALUES (1, 1);
INSERT INTO USUARIO_JOGOS(usuario_id, jogos_id) VALUES (1, 4);
INSERT INTO USUARIO_JOGOS(usuario_id, jogos_id) VALUES (1, 5);
INSERT INTO USUARIO_JOGOS(usuario_id, jogos_id) VALUES (1, 7);
INSERT INTO USUARIO_JOGOS(usuario_id, jogos_id) VALUES (1, 11);

INSERT INTO USUARIO_JOGOS(usuario_id, jogos_id) VALUES (2, 2);
INSERT INTO USUARIO_JOGOS(usuario_id, jogos_id) VALUES (2, 3);
INSERT INTO USUARIO_JOGOS(usuario_id, jogos_id) VALUES (2, 6);

--Uncharted 1
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (1, 2);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (1, 3);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (1, 5);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (1, 9);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (1, 4);
--Uncharted 2
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (2, 2);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (2, 3);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (2, 5);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (2, 9);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (2, 4);
--Uncharted 3
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (3, 2);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (3, 3);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (2, 5);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (3, 9);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (3, 4);
--Uncharted 4
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (4, 2);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (4, 3);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (4, 5);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (4, 9);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (4, 10);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (4, 4);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (4, 1);
--Resident Evil 7
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (5, 1);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (5, 3);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (5, 5);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (5, 9);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (5, 1);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (5, 3);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (5, 4);
--Fifa 57
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (6, 4);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (6, 9);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (6, 10);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (6, 3);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (6, 4);
--Counter Strike
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (7, 5);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (7, 10);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (7, 1);
--Resident Evil 8
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (8, 1);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (8, 3);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (8, 5);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (8, 9);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (8, 10);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (8, 3);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (8, 4);
--Resident Evil 4
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (9, 1);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (9, 3);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (9, 5);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (9, 9);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (9, 3);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (9, 4);
--SuperMario
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (10, 3);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (10, 9);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (10, 11);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (10, 2);
--God of Was 4
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (11, 2);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (11, 3);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (11, 9);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (11, 4);
--Warzone
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (12, 2);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (12, 5);
INSERT INTO JOGO_CATEGORIAS(jogo_id, categorias_id) VALUES (12, 10);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (12, 1);
INSERT INTO JOGO_PLATAFORMAS(jogo_id, plataformas_id) VALUES (12, 3);


INSERT INTO AV_JOGO(id, usuario_id, jogo_id, nota, review, horas_Jogadas) VALUES(1, 1, 1, 8, 'Jogo top', 12);
INSERT INTO AV_JOGO(id, usuario_id, jogo_id, nota, review, horas_Jogadas) VALUES(2, 1, 4, 10, 'Jogo top+', 24);
INSERT INTO AV_JOGO(id, usuario_id, jogo_id, nota, review, horas_Jogadas) VALUES(3, 1, 5, 4, 'Jogo top++', 50);
INSERT INTO AV_JOGO(id, usuario_id, jogo_id, nota, review, horas_Jogadas) VALUES(4, 1, 6, 5, 'Jogo top+++', 8);


INSERT INTO NOTICIA(id, conteudo, data_publicacao, titulo, autor_id, jogo_id) VALUES(1, 'Esta eh uma noticia 1', '2021-03-24 16:48:05.591', 'Breaking title 1', 1, 1);
INSERT INTO NOTICIA(id, conteudo, data_publicacao, titulo, autor_id, jogo_id) VALUES(2, 'Esta eh uma noticia 2', '2021-03-24 16:49:05.591', 'Breaking title 2', 1, 2);
INSERT INTO NOTICIA(id, conteudo, data_publicacao, titulo, autor_id, jogo_id) VALUES(3, 'Esta eh uma noticia 3', '2021-03-28 16:48:05.591', 'Breaking title 3', 1, 3);
INSERT INTO NOTICIA(id, conteudo, data_publicacao, titulo, autor_id, jogo_id) VALUES(4, 'Esta eh uma noticia 4', '2021-03-28 16:48:05.580', 'Breaking title 4', 2, 3);
INSERT INTO NOTICIA(id, conteudo, data_publicacao, titulo, autor_id, jogo_id) VALUES(5, 'Esta eh uma noticia 5', '2021-03-28 16:48:05.571', 'Breaking title 5', 1, 4);


INSERT INTO TOPICO(id, titulo, sub_titulo, conteudo, data_publicacao, autor_id, jogo_id) VALUES(1, 'Topico 1', 'Subtitulo 1', 'conteudo 1', '2021-03-24 16:48:05.591', 1, 2);
INSERT INTO TOPICO(id, titulo, sub_titulo, conteudo, data_publicacao, autor_id, jogo_id) VALUES(2, 'Topico 2', 'Subtitulo 2', 'conteudo 2', '2021-03-24 16:49:05.591', 1, 3);
INSERT INTO TOPICO(id, titulo, sub_titulo, conteudo, data_publicacao, autor_id, jogo_id) VALUES(3, 'Topico 3', 'Subtitulo 3', 'conteudo 3', '2021-03-24 16:48:08.591', 2, 4);


INSERT INTO RESPOSTA(id, conteudo, data_publicacao, autor_id, topico_id) VALUES(1, 'Conteudo da resposta 1', '2021-03-24 16:48:05.591', 1, 1);
INSERT INTO RESPOSTA(id, conteudo, data_publicacao, autor_id, topico_id) VALUES(2, 'Conteudo da resposta 2', '2021-03-24 16:48:02.591', 1, 1);
INSERT INTO RESPOSTA(id, conteudo, data_publicacao, autor_id, topico_id) VALUES(3, 'Conteudo da resposta 3', '2021-03-24 16:48:08.591', 1, 2);
















