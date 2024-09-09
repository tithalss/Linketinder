-- Modelo
CREATE DATABASE linketinder;

CREATE TABLE candidatos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150),
    data_nascimento DATE,
    email VARCHAR(100) UNIQUE,
    cpf VARCHAR(11) UNIQUE,
    pais VARCHAR(50),
    cep VARCHAR(8),
    cargo VARCHAR(50),
    descricao TEXT,
    senha VARCHAR(50)
);

CREATE TABLE empresas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    cnpj VARCHAR(14) UNIQUE,
    email VARCHAR(100) UNIQUE,
    descricao TEXT,
    pais VARCHAR(50),
    cep VARCHAR(8),
    senha VARCHAR(50)
);

CREATE TABLE competencias (
    id SERIAL PRIMARY KEY,
    competencia VARCHAR(50)
);

CREATE TABLE vagas (
    id SERIAL PRIMARY KEY,
    cargo VARCHAR(100),
    descricao TEXT,
    local VARCHAR(100),
    id_empresa INT REFERENCES empresas(id)
);

CREATE TABLE candidatos_competencias (
    id_candidato INT REFERENCES candidatos(id),
    id_competencia INT REFERENCES competencias(id),
    PRIMARY KEY (id_candidato, id_competencia)
);

CREATE TABLE vagas_competencias (
    id_vaga INT REFERENCES vagas(id),
    id_competencia INT REFERENCES competencias(id),
    PRIMARY KEY (id_vaga, id_competencia)
);

CREATE TABLE like_empresa (
    id_empresa INT REFERENCES empresas(id),
    id_candidato INT REFERENCES candidatos(id),
    PRIMARY KEY (id_empresa, id_candidato)
);

CREATE TABLE like_candidato (
    id_candidato INT REFERENCES candidatos(id),
    id_vaga INT REFERENCES vagas(id),
    PRIMARY KEY (id_candidato, id_vaga)
);

-- Mocking
INSERT INTO candidatos (nome, data_nascimento, email, cpf, pais, cep, descricao, senha)
VALUES 
    ('Sandubinha da Silva', '1990-05-10', 'sandubinha@email.com', '12345678910', 'Brasil', '77777777', 'Fullstack Developer - Bacharel em Ciência da Computação', 'senhacandidato1'),
    ('Joana Pereira', '1988-07-22', 'joana.pereira@email.com', '23456789101', 'Brasil', '55555555', 'Data Scientist - Mestre em Engenharia de Dados', 'senhacandidato2'),
    ('Carlos Souza', '1992-03-15', 'carlos.souza@email.com', '34567891012', 'Brasil', '66666666', 'Backend Developer - Especialista em Microserviços', 'senhacandidato3'),
    ('Mariana Santos', '1995-11-30', 'mariana.santos@email.com', '45678910123', 'Brasil', '88888888', 'Frontend Developer - Designer de Interfaces', 'senhacandidato4'),
    ('Pedro Alves', '1985-01-05', 'pedro.alves@email.com', '56789101234', 'Brasil', '99999999', 'DevOps Engineer - Especialista em Automação de Infraestrutura', 'senhacandidato5');

INSERT INTO empresas (nome, cnpj, email, descricao, pais, cep, senha)
VALUES 
    ('Pastelsoft', '98765432100000', 'pastelsoft@corpmail.com', 'Desenvolvedora de ERPs para restaurantes', 'Brasil', '87654321', 'senhaempresa1'),
    ('TechPizza', '12345678000123', 'contato@techpizza.com', 'Consultoria em soluções para e-commerce', 'Brasil', '12345678', 'senhaempresa2'),
    ('Code4Life', '23456789000134', 'recrutamento@code4life.com', 'Desenvolvimento de software médico-hospitalar', 'Brasil', '23456789', 'senhaempresa3'),
    ('Innovative Labs', '34567890123456', 'jobs@innlabs.com', 'Laboratório de inovação tecnológica', 'Brasil', '34567890', 'senhaempresa4'),
    ('FutureDev', '45678901234567', 'rh@futuredev.com', 'Agência de desenvolvimento para startups', 'Brasil', '45678901', 'senhaempresa5');

INSERT INTO competencias (competencia)
VALUES ('Python'),('Java'),('Groovy'),('Ruby'),('Angular'),('MySql'),('Jenkins'),('Pandas'),('Machine Learning'),('Análise de requisitos'),('Azure'),('React'),('COBOL'),('Jekyll');

INSERT INTO vagas (cargo, descricao, local, id_empresa)
VALUES ('Angular Developer', 'Desenvolvimento e manutenção de aplicações baseadas em angular framework', 'Remoto', 1);
