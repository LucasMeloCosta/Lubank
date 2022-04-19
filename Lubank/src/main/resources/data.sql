INSERT INTO USUARIO(nome, email, senha) VALUES('Aluno', 'aluno@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');
INSERT INTO USUARIO(nome, email, senha) VALUES('Moderador', 'moderador@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');

INSERT INTO PERFIL(id, nome) VALUES(1, 'ROLE_ALUNO');
INSERT INTO PERFIL(id, nome) VALUES(2, 'ROLE_MODERADOR');

INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES(1, 1);
INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES(2, 2);

INSERT INTO CONTA(DTYPE, id, numero, nome, limite, saldo, tipoDeConta, dataAtualizacao, dataCadastro) VALUES('ContaCorrente', '1', '123456789', 'Teste Conta Corrente', '5000', '2500', 'ContaCorrente', '2022-04-19 08:40:04.000000', '2022-04-19 07:46:23.000000');
INSERT INTO CONTA(DTYPE, id, numero, nome, limite, saldo, tipoDeConta, dataAtualizacao, dataCadastro) VALUES('ContaPoupanca', '2', '223456789', 'Teste Conta Poupanca', '5000', '2500', 'ContaPoupanca', '2022-04-19 08:40:04.000000', '2022-04-19 07:46:23.000000');

