package model;

import java.util.Arrays;

public class Livro {
    
    private String nome;
    private int[] autores;

    public Livro(String nome, String autores) {
        this.nome = nome;
        this.autores = this.preencherAutores(autores);
    }

    public String buscarNome() {
        return nome;
    }

    public int[] buscarAutores() {
        return autores;
    }

    private int[] preencherAutores(String autores) {
        var autoresStringSemColchetes = autores.substring(0, autores.length() - 1).substring(1);
        if ("".equals(autoresStringSemColchetes))
            return new int[0];
        
        var autoresStringArray = autoresStringSemColchetes.split(",\\s?");

        // Converte para array de inteiros
        return Arrays.stream(autoresStringArray).mapToInt(Integer::parseInt).toArray();
    }
}
