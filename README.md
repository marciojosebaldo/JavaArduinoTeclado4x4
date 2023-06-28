# Título: JavaArduinoTeclado4x4
# Descrição: Usuário utiliza teclado 4x4 conectado no Arduino para inserir a senha e simular a abertura de uma porta. Esta abertura da porta é simulada com o acendimento do LED nativo do pino 13 do Arduino. Esta senha é validada no próprio banco de dados. Além disso, é possível cadastrar usuario (cadastro) e reserva de quartos através do Java Web do framework e por meio do [localhost](http://localhost:8080/).

# Itens:
  # Arduino Mega 2560 (pode ser usado Uno)
  # Cabo USB
  # Teclado 4x4
  # 8 conectores macho/macho
  # Framework Spring Boot Java
  # Banco de Dados PostgreSQL. Nome do banco: gestaoHotel
    # CREATE TABLE cadastro (id SERIAL NOT NULL PRIMARY KEY, usuario VARCHAR(50), senha VARCHAR(50), data_nascimento VARCHAR(50), num_documento VARCHAR(50), email VARCHAR(80), telefone VARCHAR(80))
    # CREATE TABLE reserva (id SERIAL NOT NULL PRIMARY KEY, usuario VARCHAR(50), senha VARCHAR(50), data_entrada VARCHAR(50), data_saida VARCHAR(50), quarto INTEGER)

# Biblioteca Java: 
  # jSerialComm-2.9.3.jar
  # postgresql-42.6.0

# Biblioteca Arduino:
  # Keypad Library for Arduino
