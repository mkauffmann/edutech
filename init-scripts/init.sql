CREATE TABLE badges (
    ID SERIAL PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    IMG_URL VARCHAR(255) NOT NULL
);


CREATE TABLE lesson (
    ID_LESSON SERIAL PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(255) NOT NULL,
    VIDEO_URL VARCHAR(255) NOT NULL
);


CREATE TABLE course (
    ID_COURSE SERIAL PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(255) NOT NULL,
    COVER_IMG_URL VARCHAR(255) NOT NULL
);

CREATE TABLE course_lesson (
    ID_COURSE SERIAL REFERENCES course(ID_COURSE),
    ID_LESSON SERIAL REFERENCES lesson(ID_LESSON)
);

-- course 1
INSERT INTO course (NAME, DESCRIPTION, COVER_IMG_URL)
VALUES
('Curso de PROGRAMAÇÃO JAVA para INICIANTES',
'Nesse curso de programação Java para iniciantes nós vamos explorar os conceitos fundamentais da linguagem de forma clara e prática, te ajudando a dar os primeiros passos e avançar para o próximo nível na programação.',
'./images/courses/java.png');

-- lesson 1
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Fundamentos + Programação Orientada Objetos',
'Se você já programa e quer iniciar sua jornada no universo do Java, este vídeo é para você!',
'youtu.be/nODe5lFcGpg');

-- lesson 2
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Tutorial Completo de Java Spring',
'O Spring Boot é uma ferramenta essencial para quem programa em Java. Ele facilita o desenvolvimento e elimina a necessidade de configurar manualmente um grande número de dependências e frameworks, o que torna o processo muito mais ágil e eficiente.',
'youtu.be/YY_hf0FOIcU');

-- lesson 3
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Como tratar exceções no Java Spring',
'Nesse vídeo eu vou te mostrar as melhores práticas tratar as exceções da sua aplicação Spring Boot de forma elegante e eficiente.',
'youtu.be/GmbK-O3v3Gg');

-- lesson 4
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Autenticação e Autorização com Spring Security, JWT Tokens e Roles',
'Nesse vídeo vamos aprender a implementar autenticação nas nossas aplicações Java Spring usando a biblioteca Spring Security. Iremos implementar autenticação Stateless usando tokens JWT, com autorização através do controle de ROLES dos nossos usuários.',
'youtu.be/5w-YCcOjPD0');

-- lesson 5
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Como fazer DEPLOY de aplicação JAVA SPRING NA AWS',
'Nesse vídeo você irá aprender como fazer o deploy da sua aplicação Java Spring Boot usando o serviço de computação em nuvem mais popular entre as empresas, o AWS EC2.',
'youtu.be/bEkCdlrxF54');

-- course 2
INSERT INTO course (NAME, DESCRIPTION, COVER_IMG_URL)
VALUES
('Curso de Docker Completo',
'Curso introdutório ao Docker, ideal para quem deseja aprender os fundamentos da criação, gerenciamento e uso de containers.',
'./images/courses/docker.png');

-- lesson 6
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Preparando ambiente de estudos/desenvolvimento Docker',
'Nesse video irem demonstrar a instalação do Docker Desktop e comentar sobre as diferenças com o Docker Toolbox.',
'youtu.be/c2y_yz9B6_M');

-- lesson 7
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Introducao a CLI do Docker',
'Neste video irei dar uma introdução a CLI do Docker. Explicando de forma prática como funciona a estrutura da CLI e como consultar o help dos comandos e sub comandos.',
'youtu.be/_su5XaOdEKg');


-- lesson 8
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Gerenciando containers Docker - Parte 1',
'Neste vídeo iremos sair da teoria e dar inicio a prática. Vamos falar sobre como iniciar nosso primeiro container. Abordaremos também como gerenciar os containers. ',
'youtu.be/FLgtWvt21u0');

-- lesson 9
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Gerenciando containers Docker - Parte 2 ',
'Neste vídeo iremos continuar o assunto sobre gerenciamento de containers.',
'youtu.be/mgSgkoxGtd0');

