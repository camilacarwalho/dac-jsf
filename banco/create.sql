CREATE TABLE dependente(
	uuid VARCHAR(100) PRIMARY KEY,
	nome VARCHAR(100),
	nascimento DATE
);

CREATE TABLE pessoa(
	id SERIAL PRIMARY KEY,
	cpf VARCHAR(15),
	nome VARCHAR(100),
	depend VARCHAR(100),
	FOREIGN KEY (depend) REFERENCES dependente (uuid)
);
