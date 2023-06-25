package InciandoNoSpring.PrimeiraAplicacao;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import InciandoNoSpring.PrimeiraAplicacao.Model.M_Usuario;
import InciandoNoSpring.PrimeiraAplicacao.Service.S_Arduino;
import InciandoNoSpring.PrimeiraAplicacao.Service.S_Usuario;
import com.fazecast.jSerialComm.SerialPort;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class PrimeiraAplicacaoApplication {

	private static final String PORT_NAME = "COM3";
	private static final int BAUD_RATE = 9600;
	private static final int READ_TIMEOUT = 1000; // Tempo de espera em milissegundos

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
							System.out.println(senha);

							M_Usuario usuario = S_Arduino.loginArduino(senha);
							senha = "";

							if (usuario != null) {

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