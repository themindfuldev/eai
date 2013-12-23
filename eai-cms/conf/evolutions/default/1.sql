# --- !Ups

-- categoria_de_produto
CREATE TABLE categoria_de_produto (
    id bigint NOT NULL,
    ativo boolean NOT NULL,
    descricao character varying(255) NOT NULL,
    nome character varying(255) NOT NULL,
    categoria_mestre bigint NOT NULL
);

ALTER TABLE public.categoria_de_produto OWNER TO eai;

ALTER TABLE ONLY categoria_de_produto
    ADD CONSTRAINT categoria_de_produto_pkey PRIMARY KEY (id);

ALTER TABLE ONLY categoria_de_produto
    ADD CONSTRAINT fk_9q46t0n2f41mldnon5vkob3r9 FOREIGN KEY (categoria_mestre) REFERENCES categoria_de_produto(id);


CREATE SEQUENCE categoria_de_produto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.categoria_de_produto_seq OWNER TO eai;

-- categoria_de_produto_subcategorias
CREATE TABLE categoria_de_produto_subcategorias (
    categoria_de_produto bigint NOT NULL,
    subcategorias bigint NOT NULL
);

ALTER TABLE public.categoria_de_produto_subcategorias OWNER TO eai;

ALTER TABLE ONLY categoria_de_produto_subcategorias
    ADD CONSTRAINT fk_srficeibyhu89gnh7rg0y5rw8 FOREIGN KEY (subcategorias) REFERENCES categoria_de_produto(id);

ALTER TABLE ONLY categoria_de_produto_subcategorias
    ADD CONSTRAINT fk_te5wqrs0nn48cj7xmoc7m1n3k FOREIGN KEY (categoria_de_produto) REFERENCES categoria_de_produto(id);
    
ALTER TABLE ONLY categoria_de_produto_subcategorias
    ADD CONSTRAINT uk_srficeibyhu89gnh7rg0y5rw8 UNIQUE (subcategorias);
    
-- tipo_de_produto
CREATE TABLE tipo_de_produto (
    id bigint NOT NULL,
    ativo boolean NOT NULL,
    descricao character varying(255) NOT NULL,
    nome character varying(255) NOT NULL
);

ALTER TABLE public.tipo_de_produto OWNER TO eai;

ALTER TABLE ONLY tipo_de_produto
    ADD CONSTRAINT tipo_de_produto_pkey PRIMARY KEY (id);


CREATE SEQUENCE tipo_de_produto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.tipo_de_produto_seq OWNER TO eai;

-- produto
CREATE TABLE produto (
    id bigint NOT NULL,
    ativo boolean NOT NULL,
    descricao character varying(255) NOT NULL,
    nome character varying(255) NOT NULL,
    categoria_de_produto bigint NOT NULL,
    tipo_de_produto bigint NOT NULL
);

ALTER TABLE public.produto OWNER TO eai;

ALTER TABLE ONLY produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);

ALTER TABLE ONLY produto
    ADD CONSTRAINT fk_htfb04n8nxb1ob02kcu0iiaj6 FOREIGN KEY (categoria_de_produto) REFERENCES categoria_de_produto(id);

ALTER TABLE ONLY produto
    ADD CONSTRAINT fk_mn05ldres7yo5cpu7p90qxi0a FOREIGN KEY (tipo_de_produto) REFERENCES tipo_de_produto(id);


CREATE SEQUENCE produto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.produto_seq OWNER TO eai;

-- imagem_de_produto
CREATE TABLE imagem_de_produto (
    id bigint NOT NULL,
    ativo boolean NOT NULL,
    url character varying(255) NOT NULL,
    produto bigint NOT NULL
);

ALTER TABLE public.imagem_de_produto OWNER TO eai;

ALTER TABLE ONLY imagem_de_produto
    ADD CONSTRAINT imagem_de_produto_pkey PRIMARY KEY (id);

ALTER TABLE ONLY imagem_de_produto
    ADD CONSTRAINT fk_5vmfq6ypyrdudcswigyaju8y0 FOREIGN KEY (produto) REFERENCES produto(id);


CREATE SEQUENCE imagem_de_produto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.imagem_de_produto_seq OWNER TO eai;


-- produto_imagens_de_produto
CREATE TABLE produto_imagens_de_produto (
    produto bigint NOT NULL,
    imagens_de_produto bigint NOT NULL
);

ALTER TABLE public.produto_imagens_de_produto OWNER TO eai;

