-- TABLE
-- Create table "appointment"
-- Used as application datasource
CREATE TABLE public.appointment (
    id int4 NOT NULL,
	expenditure_nature_code int8,
    expenditure_nature_description varchar(100),
    appointment_description varchar(100),
    appointment_date date,
    appointment_value real,

	CONSTRAINT pk_appointment PRIMARY KEY (id),
	CONSTRAINT uk_appointment_id UNIQUE (id)
);

-- Create sequence "appointment_id_seq" for "appointment" table
CREATE SEQUENCE public.appointment_id_seq
-- Use the same data type as the column that will use (own) the sequence values
AS int4
INCREMENT 1
MINVALUE 1
NO MAXVALUE
NO CYCLE
START WITH 1
-- Column that will use the sequence
OWNED BY public.appointment.id;

-- Changes sequence column standard value to admit the sequence value
ALTER TABLE public.appointment ALTER COLUMN id SET DEFAULT nextval('appointment_id_seq'::regclass);

-- DATA
-- Insert data at "appointment" table
INSERT INTO public.appointment (expenditure_nature_code, expenditure_nature_description, appointment_description, appointment_date, appointment_value)
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
