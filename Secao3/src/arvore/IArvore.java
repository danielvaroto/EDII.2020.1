package arvore;

public interface IArvore {
    void inserir(long key);
    boolean buscar(long k);
    void aplicaTempoTotalInsercao(long tempoTotal);
    void aplicaTempoTotalBusca(long tempoTotal);
    String buscaEstatisticasInsercao();
    String buscaEstatisticasBusca();
}
