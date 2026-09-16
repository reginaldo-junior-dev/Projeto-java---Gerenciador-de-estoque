-- Script de recriação do banco "projetojava" para o projeto Gerenciador de Estoque
-- Gerado a partir da leitura do código (ConnectionFactory, AlimentoDAO, EstoqueDAO, TelaLogin)
-- Execute este script inteiro no MySQL (Workbench, DBeaver ou `mysql -u root -p < schema.sql`)

CREATE DATABASE IF NOT EXISTS projetojava
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

USE projetojava;

-- Tabela de usuários do sistema (login da TelaLogin)
CREATE TABLE IF NOT EXISTS acesso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(60) NOT NULL, -- hash BCrypt (60 caracteres), nunca texto puro
    cargo VARCHAR(20) NOT NULL -- 'Administrador' ou 'Funcionário' (usado em TelaLogin.java)
);

-- Tabela de alimentos (cadastro). O id NÃO é auto_increment: o próprio usuário
-- digita o código em TelaCadastro (veja alimento.setId(Integer.parseInt(txtcod...))).
CREATE TABLE IF NOT EXISTS alimento (
    id INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(50),
    quantidade INT NOT NULL DEFAULT 0,
    data_val DATE,
    fornecedor VARCHAR(100),
    preco DECIMAL(10,2)
);

-- Tabela de movimentações de estoque (entrada/saída)
CREATE TABLE IF NOT EXISTS estoque (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_alimento INT NOT NULL,
    movimentacao VARCHAR(10) NOT NULL, -- 'entrada' ou 'saída'
    data_ent_sai DATE NOT NULL,
    quanti_usada INT NOT NULL,
    CONSTRAINT fk_estoque_alimento FOREIGN KEY (id_alimento)
        REFERENCES alimento(id)
);

-- Usuários iniciais para conseguir logar no sistema (senha de ambos: 12345678, já em hash BCrypt).
-- Troque essas senhas depois, e gere hashes novos com BCrypt.hashpw(senha, BCrypt.gensalt()).
INSERT INTO acesso (login, senha, cargo) VALUES
    ('admin', '$2a$10$Mt1V6xNxEXS5ry.TUScjJ.RXjVykya/jqPGfF8IK9YIA3qFj0NF8q', 'Administrador'),
    ('func', '$2a$10$Mt1V6xNxEXS5ry.TUScjJ.RXjVykya/jqPGfF8IK9YIA3qFj0NF8q', 'Funcionário');
