package InciandoNoSpring.PrimeiraAplicacao;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.fazecast.jSerialComm.SerialPort;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class PrimeiraAplicacaoApplication {

	private static final String PORT_NAME = "COM3";
	private static final int BAUD_RATE = 9600;
	private static final int READ_TIMEOUT = 2000; // Tempo de espera em milissegundos

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

		while (true) {
			try {
				if (inputStream.available() > 0) {
					int bytesRead = inputStream.read(buffer);
					if (bytesRead > 0) {
						String receivedData = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
						System.out.println("Caractere recebido: " + receivedData);

						if (receivedData == "C" || receivedData == "c") {
							// Chamar Service



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