package secao3;

import arquivo.*;
import java.io.IOException;
import java.util.List;
import arvore.*;
import java.util.ArrayList;
import model.ArgumentosEntrada;

public class Secao3 {

    public static void main(String[] args) throws IOException {
        var argumentos = new ArgumentosEntrada(args);

        System.out.println("Iniciando leitura do arquivo do dataset.");
        List<String> idsLivros = ArquivoUtils.LerDataset(argumentos.caminhoDatasetLivros);

        System.out.println("Iniciando leitura do arquivo de entrada.");
        int[] tamanhos = ArquivoUtils.LerTamanhoEntradas(argumentos.caminhoEntrada);

        List<String> estatisticasInsercao = new ArrayList<>();
        List<String> estatisticasBusca = new ArrayList<>();
        List<String> estatisticasInsercaoB = new ArrayList<>();
        List<String> estatisticasBuscaB = new ArrayList<>();
        List<String> estatisticasInsercaoB2 = new ArrayList<>();
        List<String> estatisticasBuscaB2 = new ArrayList<>();

        System.out.println("Iniciando inserção e busca na arvore.");
        for (int tamanho : tamanhos) {
            // Cada tamanho é executado cinco vezes
            for (int i = 0; i < 5; i++) {
                // Cria array de titulos aleatorio no tamanho especificado
                long[] entradasAleatorias = ArquivoUtils.BuscarAleatorio(idsLivros, tamanho);
                ArvoreVermelhoPreto arvoreVP = new ArvoreVermelhoPreto(tamanho);
                ArvoreB arvoreB = new ArvoreB(2, tamanho);
                ArvoreB arvoreB2 = new ArvoreB(20, tamanho);

                // ARVORE VERMELHO-PRETO
                ExecutaInsercaoEBusca(tamanho,
                        arvoreVP,
                        entradasAleatorias,
                        estatisticasInsercao,
                        estatisticasBusca);

                // ARVORE B DE ORDEM 2
                ExecutaInsercaoEBusca(tamanho,
                        arvoreB,
                        entradasAleatorias,
                        estatisticasInsercaoB,
                        estatisticasBuscaB);

                // ARVORE B DE ORDEM 20
                ExecutaInsercaoEBusca(tamanho,
                        arvoreB2,
                        entradasAleatorias,
                        estatisticasInsercaoB2,
                        estatisticasBuscaB2);
            }
        }

        System.out.println("Iniciando salvamentos de estatítica nos arquivos de saída.");
        ArquivoUtils.SalvaEstatisticasOrdenacao(
                argumentos.caminhoSaidaInsercao,
                estatisticasInsercao,
                estatisticasInsercaoB,
                estatisticasInsercaoB2);

        ArquivoUtils.SalvaEstatisticasOrdenacao(
                argumentos.caminhoSaidaBusca,
                estatisticasBusca,
                estatisticasBuscaB,
                estatisticasBuscaB2);
    }

    private static void ExecutaInsercaoEBusca(int tamanho,
            IArvore arvore,
            long[] entradasAleatorias,
            List<String> estatisticasInsercao,
            List<String> estatisticasBusca) {
        // EXECUTA INSERCAO
        long tempoInicial = System.currentTimeMillis(); // Grava tempo de inicio
        for (int j = 0; j < tamanho; j++) {
            arvore.inserir(entradasAleatorias[j]);
        }
        long tempoFinal = System.currentTimeMillis(); // Grava tempo do fim
        arvore.aplicaTempoTotalInsercao(tempoFinal - tempoInicial);

        // EXECUTA BUSCA
        tempoInicial = System.currentTimeMillis(); // Grava tempo de inicio
        for (int j = 0; j < (tamanho / 10) + 1; j++) {
            arvore.buscar(entradasAleatorias[j]);
        }
        tempoFinal = System.currentTimeMillis(); // Grava tempo do fim
        arvore.aplicaTempoTotalBusca(tempoFinal - tempoInicial); // Calcula e salva tempo

        // SALVA ESTATISTICAS
        estatisticasInsercao.add(arvore.buscaEstatisticasInsercao());
        estatisticasBusca.add(arvore.buscaEstatisticasBusca());
    }
}
