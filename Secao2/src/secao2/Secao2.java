package secao2;

import arquivo.ArquivoUtils;
import estrutura.SondagemDuplaTabelaHash;
import model.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import ordenacao.MergeSort;

public class Secao2 {

    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        var argumentos = new ArgumentosEntrada(args);
        var tabelaLivros = new SondagemDuplaTabelaHash<Livro>();
        var tabelaAutores = ArquivoUtils.lerDatasetAutores(argumentos.caminhoDatasetAutores);
        var listaLivros = ArquivoUtils.lerDatasetLivros(argumentos.caminhoDatasetLivros);

        // Executa bloco N vezes (número de livros aleatorios a serem buscados)
        for (var i = 0; i < argumentos.n; i++) {
            // busca um livro aleatorio da lista de livros
            var livroAleatorio = ArquivoUtils.buscarAleatorio(listaLivros);

            // insere o livro aleatório na tabela de livros
            tabelaLivros.inserir(livroAleatorio.hashCode(), livroAleatorio);

            // acresce a quantidade de livros dos autores do livro aleatorio
            var autores = livroAleatorio.buscarAutores();
            for (var j = 0; j < autores.length; j++) {
                tabelaAutores.buscar(autores[j]).acrescerQuantidadeLivros();
            }
        }

        // transforma tabela de autores em array para ordenação
        var arrayAutores = Arrays.stream(tabelaAutores.buscarEntradas())
                .filter(x -> x != null)
                .map(x -> x.buscarValor())
                .collect(Collectors.toList())
                .toArray(new Autor[0]);

        // mergesort no array de autores pela quantidade de livros
        MergeSort.ordenarAutores(arrayAutores);

        // imprime os M autores com maior quantidade de livros na saida
        ArquivoUtils.salvarSaidaAutores(argumentos.caminhoSaida, arrayAutores, argumentos.m);
    }
}
