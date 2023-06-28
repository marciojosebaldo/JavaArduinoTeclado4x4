package InciandoNoSpring.PrimeiraAplicacao;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import InciandoNoSpring.PrimeiraAplicacao.Model.M_Cadastro;
import InciandoNoSpring.PrimeiraAplicacao.Service.S_Arduino;
import com.fazecast.jSerialComm.SerialPort;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PrimeiraAplicacaoApplication {

	private static final String PORT_NAME = "/dev/ttyUSB0"; // Porta Linux Ubuntu
	private static final int BAUD_RATE = 9600; // Velocidade em bits
	private static final int READ_TIMEOUT = 200; // Tempo de espera em milissegundos

	public static void main(String[] args) {

		SpringApplication.run(PrimeiraAplicacaoApplication.class, args);

		SerialPort serialPort = SerialPort.getCommPort(PORT_NAME);
		serialPort.setBaudRate(BAUD_RATE);
		if (!serialPort.openPort()) {
			System.err.println("Falha ao se comunicar com a porta serial.");
			return;
		}

		InputStream inputStream = serialPort.getInputStream();
		byte[] buffer = new byte[1024];
		List<String> senhaArduino = new ArrayList<>();

		while (true) {
			try {
				if (inputStream.available() > 0) {
					int leituraCaractere = inputStream.read(buffer);
					if (leituraCaractere > 0) {
						String caractereFormatado = new String(buffer, 0, leituraCaractere, StandardCharsets.UTF_8);
						caractereFormatado = caractereFormatado.trim();
						caractereFormatado = caractereFormatado.toUpperCase();
						System.out.println("Caractere recebido: " + caractereFormatado);
						senhaArduino.add(caractereFormatado);

						if (caractereFormatado.equals("C")) {
							senhaArduino.remove(senhaArduino.size() - 1); // Para remover o C

							StringBuilder builder = new StringBuilder();

							for (String elemento : senhaArduino) {
								builder.append(elemento);
							}

							String senha = builder.toString();

							// Refazer o código abaixo para o Arduino importar os dados da leitura
							M_Cadastro cadastro = S_Arduino.loginArduino(senha);

							if (cadastro != null) {
                                // Envia comando para o Arduino
								System.out.println("Usuário válido. Enviando comando para o Arduino.");
								String abrePorta = "abrePorta";
								serialPort.getOutputStream().write(abrePorta.getBytes());
								senha = "";
								senhaArduino.clear();
							} else {
								System.out.println("Usuário inválido. Comando não enviado para o Arduino.");
								senha = "";
								senhaArduino.clear();
							}
						}
					}
				} else {
					Thread.sleep(READ_TIMEOUT);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}