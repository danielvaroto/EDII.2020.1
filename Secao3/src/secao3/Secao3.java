package secao3;

import arquivo.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import arvore.*;


public class Secao3 {

   
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
        List<String> titulos = ArquivoUtils.LerDataset(caminhoDataset);
        System.out.println("Finalizada leitura do arquivo do dataset.");

        // Cria array com os tamanhos de entrada
        System.out.println("Iniciando leitura do arquivo de entrada.");
        int[] tamanhos = ArquivoUtils.LerTamanhoEntradas(caminhoEntrada);
        System.out.println("Finalizada leitura do arquivo de entrada.");


        // Loop de tamanhos para executar todos os tamanhos
        System.out.println("Iniciando inserção e busca na arvore.");
        for (int tamanho : tamanhos) {
            // Cada tamanho é executado cinco vezes
            for (int i = 0; i < 5; i++) {
                // Cria array de titulos aleatorio no tamanho especificado
                String[] entradasAleatorias = ArquivoUtils.BuscarAleatorio(titulos, tamanho);
            }

            System.out.println("Tamanho de entradas " + tamanho + " executado.");
        }
        System.out.println("Finalizada execucao dos algoritmos inserção e busca na arvore.");

        System.out.println("Iniciando salvamento de dados no arquivo de saida.");
        
        System.out.println("Finalizado salvamento de dados no arquivo de saida.");
    }
    
}
