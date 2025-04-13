package controller;

import model.Conversao;
import service.CotacaoService;
import ui.ConversorUI;
import util.OpcoesConversao;

public class ConversorController {
    private final ConversorUI ui;
    private final CotacaoService service;

    public ConversorController() {
        this.ui = new ConversorUI();
        this.service = new CotacaoService();
    }

    public void iniciar() {
        int opcao = 0;

        while (!OpcoesConversao.opcaoSair(opcao)) {
            ui.exibirMenu();
            opcao = ui.lerOpcao();

            if (OpcoesConversao.opcaoSair(opcao)) {
                ui.exibirMensagemDespedida();
                break;
            }

            if (!OpcoesConversao.opcaoValida(opcao)) {
                ui.exibirErro("Opção inválida. Por favor, escolha uma opção entre 1 e 7.");
                ui.aguardarTecla();
                continue;
            }

            double valor = ui.lerValor();
            if (valor < 0) {
                ui.aguardarTecla();
                continue;
            }

            try {
                Conversao conversao = OpcoesConversao.getConversao(opcao);
                double cotacao = service.obterCotacao(conversao.getCodigoAPI());
                double valorConvertido = valor * cotacao;

                ui.exibirResultado(valor, valorConvertido, conversao);
            } catch (Exception e) {
                ui.exibirErro(e.getMessage());
            }

            ui.aguardarTecla();
        }

        ui.fechar();
    }
}
