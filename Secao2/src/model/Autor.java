package model;

public class Autor {

    private int id, quantidadeLivros;
    private String nome;

    public Autor(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.quantidadeLivros = 0;
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
