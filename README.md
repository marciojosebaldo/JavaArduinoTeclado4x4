# Título: JavaArduinoTeclado4x4
# Descrição: Usuário utilia teclado 4x4 para inserir e senha e é validada no banco de dados. Além disso, é possível cadastrar usuario (cadastro) e reserva de quartos

# Arduino Mega 2560
# Framework Spring Boot Java
# Teclado 4x4
# Banco de Dados PostgreSQL. Nome do banco: gestaoHotel
  # CREATE TABLE cadastro (id SERIAL NOT NULL PRIMARY KEY, usuario VARCHAR(50), senha VARCHAR(50), data_nascimento VARCHAR(50), num_documento VARCHAR(50), email VARCHAR(80), telefone VARCHAR(80))
  # CREATE TABLE reserva (id SERIAL NOT NULL PRIMARY KEY, usuario VARCHAR(50), senha VARCHAR(50), data_entrada VARCHAR(50), data_saida VARCHAR(50), quarto INTEGER)

# Biblioteca Java: 
  # jSerialComm-2.9.3.jar
  # postgresql-42.6.0

# Biblioteca Arduino:
  # Keypad Library for Arduino

