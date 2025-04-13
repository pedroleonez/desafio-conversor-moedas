# Conversor de Moedas

Um aplicativo em Java que permite converter valores entre diferentes moedas utilizando a API pública da AwesomeAPI.

## Descrição

Este programa oferece um conversor de moedas com interface de linha de comando que permite realizar conversões entre as seguintes moedas:
- Dólar (USD)
- Real Brasileiro (BRL)
- Peso Argentino (ARS)
- Peso Colombiano (COP)

O programa utiliza dados de cotação em tempo real obtidos através da API da AwesomeAPI.

## Recursos

- Consulta em tempo real de cotações de moedas
- Interface de usuário via linha de comando
- Conversão entre múltiplos pares de moedas
- Tratamento de erros para entradas inválidas e falhas de conexão

## Pré-requisitos

- Java 21
- Dependência da biblioteca Gson para processamento JSON
- Conexão com a internet para acessar a API

## Como executar

1. Compile o programa:
   ```
   javac -cp ".:gson-2.10.1.jar" ConversorMoedas.java
   ```

2. Execute o programa:
   ```
   java -cp ".:gson-2.10.1.jar" ConversorMoedas
   ```

## Como usar

1. Quando o programa iniciar, você verá um menu com as opções de conversão disponíveis
2. Digite o número correspondente à conversão desejada
3. Insira o valor que deseja converter
4. O resultado da conversão será exibido
5. Pressione ENTER para continuar e repetir o processo ou escolha a opção "Sair" para encerrar

## API utilizada

Este projeto utiliza a API gratuita de cotações de moedas da AwesomeAPI:
```
https://economia.awesomeapi.com.br/json/last/:moedas
```

## Estrutura do projeto

- `ConversorMoedas.java`: Contém toda a lógica do programa, incluindo interface com usuário e comunicação com a API

## Tecnologias utilizadas

- Java 21
- Gson (para processamento de JSON)
- Scanner (para entrada de dados do usuário)

## Autor

Pedro Leonez
