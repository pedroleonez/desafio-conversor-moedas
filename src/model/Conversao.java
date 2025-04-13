package model;

public class Conversao {
    private final String codigoAPI;
    private final String simboloOrigem;
    private final String simboloDestino;
    private final String nomeOrigem;
    private final String nomeDestino;

    public Conversao(String codigoAPI, String simboloOrigem, String simboloDestino,
                    String nomeOrigem, String nomeDestino) {
        this.codigoAPI = codigoAPI;
        this.simboloOrigem = simboloOrigem;
        this.simboloDestino = simboloDestino;
        this.nomeOrigem = nomeOrigem;
        this.nomeDestino = nomeDestino;
    }

    public String getCodigoAPI() {
        return codigoAPI;
    }

    public String getSimboloOrigem() {
        return simboloOrigem;
    }

    public String getSimboloDestino() {
        return simboloDestino;
    }

    public String getNomeOrigem() {
        return nomeOrigem;
    }

    public String getNomeDestino() {
        return nomeDestino;
    }
}
