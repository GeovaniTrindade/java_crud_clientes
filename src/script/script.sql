create table tb_cliente(
	idCliente	serial			primary key,
	nome		varchar(100)	not null,
	email		varchar(50)		not null,
	cpf			varchar(14)		not null unique,
	telefone	varchar(20)		not null
);

insert into tb_cliente (nome, email, cpf, telefone)
values('Lucas Fernandes', 'lucasfernandes@email.com', '123.432.345-67', '(21)989787-5677');

select * from tb_cliente;