-- lesson 10
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Como funciona volumes em containers Docker',
'Um problema conhecido quando trabalhamos com containers é a persistência de dados.Neste video irei explicar como o docker e resolve este problema e quais são as soluções disponíveis.',
'youtu.be/Y02f4hj0yDU');

-- course 3
INSERT INTO course (NAME, DESCRIPTION, COVER_IMG_URL)
VALUES
('Curso de React',
'Curso básico de React que ensina os principais conceitos da biblioteca JavaScript mais usada no desenvolvimento front-end.',
'./images/courses/react.png');

-- lesson 11
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Introdução',
'Neste vídeo você vai conhecer o projeto que vamos desenvolver no curso de #React e também aprender o que é e para que é utilizado o React, a biblioteca #JavaScript mais utilizada da atualidade',
'youtu.be/FXqX7oof0I4');

-- lesson 12
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Instalando o React (create-react-app)',
'Nesta aula você vai aprender a instalar e também rodar uma aplicação em #React, vamos utilizar o create-react-app',
'youtu.be/Jg6JaEjovJk');

-- lesson 13
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Entendendo o JSX',
'Neste vídeo vamos conhecer um recurso muito utilizado no #React o #JSX, que é a forma que escrevemos HTML no React, podendo deixar ele dinâmico, imprimindo valores de propriedades ou variáveis e também executando lógicas simples',
'youtu.be/9iKNxnFJY_Q');

-- lesson 14
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Criando componentes no React',
'Neste vídeo vamos ampliar ainda mais os nossos conhecimentos em #React, criando componentes e entendendo para que eles servem',
'youtu.be/-wrsG0IGc-M');

-- lesson 15
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Trabalhando com props',
'Um recurso que permite passar dados do componente pai para o componente filho, desta maneira você consegue deixar os componentes dinâmicos, reagindo aos dados enviados',
'youtu.be/ZLtBdpwg8tI');

-- course 4
INSERT INTO course (NAME, DESCRIPTION, COVER_IMG_URL)
VALUES
('Curso de Segurança da Informação',
'Começa aqui o seu curso de Segurança da Informação, onde vamos aprender os fundamentos necessários para conseguir viver mais tranquilo, sem se preocupar tanto com fraudes e tentativas de invasão.',
'./images/courses/sec.jpeg');

-- lesson 16
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Os pilares da Segurança da Informação',
'Quais são os pilares da Segurança da Informação? Quantos são os pilares da segurança?',
'youtu.be/Y0beKLRf-fI');

-- lesson 17
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Termos de Segurança da Informação',
'Quais são os termos mais comuns de Segurança da Informação? Qual é o vacabulário da Segurança da Informação? O que preciso saber para iniciar na área de segurança da Informação?',
'youtu.be/mJCbnSrlQhs');

-- lesson 18
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Principais Riscos',
'Quais os riscos que o meu computador corre? O que um hacker pode fazer com o meu computador? Como uma hacker usa meu computador?',
'youtu.be/yMaPEZVAuqk');

-- lesson 19
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Cuidados a serem tomados',
'Como deve me proteger na Internet? Quais os cuidados que devo tomar na hora de usar o computador? Como manter meu computador atualizado?',
'youtu.be/PxcQu1wA2mY');

-- lesson 20
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Cuidados com APPs e venda, troca e furto',
'O que preciso saber quando for comprar um celular de outra pessoa? Como proteger minhas senhas? Como proteger meu celular?',
'youtu.be/tHput2b6Fj8');



INSERT INTO course_lesson (ID_COURSE, ID_LESSON)
VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(2, 6),
(2, 7),
(2, 8),
(2, 9),
(2, 10),
(3, 11),
(3, 12),
(3, 13),
(3, 14),
(3, 15),
(4, 16),
(4, 17),
(4, 18),
(4, 19),
(4, 20)
;


INSERT INTO badges (NAME, IMG_URL)
VALUES
    ('Régua', '/images/badges/ruler.png'),
    ('Livro', '/images/badges/book.png'),
    ('Medalha', '/images/badges/medal.png'),
    ('Formado', '/images/badges/mortarboard.png');
