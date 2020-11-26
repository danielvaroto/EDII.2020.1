package arvore;

import model.ArvoreResult;

public class ArvoreVermelhoPreto {

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
    private ArvoreResult insercaoResultado, buscaResultado;

    public ArvoreVermelhoPreto(int entradas) {
        insercaoResultado = new ArvoreResult(entradas, 0, 0, 0);
        buscaResultado = new ArvoreResult(entradas, 0, 0, 0);
    }
    
    public void aplicaTempoTotalInsercao(long tempoTotal) {
        insercaoResultado.IncrementProcessingTimeInMiliseconds(tempoTotal);
    }
    
    public void aplicaTempoTotalBusca(long tempoTotal) {
        buscaResultado.IncrementProcessingTimeInMiliseconds(tempoTotal);
    }
    
    public String buscaEstatisticasInsercao() {
        String resultado = new StringBuilder()
                    .append(insercaoResultado.getEntriesCount())
                    .append(",")
                    .append(insercaoResultado.getComparisonCount())
                    .append(",")
                    .append(insercaoResultado.getCopyCount())
                    .append(",")
                    .append(insercaoResultado.getProcessingTimeInMiliseconds())
                    .toString();
        
        return resultado;
    }
    
    public String buscaEstatisticasBusca() {
        String resultado = new StringBuilder()
                    .append(buscaResultado.getEntriesCount())
                    .append(",")
                    .append(buscaResultado.getComparisonCount())
                    .append(",")
                    .append(buscaResultado.getCopyCount())
                    .append(",")
                    .append(buscaResultado.getProcessingTimeInMiliseconds())
                    .toString();
        
        return resultado;
    }

    public void inserir(long valor) {
        raiz = inserir(raiz, valor);
        raiz.cor = CorArvoreVermelhoPreto.PRETO;
    }

    private NoArvoreVermelhoPreto inserir(NoArvoreVermelhoPreto no, long valor) {
        if (no == null) {
            return new NoArvoreVermelhoPreto(valor, CorArvoreVermelhoPreto.VERMELHO, 1);
        }

        if (insercaoResultado.IncrementComparisonCount() && valor < no.valor) {
            no.noFilhoEsquerdo = inserir(no.noFilhoEsquerdo, valor);
        } else if (insercaoResultado.IncrementComparisonCount() && valor > no.valor) {
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

    public boolean buscar(long valor) {
        NoArvoreVermelhoPreto no = raiz;

        while (no != null) {
            if (buscaResultado.IncrementComparisonCount() && valor < no.valor) {
                no = no.noFilhoEsquerdo;
            } else if (buscaResultado.IncrementComparisonCount() && valor > no.valor) {
                no = no.noFilhoDireito;
            } else {
                return true;
            }
        }

        return false;
    }

    private NoArvoreVermelhoPreto rotacionarParaDireita(NoArvoreVermelhoPreto no) {
        // incrementa quantidade de copias
        insercaoResultado.IncrementCopyCount();
        
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
        insercaoResultado.IncrementCopyCount();
        
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
