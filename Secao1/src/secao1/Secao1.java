package secao1;

import file.FileUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.*;
import sort.*;

public class Secao1 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        String caminhoDataset;
        String caminhoEntrada;
        String caminhoSaida;

        // Verifica se os caminhos dos arquivos foram enviados
        if (args.length < 1) {
            System.out.println("Caminho do arquivo do dataset nao especificado, executando com arquivo '.\\exemploDataset.csv'.");
        }
        if (args.length < 2) {
            System.out.println("Caminho do arquivo de entrada nao especificado, executando com arquivo '.\\exemploEntrada.txt'.");
        }
        if (args.length < 3) {
            System.out.println("Caminho do arquivo da saida nao especificado, executando com arquivo '.\\exemploSaida.txt'.");
        }

        // Preenche os caminhos dos arquivos, utiliza valores padrao se nao forem passados pelo usuarios
        caminhoDataset = (args.length < 1) ? "./exemploDataset.csv" : args[0];
        caminhoEntrada = (args.length < 2) ? "./exemploEntrada.txt" : args[1];
        caminhoSaida = (args.length < 3) ? "./exemploSaida.txt" : args[2];

        // Cria lista com todos os titulos
        System.out.println("Iniciando leitura do arquivo do dataset.");
        List<String> titulos = FileUtils.LerDataset(caminhoDataset);
        System.out.println("Finalizada leitura do arquivo do dataset.");

        // Cria array com os tamanhos de entrada
        System.out.println("Iniciando leitura do arquivo de entrada.");
        int[] tamanhos = FileUtils.LerTamanhoEntradas(caminhoEntrada);
        System.out.println("Finalizada leitura do arquivo de entrada.");

        List<SortResult> resultadosQuickSort = new ArrayList<>();
        List<SortResult> resultadosMergeSort = new ArrayList<>();

        // Loop de tamanhos para executar todos os tamanhos
        System.out.println("Iniciando execucao dos algoritmos de ordenacao.");
        for (int tamanho : tamanhos) {
            // Cada tamanho é executado cinco vezes
            for (int i = 0; i < 5; i++) {
                // Cria array de titulos aleatorio no tamanho especificado
                String[] entradasAleatorias = FileUtils.BuscarAleatorio(titulos, tamanho);

                // Executa os algoritmos de ordenacao na mesma entrada aleatoria
                SortResult resultadoQuickSort = QuickSort.Sort(entradasAleatorias);
                SortResult resultadoMergeSort = MergeSort.Sort(entradasAleatorias);

                // Salva os resultados na lista de resultados
                resultadosQuickSort.add(resultadoQuickSort);
                resultadosMergeSort.add(resultadoMergeSort);
            }

            System.out.println("Tamanho de entradas " + tamanho + " executado.");
        }
        System.out.println("Finalizada execucao dos algoritmos de ordenacao.");

        // Grava todos os resultados do QuickSort
        System.out.println("Iniciando salvamento de dados no arquivo de saida.");
        FileUtils.SalvaEstatisticasOrdenacao(caminhoSaida, "QuickSort", resultadosQuickSort);
        FileUtils.SalvaEstatisticasOrdenacao(caminhoSaida, "MergeSort", resultadosMergeSort);
        System.out.println("Finalizado salvamento de dados no arquivo de saida.");
    }
}
