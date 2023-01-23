-- TABELA
-- Cria tabela "lancamento"
-- Usada como fonte de dados da aplicação
CREATE TABLE public.lancamento (
    id int4 NOT NULL,
	codigo_natureza_despesa int8,
    descricao_natureza_despesa varchar(100),
    descricao_lancamento varchar(100),
    data_lancamento date,
    valor_lancamento real,

	CONSTRAINT pk_lancamento PRIMARY KEY (id),
	CONSTRAINT uk_lancamento_id UNIQUE (id)
);

-- Cria a sequencia "lancamento_id_seq" para a tabela "lancamento"
CREATE SEQUENCE public.lancamento_id_seq
-- Usar o mesmo tipo da coluna que vai usar a sequencia
AS int4
INCREMENT 1
MINVALUE 1
NO MAXVALUE
NO CYCLE
START WITH 1
-- Coluna que vai usar a sequenca
OWNED BY public.lancamento.id;

-- Altera o valor padrao da coluna que usa a sequencia para usar a sequencia
ALTER TABLE public.lancamento ALTER COLUMN id SET DEFAULT nextval('lancamento_id_seq'::regclass);

-- DADOS
-- Inserir dados na tabela "lancamento"
INSERT INTO public.lancamento (codigo_natureza_despesa, descricao_natureza_despesa, descricao_lancamento, data_lancamento, valor_lancamento)
VALUES
(44905224,'EQUIPAMENTO DE PROTEÇÃO SEGURANÇA E SOCORRO','Alarme','2020-05-01',1000),
(44905287,'MATERIAL DE CONSUMO DE USO DURADOURO','Cortina de sala','2020-05-02',1000),
(44905287,'MATERIAL DE CONSUMO DE USO DURADOURO','Bandeiras','2020-05-03',500),
(44905287,'MATERIAL DE CONSUMO DE USO DURADOURO','Guarda Sol','2020-05-04',500),
(33903016,'MATERIAL DE EXPEDIENTE','Resma de Papel A4','2020-05-01',2000),
(33903016,'MATERIAL DE EXPEDIENTE','Cartucho Impressora','2020-05-10',2000),
(44905218,'COLEÇÕES E MATERIAIS BIBLIOGRÁFICOS','Dicionários','2020-05-12',4000),
(44905218,'COLEÇÕES E MATERIAIS BIBLIOGRÁFICOS','Livro de Algoritmos','2020-05-11',4000),
(33903024,'MATERIAL P/ MANUT. DE BENS IMÓVEIS/INSTALAÇÕES','Amianto','2020-05-13',8000),
(33903024,'MATERIAL P/ MANUT. DE BENS IMÓVEIS/INSTALAÇÕES','Aparelhos Sanitários','2020-05-11',6000),
(33903024,'MATERIAL P/ MANUT. DE BENS IMÓVEIS/INSTALAÇÕES','Cimento','2020-05-12',2000),
(33903302,'PASSAGENS PARA O EXTERIOR','Viagem para Las Vegas','2020-05-01',32000);
