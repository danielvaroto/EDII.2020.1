package arvore;

import model.ResultadoOperacao;

public class ArvoreVermelhoPreto implements IArvore {

    private enum CorArvoreVermelhoPreto {
        VERMELHO,
        PRETO
    }

    private class NoArvoreVermelhoPreto {

        private long valor;
        private NoArvoreVermelhoPreto noFilhoEsquerdo, noFilhoDireito;
        private CorArvoreVermelhoPreto cor;
        private int tamanho;

        private NoArvoreVermelhoPreto(long valor, CorArvoreVermelhoPreto cor, int tamanho) {
            this.valor = valor;
            this.cor = cor;
            this.tamanho = tamanho;
        }

        private boolean ehVermelho() {
            return this.cor == CorArvoreVermelhoPreto.VERMELHO;
        }

        private boolean ehPreto() {
            return this.cor == CorArvoreVermelhoPreto.PRETO;
        }

        private void inverterCor() {
            if (this.ehVermelho()) {
                this.cor = CorArvoreVermelhoPreto.PRETO;
            } else if (this.ehPreto()) {
                this.cor = CorArvoreVermelhoPreto.VERMELHO;
            }
        }
    }

    private NoArvoreVermelhoPreto raiz;
    private ResultadoOperacao insercaoResultado, buscaResultado;

    public ArvoreVermelhoPreto(int entradas) {
        insercaoResultado = new ResultadoOperacao(entradas, 0, 0, 0);
        buscaResultado = new ResultadoOperacao(entradas, 0, 0, 0);
    }

    @Override
    public void inserir(long valor) {
        raiz = inserir(raiz, valor);
        raiz.cor = CorArvoreVermelhoPreto.PRETO;
    }

    @Override
    public boolean buscar(long valor) {
        NoArvoreVermelhoPreto no = raiz;

        while (no != null) {
            if (buscaResultado.incrementarQuantidadeComparacoes() && valor < no.valor) {
                no = no.noFilhoEsquerdo;
            } else if (buscaResultado.incrementarQuantidadeComparacoes() && valor > no.valor) {
                no = no.noFilhoDireito;
            } else {
                return true;
            }
        }

        return false;
    }

    @Override
    public void aplicaTempoTotalInsercao(long tempoTotal) {
        insercaoResultado.aplicarTempoProcessamentoMilissegundos(tempoTotal);
    }

    @Override
    public void aplicaTempoTotalBusca(long tempoTotal) {
        buscaResultado.aplicarTempoProcessamentoMilissegundos(tempoTotal);
    }

    @Override
    public String buscaEstatisticasInsercao() {
        return insercaoResultado.buscaTextoFormatado();        
    }

    @Override
    public String buscaEstatisticasBusca() {
        return buscaResultado.buscaTextoFormatado();
    }

    private NoArvoreVermelhoPreto inserir(NoArvoreVermelhoPreto no, long valor) {
        if (no == null) {
            return new NoArvoreVermelhoPreto(valor, CorArvoreVermelhoPreto.VERMELHO, 1);
        }

        if (insercaoResultado.incrementarQuantidadeComparacoes() && valor < no.valor) {
            no.noFilhoEsquerdo = inserir(no.noFilhoEsquerdo, valor);
        } else if (insercaoResultado.incrementarQuantidadeComparacoes() && valor > no.valor) {
            no.noFilhoDireito = inserir(no.noFilhoDireito, valor);
        } else {
            no.valor = valor;
        }

        if (ehVermelho(no.noFilhoDireito) && !ehVermelho(no.noFilhoEsquerdo)) {
            no = rotacionarParaEsquerda(no);
        }
        if (ehVermelho(no.noFilhoEsquerdo) && ehVermelho(no.noFilhoEsquerdo.noFilhoEsquerdo)) {
            no = rotacionarParaDireita(no);
        }
        if (ehVermelho(no.noFilhoEsquerdo) && ehVermelho(no.noFilhoDireito)) {
            inverterCores(no);
        }

        no.tamanho = buscarTamanho(no.noFilhoEsquerdo) + buscarTamanho(no.noFilhoDireito) + 1;

        return no;
    }

    private boolean ehVermelho(NoArvoreVermelhoPreto no) {
        return no == null ? false : no.ehVermelho();
    }

    private NoArvoreVermelhoPreto rotacionarParaDireita(NoArvoreVermelhoPreto no) {
        // incrementa quantidade de copias
        insercaoResultado.incrementarQuantidadeCopias();

        var noFilhoEsquerdo = no.noFilhoEsquerdo;

        // rotaciona
        no.noFilhoEsquerdo = noFilhoEsquerdo.noFilhoDireito;
        noFilhoEsquerdo.noFilhoDireito = no;

        // ajusta cores
        noFilhoEsquerdo.cor = noFilhoEsquerdo.noFilhoDireito.cor;
        noFilhoEsquerdo.noFilhoDireito.cor = CorArvoreVermelhoPreto.VERMELHO;

        // ajusta tamanho
        noFilhoEsquerdo.tamanho = no.tamanho;
        no.tamanho = buscarTamanho(no.noFilhoEsquerdo) + buscarTamanho(no.noFilhoDireito) + 1;

        return noFilhoEsquerdo;
    }

    private NoArvoreVermelhoPreto rotacionarParaEsquerda(NoArvoreVermelhoPreto no) {
        // incrementa quantidade de copias
        insercaoResultado.incrementarQuantidadeCopias();

        var noFilhoDireito = no.noFilhoDireito;

        // rotaciona
        no.noFilhoDireito = noFilhoDireito.noFilhoEsquerdo;
        noFilhoDireito.noFilhoEsquerdo = no;

        // ajusta cores
        noFilhoDireito.cor = noFilhoDireito.noFilhoEsquerdo.cor;
        noFilhoDireito.noFilhoEsquerdo.cor = CorArvoreVermelhoPreto.VERMELHO;

        // ajusta tamanho
        noFilhoDireito.tamanho = no.tamanho;
        no.tamanho = buscarTamanho(no.noFilhoEsquerdo) + buscarTamanho(no.noFilhoDireito) + 1;

        return noFilhoDireito;
    }

    private void inverterCores(NoArvoreVermelhoPreto no) {
        no.inverterCor();
        no.noFilhoEsquerdo.inverterCor();
        no.noFilhoDireito.inverterCor();
    }

    private int buscarTamanho(NoArvoreVermelhoPreto no) {
        return no == null ? 0 : no.tamanho;
    }
}
