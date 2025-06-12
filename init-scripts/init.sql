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

-- course 5
INSERT INTO course (NAME, DESCRIPTION, COVER_IMG_URL)
VALUES
('Curso de SQL com MySQL',
'Nessa playlist você vai aprender a linguagem SQL com o Curso de SQL com MySQL ',
'./images/courses/sql.png');

-- lesson 21
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Diagrama Entidade Relacionamento (DER)',
'Na primeira aula, vamos falar sobre o Diagrama Entidade Relacionamento, tabelas, atributos, primary keys (chaves primárias), foreign keys (chaves estrangeiras) e muito mais.',
'youtu.be/lHYV_H1526Q');

-- lesson 22
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Relacionamentos One-to-one, One-to-many e Many-to-many',
'Nesse vídeo vamos falar sobre os relacionamentos entre entidades usando SQL (ou qualquer SGBD que quiser na verdade).',
'youtu.be/4v-SzrpC738');

-- lesson 23
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Preparando o ambiente dev',
'Nesse vídeo vamos preparar nosso ambiente de desenvolvimento para o  Curso de SQL com MySQL.',
'youtu.be/eD9_ps_viMw');


-- lesson 24
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Criando tabelas e colunas',
'Nesse vídeo vamos aprender a criar as tabelas e colunas que representamos usando aquele diagrama entidade relacionamento.',
'youtu.be/TDevWRDCH8s');

-- lesson 25
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Insert, Use, Show, Describe',
'Nesse vídeo vamos aprender a criar as tabelas e colunas que representamos usando aquele diagrama entidade relacionamento.',
'youtu.be/BLgrxVZKIO4');

-- course 6
INSERT INTO course (NAME, DESCRIPTION, COVER_IMG_URL)
VALUES
('Curso Básico de Power BI',
'Hoje vamos dar início ao Curso Básico de Power BI 2024 para começar o ano dando seus primeiros passos com essa ferramenta incrível que é o Power BI.',
'./images/courses/power-bi.png');

-- lesson 26
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Introdução ao Power BI',
'Nessa primeira aula eu quero falar o que é o Power BI, como instalar o Power BI, como ele funciona e como você pode dar seus primeiros passos no Power BI.',
'youtu.be/VMSfIaiW54M');

-- lesson 27
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Editando a Base de Dados com Power Query',
'Nessa aula eu vou te mostrar mais sobre o tratamento de dados no Power Query, como podemos juntar colunas no Power Query, como extrair dados de uma coluna para análises mais específicas.',
'youtu.be/9JAZ9S_DNGA');

-- lesson 28
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Introdução às Fórmulas do Power BI',
'Nessa aula eu quero te mostrar as Fórmulas do Power BI, que são chamadas de Fórmulas DAX (Data Analysis Expressions).',
'youtu.be/xx3uYFmsqG4');

-- lesson 29
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Tipos de Gráficos no Power BI e Como Usar',
'Nessa aula eu quero te mostrar quais são os tipos de gráficos no Power BI e como usar cada um deles.',
'youtu.be/0ZlpuB3Hclc');

-- lesson 30
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Construindo o Dashboard',
'Nessa aula nós vamos finalizar o nosso Dashboard no Power BI para que você tenha uma análise completa com todos os indicadores!',
'youtu.be/fvqEHuRmyYw');

-- course 7
INSERT INTO course (NAME, DESCRIPTION, COVER_IMG_URL)
VALUES
('Curso de C# e .NET 8: Guia Completo do Básico ao Avançado',
'Bem-vindo ao curso completo de C# e .NET 8!',
'./images/courses/csharp.png');

-- lesson 31
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Sintaxe Básica',
'Na Aula #1 do curso "Curso de C# e .NET 8", você vai aprender sobre a sintaxe básica da linguagem C#',
'youtu.be/ON3R9J6SK_w');

-- lesson 32
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Como Executar uma Aplicação .NET no Visual Studio Code',
'Neste vídeo, você aprenderá o passo a passo para configurar e executar uma aplicação .NET no Visual Studio Code, um editor de código leve e altamente versátil.',
'youtu.be/3JIBmshOzu4');

-- lesson 33
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Variáveis',
'Na Aula #2 do curso "Curso de C# e .NET 8", vamos focar em variáveis. Você vai aprender o que são variáveis, como declará-las e utilizá-las no C#.',
'youtu.be/BEBpG0HxmnY');

-- lesson 34
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Constantes',
'Na Aula #3 do curso "Curso de C# e .NET 8", vamos explorar o conceito de constantes em C#. Você vai aprender como declarar e utilizar constantes, entendendo sua importância na criação de código.',
'youtu.be/3ag2cbnO_uQ');

-- lesson 35
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Input e Output',
'Na Aula #4 do curso "Curso de C# e .NET 8", vamos aprender sobre Input e Output em C#. Você descobrirá como capturar a entrada do usuário e exibir informações na tela, dominando as interações básicas com o console.',
'youtu.be/4Z4wxWrdpU0');

-- course 8
INSERT INTO course (NAME, DESCRIPTION, COVER_IMG_URL)
VALUES
('Curso completo e atual de HTML5 e CSS3',
'O Curso de HTML5 e CSS3 vai ensinar a criar sites usando a linguagem de marcação hipertexto (HTML) e  folhas de estilo em cascata (CSS), todas em suas versões mais recentes. ',
'./images/courses/html5_css3.png');

-- lesson 36
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Seu primeiro código HTML',
'O Curso de HTML5 e CSS3 vai ensinar a criar sites usando a linguagem de marcação hipertexto (HTML) e  folhas de estilo em cascata (CSS), todas em suas versões mais recentes.',
'youtu.be/E6CdIawPTh0');

