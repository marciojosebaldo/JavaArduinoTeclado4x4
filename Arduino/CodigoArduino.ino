// Fonte: https://www.robocore.net/tutoriais/usando-teclado-matricial-com-arduino
// Download biblioteca Password.h: https://playground.arduino.cc/Code/Password/
// Download biblioteca Keypad.h: https://playground.arduino.cc/Code/Keypad/

#include <Password.h> 
#include <Keypad.h>

const byte linhas = 4; // Números das linhas
const byte colunas = 4; // Número das colunas

const int ledVermelho = 11;
const int ledVerde = 13;

Password senha = Password("9999");

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
  
  pinMode(ledVermelho, OUTPUT);
  pinMode(ledVerde, OUTPUT);

  digitalWrite(ledVermelho, LOW);
  digitalWrite(ledVerde, LOW);
  
}

void loop() {
  
  char leituraTecla = tecladoHotel.getKey();
  
  if (leituraTecla) {
    
    if(leituraTecla == 'C') {
      
      if (senha.evaluate()) {
       
        //Serial.println("Senha confirmada!");

        Serial.println(senha.evaluate());
        delay(2000);
        
        for(int i = 0; i < 5; i++){ 
          digitalWrite(ledVerde, HIGH);
          delay(50);
          digitalWrite(ledVerde, LOW);
          delay(50);
          
        }
        
      } else {
        
        Serial.println("Senha incorreta!");
        
         for(int i = 0; i < 5; i++){ 
          digitalWrite(ledVermelho, HIGH);
          delay(50);
          digitalWrite(ledVermelho, LOW);
          delay(50);
          
        }
      }
      
      senha.reset();
      
    } else {
    Serial.println(leituraTecla); // Exibe a tecla pressionada
    senha.append(leituraTecla);
    }
  }
}
