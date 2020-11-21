package secao2;

import arquivo.ArquivoUtils;
import estrutura.SondagemDuplaTabelaHash;
import model.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import ordenacao.MergeSort;

public class Secao2 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        var argumentos = new ArgumentosEntrada(args);
        var tabelaLivros = new SondagemDuplaTabelaHash<Livro>();
        var tabelaAutores = ArquivoUtils.lerDatasetAutores(argumentos.caminhoDatasetAutores);
        var listaLivros = ArquivoUtils.lerDatasetLivros(argumentos.caminhoDatasetLivros);

        for (var i = 0; i < argumentos.n; i++) {
            Livro livroAleatorio = ArquivoUtils.buscarAleatorio(listaLivros);

            tabelaLivros.inserir(livroAleatorio.hashCode(), livroAleatorio);

            var autores = livroAleatorio.buscarAutores();
            for (var j = 0; j < autores.length; j++) {
                tabelaAutores.buscar(autores[j]).acrescerQuantidadeLivros();
            }
        }

        var arrayAutores = Arrays.stream(tabelaAutores.buscarEntradas())
                .filter(x -> x != null)
                .map(x -> x.buscarValor())
                .collect(Collectors.toList())
                .toArray(new Autor[0]);

        MergeSort.ordenarAutores(arrayAutores);
        
        ArquivoUtils.salvarSaidaAutores(argumentos.caminhoSaida, arrayAutores, argumentos.m);
        
        // loop N livros
        //      pega um livro random
        //      ve se ja ta na tabela
        //      acresce a quantidade de livros do autor
        // quicksort ou mergesort na tabela de autores pela quantidade de livros
        // imprime os M autores com maior quantidade de livros na saida
    }

}