-- lesson 37
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Parágrafos e Quebras',
'O Curso de HTML5 e CSS3 vai ensinar a criar sites usando a linguagem de marcação hipertexto (HTML) e  folhas de estilo em cascata (CSS), todas em suas versões mais recentes.',
'youtu.be/f6NTJdtEFOc');

-- lesson 38
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Quais são os formatos para imagens na Web?',
'O Curso de HTML5 e CSS3 vai ensinar a criar sites usando a linguagem de marcação hipertexto (HTML) e  folhas de estilo em cascata (CSS), todas em suas versões mais recentes.',
'youtu.be/xg-vHgLF0m');

-- lesson 39
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('A tag img em HTML5',
'O Curso de HTML5 e CSS3 vai ensinar a criar sites usando a linguagem de marcação hipertexto (HTML) e  folhas de estilo em cascata (CSS), todas em suas versões mais recentes.',
'youtu.be/CwOmEetWMnU');

-- lesson 40
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Estilos CSS inline',
'O Curso de HTML5 e CSS3 vai ensinar a criar sites usando a linguagem de marcação hipertexto (HTML) e  folhas de estilo em cascata (CSS), todas em suas versões mais recentes.',
'youtu.be/byqhpuVpvEI');

-- course 9
INSERT INTO course (NAME, DESCRIPTION, COVER_IMG_URL)
VALUES
('Curso de Redes de Computadores',
'Curso de redes da Bóson Treinamentos, vamos abordar desde tópicos básicos ate tópicos avançados.',
'./images/courses/networks.jpg');

-- lesson 41
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Componentes de uma rede, LAN, WAN',
'Essa é a primeira vídeo-aula do curso de redes, falamos sobre o que é uma rede, quais os componentes de uma rede de computadores bem como o conceito básico de #LAN e #WAN.',
'youtu.be/efGBoJ-f_2Y');

-- lesson 42
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Escopos de Rede',
'Neste vídeo vamos falar sobre os principais escopos de rede existentes, incluindo os escopos LAN, MAN, WAN, PAN, além d citar outros escopos, como GAN, CAN, HAN e SAN.',
'youtu.be/4dxynCvfbFM');

-- lesson 43
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Topologias',
'Na segunda vídeo-aula do curso de #Redes de Computadores, apresentamos as principais topologias: barramento, anel, estrela e malha.',
'youtu.be/1hJaTQIT2HQ');

-- lesson 44
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Modelo OSI',
'Nesse vídeo do Curso de Redes de Computadores apresentamos o modelo #OSI e as suas sete camadas: física, enlace, rede, transporte, sessão, apresentação e aplicação.',
'youtu.be/WO1uGJRqrwI');

-- lesson 45
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Introdução ao TCP/IP',
'Neste vídeo de nosso curso de redes de computadores, vamos explicar o que é a pilha de protocolos TCP/IP.',
'youtu.be/bH29oltn8Cw');

-- course 10
INSERT INTO course (NAME, DESCRIPTION, COVER_IMG_URL)
VALUES
('Curso Introdução a Machine Learning',
'A evolução e a popularidade da IA deve somente aumentar com o tempo, dada as muitas aplicações práticas e soluções para problemas mundiais.',
'./images/courses/ml.jpg');

-- lesson 46
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('O que é Inteligência Artificial?',
'Nesse vídeo vamos aprender o que é inteligência artificial e como ela está sendo aplicada no nosso dia-a-dia.',
'youtu.be/ID5Ui22F8HQ');

-- lesson 47
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('O que é Machine Learning (Aprendizado de Máquina)?',
'Nesse vídeo vamos aprender o que é machine learning (aprendizado de máquina) e por que essa área tem sido tão importante no mundo da inteligência artificial.',
'youtu.be/qDmojpkd8rE');

-- lesson 48
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('Dados de Treino e Teste',
'Aprenda nesse vídeo a importância em se separar os dados em duas partes: treino e teste.',
'youtu.be/N2TT2Q83abc');

-- lesson 49
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('O que é Overfitting e Underfitting?',
'Aprenda nesse vídeo os conceitos de overfitting (sobre ajuste) e underfitting (sub ajuste) no ramo do machine learning.',
'youtu.be/IHAb3NHDahU');

-- lesson 50
INSERT INTO lesson (NAME, DESCRIPTION, VIDEO_URL)
VALUES
('O que é Aprendizado Supervisionado x Não Supervisionado',
'Entenda os conceitos de aprendizado supervisionado e aprendizado não supervisionado no mundo do machine learning.',
'youtu.be/JDDqP6IZ4NQ');


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
(4, 20),
(5, 21),
(5, 22),
(5, 23),
(5, 24),
(5, 25),
(6, 26),
(6, 27),
(6, 28),
(6, 29),
(6, 30),
(7, 31),
(7, 32),
(7, 33),
(7, 34),
(7, 35),
(8, 36),
(8, 37),
(8, 38),
(8, 39),
(8, 40),
(9, 41),
(9, 42),
(9, 43),
(9, 44),
(9, 45),
(10, 46),
(10, 47),
(10, 48),
(10, 49),
(10, 50)
;


INSERT INTO badges (NAME, IMG_URL)
VALUES
    ('Régua', '/images/badges/ruler.png'),
    ('Livro', '/images/badges/book.png'),
    ('Medalha', '/images/badges/medal.png'),
    ('Formado', '/images/badges/mortarboard.png');
