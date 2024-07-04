
-- Script de criação da tabela Assunto
CREATE TABLE Assunto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(40)
);

-- Script de rollback da tabela Assunto (drop table)
DROP TABLE IF EXISTS Assunto;

-- Script de criação da tabela Autor
CREATE TABLE Autor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(40)
);

-- Script de rollback da tabela Autor (drop table)
DROP TABLE IF EXISTS Autor;

-- Script de criação da tabela Livro
CREATE TABLE Livro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(40),
    editora VARCHAR(40),
    edicao INTEGER,
    AnoPublicacao VARCHAR(4),
    id_autor BIGINT,
    id_assunto BIGINT,
    FOREIGN KEY (id_autor) REFERENCES Autor(id),
    FOREIGN KEY (id_assunto) REFERENCES Assunto(id)
);

-- Script de rollback da tabela Livro (drop table)
DROP TABLE IF EXISTS Livro;