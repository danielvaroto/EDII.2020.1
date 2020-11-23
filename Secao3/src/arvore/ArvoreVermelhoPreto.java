package arvore;

public class ArvoreVermelhoPreto {

    private enum CorArvoreVermelhoPreto {
        VERMELHO,
        PRETO
    }

    private class NoArvoreVermelhoPreto {

        private int valor;
        private NoArvoreVermelhoPreto noFilhoEsquerdo, noFilhoDireito;
        private CorArvoreVermelhoPreto cor;
        private int tamanho;

        private NoArvoreVermelhoPreto(int valor, CorArvoreVermelhoPreto cor, int tamanho) {
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

    public void inserir(int valor) {
        raiz = put(raiz, valor);
        raiz.cor = CorArvoreVermelhoPreto.PRETO;
    }

    private NoArvoreVermelhoPreto put(NoArvoreVermelhoPreto no, int valor) {
        if (no == null) {
            return new NoArvoreVermelhoPreto(valor, CorArvoreVermelhoPreto.VERMELHO, 1);
        }

        if (valor < no.valor) {
            no.noFilhoEsquerdo = put(no.noFilhoEsquerdo, valor);
        } else if (valor > no.valor) {
            no.noFilhoDireito = put(no.noFilhoDireito, valor);
        } else {
            no.valor = valor;
        }

        if (no.noFilhoDireito.ehVermelho() && no.noFilhoEsquerdo.ehPreto()) {
            no = rotacionarParaEsquerda(no);
        }
        if (no.noFilhoEsquerdo.ehVermelho() && no.noFilhoEsquerdo.noFilhoEsquerdo.ehVermelho()) {
            no = rotacionarParaDireita(no);
        }
        if (no.noFilhoEsquerdo.ehVermelho() && no.noFilhoDireito.ehVermelho()) {
            inverterCores(no);
        }

        no.tamanho = buscarTamanho(no.noFilhoEsquerdo) + buscarTamanho(no.noFilhoDireito) + 1;

        return no;
    }

    private NoArvoreVermelhoPreto rotacionarParaDireita(NoArvoreVermelhoPreto no) {
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
