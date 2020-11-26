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

        // Cria lista com todos os titulos
        System.out.println("Iniciando leitura do arquivo do dataset.");
        List<String> idsLivros = ArquivoUtils.LerDataset(argumentos.caminhoDatasetLivros);
        System.out.println("Finalizada leitura do arquivo do dataset.");

        // Cria array com os tamanhos de entrada
        System.out.println("Iniciando leitura do arquivo de entrada.");
        int[] tamanhos = ArquivoUtils.LerTamanhoEntradas(argumentos.caminhoEntrada);
        System.out.println("Finalizada leitura do arquivo de entrada.");

        // Loop de tamanhos para executar todos os tamanhos
        System.out.println("Iniciando inserção e busca na arvore.");
        
        List<String> estatisticasInsercao = new ArrayList<>();
        List<String> estatisticasBusca = new ArrayList<>();
        
        for (int tamanho : tamanhos) {
            // Cada tamanho é executado cinco vezes
            for (int i = 0; i < 5; i++) {
                // Cria array de titulos aleatorio no tamanho especificado
                long[] entradasAleatorias = ArquivoUtils.BuscarAleatorio(idsLivros, tamanho);
                ArvoreVermelhoPreto arvoreVP = new ArvoreVermelhoPreto(tamanho);
                ArvoreB arvoreB = new ArvoreB(2, tamanho);
                ArvoreB arvoreB2 = new ArvoreB(20, tamanho);
                
                // Grava tempo de inicio
                long tempoInicial = System.currentTimeMillis();
                for (int j = 0; j < tamanho; j++) {
                    arvoreVP.inserir(entradasAleatorias[j]);
                }
                // Grava tempo do fim
                long tempoFinal = System.currentTimeMillis();
                arvoreVP.aplicaTempoTotalInsercao(tempoFinal - tempoInicial);
                
                estatisticasInsercao.add(arvoreVP.buscaEstatisticasInsercao());
                
                // Grava tempo de inicio
                tempoInicial = System.currentTimeMillis();
                for (int j = 0; j < (tamanho / 10)+1; j++) {
                    arvoreVP.buscar(entradasAleatorias[j]);
                }
                // Grava tempo do fim
                tempoFinal = System.currentTimeMillis();
                arvoreVP.aplicaTempoTotalInsercao(tempoFinal - tempoInicial);
                
                estatisticasBusca.add(arvoreVP.buscaEstatisticasBusca());

                // Grava tempo de inicio
                tempoInicial = System.currentTimeMillis();
                for (int j = 0; j < tamanho; j++) {
                    arvoreB.Inserir(entradasAleatorias[j]);
                }
                // Grava tempo do fim
                tempoFinal = System.currentTimeMillis();
                arvoreB.setTimeInsert(tempoFinal - tempoInicial);

                // Grava tempo de inicio
                tempoInicial = System.currentTimeMillis();
                for (int j = 0; j < (tamanho / 10)+1; j++) {
                    arvoreB.Buscar(entradasAleatorias[j]);
                }
                // Grava tempo do fim
                tempoFinal = System.currentTimeMillis();
                arvoreB.setTimeBusca(tempoFinal - tempoInicial);

                // Grava tempo de inicio
                tempoInicial = System.currentTimeMillis();
                for (int j = 0; j < tamanho; j++) {
                    arvoreB2.Inserir(entradasAleatorias[j]);
                }
                // Grava tempo do fim
                tempoFinal = System.currentTimeMillis();
                arvoreB.setTimeInsert(tempoFinal - tempoInicial);

                // Grava tempo de inicio
                tempoInicial = System.currentTimeMillis();
                for (int j = 0; j < (tamanho / 10)+1; j++) {
                    arvoreB2.Buscar(entradasAleatorias[j]);
                }
                // Grava tempo do fim
                tempoFinal = System.currentTimeMillis();
                arvoreB.setTimeBusca(tempoFinal - tempoInicial);
            }
        }

        ArquivoUtils.SalvaEstatisticasOrdenacao(argumentos.caminhoSaidaInsercao, estatisticasInsercao);
        ArquivoUtils.SalvaEstatisticasOrdenacao(argumentos.caminhoSaidaBusca, estatisticasBusca);
    }

}
