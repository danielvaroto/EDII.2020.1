package arvore;

import model.ResultadoOperacao;

public class ArvoreB implements IArvore {

    private int T;
    private No raiz;
    private ResultadoOperacao insertResult, buscaResult;

    // criação do nó
    public class No {

        //altura da arvore
        int n;
        //chave da arvore
        long chave[] = new long[2 * T - 1];
        //array com os filhos 
        No filho[] = new No[2 * T];
        //sempre inicia o no como folha
        boolean eFolha = true;
    }

    public ArvoreB(int t, int Entradas) {
        //define ordem da arvore
        T = t;
        raiz = new No();
        //altura da arvore inicial é 0
        raiz.n = 0;
        raiz.eFolha = true;
        //inicializa variaveis de comparaçoes 
        insertResult = new ResultadoOperacao(Entradas, 0, 0, 0);
        buscaResult = new ResultadoOperacao(Entradas, 0, 0, 0);
    }

    //metodo de inserir na arvore
    @Override
    public void inserir(long key) {
        No r = raiz;
        insertResult.incrementarQuantidadeCopias();
        if (r.n == 2 * T - 1) {
            No s = new No();
            raiz = s;
            s.eFolha = false;
            s.n = 0;
            s.filho[0] = r;
            insertResult.incrementarQuantidadeCopias();
            Dividi(s, 0, r);
            inserirValor(s, key);
        } else {
            inserirValor(r, key);
        }
    }

    @Override
    public boolean buscar(long k) {
        if (this.BuscaChave(raiz, k) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void aplicaTempoTotalInsercao(long tempoTotal) {
        insertResult.aplicarTempoProcessamentoMilissegundos(tempoTotal);
    }

    @Override
    public void aplicaTempoTotalBusca(long tempoTotal) {
        buscaResult.aplicarTempoProcessamentoMilissegundos(tempoTotal);
    }

    @Override
    public String buscaEstatisticasInsercao() {
        return insertResult.buscaTextoFormatado();
    }

    @Override
    public String buscaEstatisticasBusca() {
        return buscaResult.buscaTextoFormatado();
    }

    // procura chave
    private No BuscaChave(No x, long chave) {
        int i = 0;

        if (x == null) {
            return x;
        }
        for (i = 0; i < x.n; i++) {
            if (buscaResult.incrementarQuantidadeComparacoes() && chave < x.chave[i]) {
                break;
            }
            if (buscaResult.incrementarQuantidadeComparacoes() && chave == x.chave[i]) {
                return x;
            }
        }

        if (x.eFolha) {
            return null;
        } else {
            return BuscaChave(x.filho[i], chave);
        }
    }
    //dividi os elementos quando estourar o T subindo o menos
    private void Dividi(No x, int pos, No y) {
        insertResult.incrementarQuantidadeCopias();
        No z = new No();
        z.eFolha = y.eFolha;
        z.n = T - 1;
        for (int j = 0; j < T - 1; j++) {
            insertResult.incrementarQuantidadeCopias();

            z.chave[j] = y.chave[j + T];
        }
        if (!y.eFolha) {
            for (int j = 0; j < T; j++) {
                insertResult.incrementarQuantidadeCopias();

                z.filho[j] = y.filho[j + T];
            }
        }
        y.n = T - 1;
        for (int j = x.n; j >= pos + 1; j--) {
            insertResult.incrementarQuantidadeCopias();
            x.filho[j + 1] = x.filho[j];
        }
        x.filho[pos + 1] = z;

        for (int j = x.n - 1; j >= pos; j--) {
            insertResult.incrementarQuantidadeCopias();

            x.chave[j + 1] = x.chave[j];
        }
        x.chave[pos] = y.chave[T - 1];
        x.n = x.n + 1;
    }
    //insere o no na arvore
    final private void inserirValor(No x, long k) {

        if (x.eFolha) {
            int i = 0;
            for (i = x.n - 1; i >= 0 && k < x.chave[i]; i--) {
                insertResult.incrementarQuantidadeComparacoes();
                x.chave[i + 1] = x.chave[i];
            }
            x.chave[i + 1] = k;
            x.n = x.n + 1;
        } else {
            int i = 0;

            for (i = x.n - 1; i >= 0 && k < x.chave[i]; i--) {
                insertResult.incrementarQuantidadeComparacoes();
            };
            i++;
            No tmp = x.filho[i];
            if (tmp.n == 2 * T - 1) {
                Dividi(x, i, tmp);
                if (k > x.chave[i]) {
                    i++;
                }
            }
            inserirValor(x.filho[i], k);
        }

    }

    public void MostrarArvore() {
        Mostrar(raiz);
    }

    private void Mostrar(No x) {
        assert (x == null);
        for (int i = 0; i < x.n; i++) {
            System.out.print(x.chave[i] + " ");
        }
        if (!x.eFolha) {
            for (int i = 0; i < x.n + 1; i++) {
                Mostrar(x.filho[i]);
            }
        }
    }
}
