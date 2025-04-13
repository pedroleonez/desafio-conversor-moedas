package service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class CotacaoService {
    private static final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static final String BASE_URL = "https://economia.awesomeapi.com.br/json/last/";

    public double obterCotacao(String parMoedas) throws IOException, InterruptedException {
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
        return Double.parseDouble(dadosMoeda.get("bid").getAsString());
    }
}
