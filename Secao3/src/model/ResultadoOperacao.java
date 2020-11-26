package model;

public class ResultadoOperacao {

    private final int quantidadeEntradas;
    private long quantidadeComparacoes;
    private long quantidadeCopias;
    private long tempoProcessamentoMilissegundos;

    public ResultadoOperacao(
            int quantidadeEntradas,
            long quantidadeComparacoes,
            long quantidadeCopias,
            long tempoProcessamentoMilissegundos) {
        this.quantidadeEntradas = quantidadeEntradas;
        this.quantidadeComparacoes = quantidadeComparacoes;
        this.quantidadeCopias = quantidadeCopias;
        this.tempoProcessamentoMilissegundos = tempoProcessamentoMilissegundos;
    }

    public int buscarQuantidadeEntradas() {
        return quantidadeEntradas;
    }

    public long buscarQuantidadeComparacoes() {
        return quantidadeComparacoes;
    }

    public boolean incrementarQuantidadeComparacoes() {
        this.quantidadeComparacoes++;

        return true;
    }

    public long buscarQuantidadeCopias() {
        return quantidadeCopias;
    }

    public void incrementarQuantidadeCopias() {
        this.quantidadeCopias++;
    }

    public long buscarTempoProcessamentoMilissegundos() {
        return tempoProcessamentoMilissegundos;
    }

    public void aplicarTempoProcessamentoMilissegundos(long tempoProcessamentoMilissegundos) {
        this.tempoProcessamentoMilissegundos += tempoProcessamentoMilissegundos;
    }

    public String buscaTextoFormatado() {
        String resultado = new StringBuilder()
                .append(this.buscarQuantidadeEntradas())
                .append(",")
                .append(this.buscarQuantidadeComparacoes())
                .append(",")
                .append(this.buscarQuantidadeCopias())
                .append(",")
                .append(this.buscarTempoProcessamentoMilissegundos())
                .toString();

        return resultado;
    }
}
