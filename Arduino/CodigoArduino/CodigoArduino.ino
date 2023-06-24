#include <Keypad.h>

const byte linhas = 4; // Números das linhas
const byte colunas = 4; // Número das colunas

const char teclasMatriz[linhas][colunas] = { // Matriz de caracteres (mapeamento do teclado)
  {'1', '2', '3', 'A'},
  {'4', '5', '6', 'B'},
  {'7', '8', '9', 'C'},
  {'*', '0', '#', 'D'}
};

const byte pinosLinhas[linhas] = {22, 23, 26, 27}; // Pinos de conexão das linhas
const byte pinosColunas[colunas] = {30, 31, 34, 35}; // Pinos de conexão das colunas

Keypad tecladoHotel = Keypad(makeKeymap(teclasMatriz), pinosLinhas, pinosColunas, linhas, colunas);

void setup() {
  Serial.begin(9600); // Inicia porta serial
}

void loop() {
  char leituraTecla = tecladoHotel.getKey();

  if (leituraTecla) {
    Serial.println(leituraTecla); // Exibe a tecla pressionada

    while (tecladoHotel.getKey() != NO_KEY); // Aguarda até que a tecla seja liberada
    delay(200); // Pequeno atraso para evitar leituras múltiplas da tecla "C"
  }
}
