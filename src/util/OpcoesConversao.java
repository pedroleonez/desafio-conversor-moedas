package util;

import model.Conversao;

import java.util.HashMap;
import java.util.Map;

public class OpcoesConversao {
    private static final Map<Integer, Conversao> conversoes = new HashMap<>();

    static {
        // Opção: código API, símbolo origem, símbolo destino, nome origem, nome destino
        conversoes.put(1, new Conversao("USD-ARS", "USD", "ARS", "[USD]", "[ARS]"));
        conversoes.put(2, new Conversao("ARS-USD", "ARS", "USD", "[ARS]", "[UDS]"));
        conversoes.put(3, new Conversao("USD-BRL", "USD", "BRL", "[USD]", "[BRL]"));
        conversoes.put(4, new Conversao("BRL-USD", "BRL", "USD", "[BRL]", "[USD]"));
        conversoes.put(5, new Conversao("USD-COP", "USD", "COP", "[USD]", "[COP]"));
        conversoes.put(6, new Conversao("COP-USD", "COP", "USD", "[COP]", "[USD]"));
    }

    public static Conversao getConversao(int opcao) {
        return conversoes.get(opcao);
    }

    public static boolean opcaoValida(int opcao) {
        return opcao >= 1 && opcao <= 7;
    }

    public static boolean opcaoSair(int opcao) {
        return opcao == 7;
    }
}
