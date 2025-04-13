import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConversorMoedas {
    private static final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static final String BASE_URL = "https://economia.awesomeapi.com.br/json/last/";
    private static final Map<Integer, String[]> conversoes = new HashMap<>();

    static {
        // Opção, Moeda origem-destino, Nome moeda origem, Nome moeda destino
        conversoes.put(1, new String[]{"USD-ARS", "[USD]", "[ARS]"});
        conversoes.put(2, new String[]{"ARS-USD", "[ARS]", "[USD]"});
        conversoes.put(3, new String[]{"USD-BRL", "[USD]", "[BRL]"});
        conversoes.put(4, new String[]{"BRL-USD", "[BRL]", "[USD]"});
        conversoes.put(5, new String[]{"USD-COP", "[USD]", "[COP]"});
        conversoes.put(6, new String[]{"COP-USD", "[COP]", "[USD]"});
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 7) {
            exibirMenu();

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());

                if (opcao == 7) {
                    System.out.println("Obrigado por usar o conversor de moedas! Até a próxima.");
                    break;
                }

                if (opcao < 1 || opcao > 7) {
                    System.out.println("Opção inválida. Por favor, escolha uma opção entre 1 e 7.");
                    continue;
                }

                System.out.println("Digite o valor que deseja converter:");
                double valor = Double.parseDouble(scanner.nextLine().replace(",", ".").trim());

                String[] dadosConversao = conversoes.get(opcao);
                realizarConversao(dadosConversao[0], valor, dadosConversao[1], dadosConversao[2]);

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido.");
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }

            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\nSeja bem-vindo(a) ao conversor de moedas =]\n");
        System.out.println("1) Dólar ==> Peso argentino");
        System.out.println("2) Peso argentino ==> Dólar");
        System.out.println("3) Dólar ==> Real brasileiro");
        System.out.println("4) Real brasileiro ==> Dólar");
        System.out.println("5) Dólar ==> Peso colombiano");
        System.out.println("6) Peso colombiano ==> Dólar");
        System.out.println("7) Sair");
        System.out.println("\nEscolha uma opção válida:");
    }

    private static void realizarConversao(String parMoedas, double valor, String moedaOrigem, String moedaDestino)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + parMoedas))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Erro na requisição: " + response.statusCode());
        }

        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        String chave = parMoedas.replace("-", "");

        if (!jsonResponse.has(chave)) {
            throw new IOException("API não retornou dados para a conversão solicitada");
        }

        JsonObject dadosMoeda = jsonResponse.getAsJsonObject(chave);
        double cotacao = Double.parseDouble(dadosMoeda.get("bid").getAsString());
        double valorConvertido = valor * cotacao;

        System.out.printf("\nValor %.2f %s corresponde ao valor de =>>> %.2f %s%n",
                valor, moedaOrigem, valorConvertido, moedaDestino);
    }
}