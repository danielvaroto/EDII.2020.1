package model;

public class Autor {

    private int id, quantidadeLivros;
    private String nome;

    public Autor(int id, int quantidadeLivros, String nome) {
        this.id = id;
        this.quantidadeLivros = quantidadeLivros;
        this.nome = nome;
    }

    public void acrescerQuantidadeLivros() {
        this.quantidadeLivros++;
    }

    public int buscarId() {
        return this.id;
    }

    public int buscarQuantidadeLivros() {
        return this.quantidadeLivros;
    }

    public String buscarNome() {
        return this.nome;
    }
}