ALTER TABLE ONLY produto_imagens_de_produto
    ADD CONSTRAINT uk_oadqxyajj7tik2c9n6edg5rtf UNIQUE (imagens_de_produto);

ALTER TABLE ONLY produto_imagens_de_produto
    ADD CONSTRAINT fk_gv0dltelhqvd4bnp48ysblxp1 FOREIGN KEY (produto) REFERENCES produto(id);

ALTER TABLE ONLY produto_imagens_de_produto
    ADD CONSTRAINT fk_oadqxyajj7tik2c9n6edg5rtf FOREIGN KEY (imagens_de_produto) REFERENCES imagem_de_produto(id);

-- usuario
CREATE TABLE usuario (
    id bigint NOT NULL,
    ativo boolean NOT NULL,
    nome_completo character varying(255) NOT NULL,
    nome_de_usuario character varying(255) NOT NULL,
    salt character varying(255) NOT NULL,
    senha character varying(255) NOT NULL
);

ALTER TABLE public.usuario OWNER TO eai;

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);
    
-- catalogo 
CREATE TABLE catalogo (
    id bigint NOT NULL,
    ativo boolean NOT NULL,
    data_de_inicio timestamp without time zone NOT NULL,
    data_de_termino timestamp without time zone NOT NULL,
    descricao character varying(255) NOT NULL,
    nome character varying(255) NOT NULL
);

ALTER TABLE public.catalogo OWNER TO eai;

ALTER TABLE ONLY catalogo
    ADD CONSTRAINT catalogo_pkey PRIMARY KEY (id);
    

CREATE SEQUENCE catalogo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.catalogo_seq OWNER TO eai;

-- item_de_catalogo
CREATE TABLE item_de_catalogo (
    id bigint NOT NULL,
    ativo boolean NOT NULL,
    preco_de_venda numeric(19,2) NOT NULL,
    prevo_de_fabricacao numeric(19,2) NOT NULL,
    catalogo bigint NOT NULL,
    produto bigint NOT NULL
);

ALTER TABLE public.item_de_catalogo OWNER TO eai;

ALTER TABLE ONLY item_de_catalogo
    ADD CONSTRAINT item_de_catalogo_pkey PRIMARY KEY (id);

ALTER TABLE ONLY item_de_catalogo
    ADD CONSTRAINT fk_ata09xfnsd6fqtk3qh8ib8u4j FOREIGN KEY (catalogo) REFERENCES catalogo(id);

ALTER TABLE ONLY item_de_catalogo
    ADD CONSTRAINT fk_eoeyskoitwp70g2r7g9l2bkef FOREIGN KEY (produto) REFERENCES produto(id);


CREATE SEQUENCE item_de_catalogo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.item_de_catalogo_seq OWNER TO eai;

-- catalogo_itens_de_catalogo
CREATE TABLE catalogo_itens_de_catalogo (
    catalogo bigint NOT NULL,
    itens_de_catalogo bigint NOT NULL
);

ALTER TABLE public.catalogo_itens_de_catalogo OWNER TO eai;

ALTER TABLE ONLY catalogo_itens_de_catalogo
    ADD CONSTRAINT uk_5ljrg9a00iwb1p104svrde3xq UNIQUE (itens_de_catalogo);

ALTER TABLE ONLY catalogo_itens_de_catalogo
    ADD CONSTRAINT fk_5ljrg9a00iwb1p104svrde3xq FOREIGN KEY (itens_de_catalogo) REFERENCES item_de_catalogo(id);

ALTER TABLE ONLY catalogo_itens_de_catalogo
    ADD CONSTRAINT fk_jqdockw5p987m6pht5xd0ftei FOREIGN KEY (catalogo) REFERENCES catalogo(id);


CREATE SEQUENCE usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.usuario_seq OWNER TO eai;

# --- !Downs
 
DROP TABLE categoria_de_produto;
DROP SEQUENCE categoria_de_produto_seq;

DROP TABLE categoria_de_produto_subcategoria;

DROP TABLE tipo_de_produto;
DROP SEQUENCE tipo_de_produto_seq;

DROP TABLE produto;
DROP SEQUENCE produto_seq;

DROP TABLE imagem_de_produto;
DROP SEQUENCE imagem_de_produto_seq;

DROP TABLE produto_imagens_de_produto;

DROP TABLE usuario;
DROP SEQUENCE usuario_seq;

DROP TABLE catalogo;
DROP SEQUENCE catalogo_seq;

DROP TABLE item_de_catalogo;
DROP SEQUENCE item_de_catalogo_seq;

DROP TABLE catalogo_itens_de_catalogo;
