CREATE TABLE tb_cliente(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE,
    email VARCHAR(150) UNIQUE,
    telefone VARCHAR(20)
);

CREATE TABLE tb_usuario(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    role TEXT NOT NULL
);

CREATE TABLE tb_atividade(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome TEXT NOT NULL UNIQUE
);

CREATE TABLE tb_processo(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    tipo_processo VARCHAR(50),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    criado_por BIGINT NOT NULL,

    CONSTRAINT fk_processo_cliente
    FOREIGN KEY (cliente_id)
    REFERENCES tb_cliente(id),

    CONSTRAINT fk__usuario_processo
    FOREIGN KEY (criado_por)
    REFERENCES tb_usuario (id)
);

CREATE TABLE tb_tarefa(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    atividade_id BIGINT NOT NULL,
    processo_id BIGINT NOT NULL,
    responsavel_id BIGINT NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_inicio TIMESTAMP NOT NULL,
    data_prevista TIMESTAMP,
    data_conclusao TIMESTAMP,

    status VARCHAR(20) NOT NULL,
    descricao TEXT,
    criada_por_id BIGINT NOT NULL,

    CONSTRAINT fk_tarefa_atividade
    FOREIGN KEY (atividade_id)
    REFERENCES tb_atividade(id),

    CONSTRAINT fk_tarefa_processo
    FOREIGN KEY (processo_id)
    REFERENCES tb_processo(id),

    CONSTRAINT fk_responsavel_tarefa
    FOREIGN KEY (responsavel_id)
    REFERENCES tb_usuario(id),

    CONSTRAINT fk_criador_tarefa
    FOREIGN KEY (criada_por_id)
    REFERENCES tb_usuario(id)

);

CREATE TABLE tb_movimentacao(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    processo_id BIGINT NOT NULL,
    tarefa_id BIGINT,
    tipo VARCHAR(50) NOT NULL,
    descricao TEXT,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_id BIGINT NOT NULL,

    CONSTRAINT fk_processo_movimentacao
    FOREIGN KEY (processo_id)
    REFERENCES tb_processo(id),

    CONSTRAINT fk_tarefa_movimentacao
    FOREIGN KEY (tarefa_id)
    REFERENCES tb_tarefa(id),

    CONSTRAINT fk_usuario_movimentacao
    FOREIGN KEY (usuario_id)
    REFERENCES tb_usuario(id)
);

