package ui;

import model.Conversao;
import util.OpcoesConversao;

import java.util.Scanner;

public class ConversorUI {
    private final Scanner scanner;

    public ConversorUI() {
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
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

    public int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, digite um número válido.");
            return -1;
        }
    }

    public double lerValor() {
        System.out.println("Digite o valor que deseja converter:");
        try {
            return Double.parseDouble(scanner.nextLine().replace(",", ".").trim());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido. Por favor, digite um número válido.");
            return -1;
        }
    }

    public void exibirResultado(double valorOriginal, double valorConvertido, Conversao conversao) {
        System.out.printf("\nValor %.2f %s corresponde ao valor de =>>> %.2f %s%n",
                valorOriginal, conversao.getNomeOrigem(), valorConvertido, conversao.getNomeDestino());
    }

    public void exibirErro(String mensagem) {
        System.out.println("Erro: " + mensagem);
    }

    public void aguardarTecla() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    public void exibirMensagemDespedida() {
        System.out.println("Obrigado por usar o conversor de moedas! Até a próxima.");
    }

    public void fechar() {
        scanner.close();
    }
}
