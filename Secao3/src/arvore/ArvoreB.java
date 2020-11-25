package arvore;

import model.ArvoreResult;

public class ArvoreB {

    private int T;
    private No raiz;
    private ArvoreResult insertResult, buscaResult;

    // criação do nó
    public class No {

        int n;
        int key[] = new int[2 * T - 1];
        No filho[] = new No[2 * T];
        boolean eFolha = true;

        public int BuscaChave(int k) {
            for (int i = 0; i < this.n; i++) {
                if (this.key[i] == k) {
                    return i;
                }
            }
            return -1;
        }
    ;

    }

  public ArvoreB(int t, int Entradas) {
        T = t;
        raiz = new No();
        raiz.n = 0;
        raiz.eFolha = true;
        insertResult = new ArvoreResult(Entradas, 0, 0, 0);
        buscaResult = new ArvoreResult(Entradas, 0, 0, 0);
    }

    // Search key
    private No BuscaKey(No x, int key) {
        int i = 0;
        buscaResult.IncrementComparisonCount();
        if (x == null) {
            return x;
        }
        for (i = 0; i < x.n; i++) {
            buscaResult.IncrementComparisonCount();
            if (key < x.key[i]) {
                break;
            }
            if (key == x.key[i]) {
                return x;
            }
        }
        if (x.eFolha) {
            return null;
        } else {
            return BuscaKey(x.filho[i], key);
        }
    }

    private void Dividi(No x, int pos, No y) {
        No z = new No();
        z.eFolha = y.eFolha;
        z.n = T - 1;
        for (int j = 0; j < T - 1; j++) {
            z.key[j] = y.key[j + T];
        }
        if (!y.eFolha) {
            for (int j = 0; j < T; j++) {
                z.filho[j] = y.filho[j + T];
            }
        }
        y.n = T - 1;
        for (int j = x.n; j >= pos + 1; j--) {
            x.filho[j + 1] = x.filho[j];
        }
        x.filho[pos + 1] = z;

        for (int j = x.n - 1; j >= pos; j--) {
            x.key[j + 1] = x.key[j];
        }
        x.key[pos] = y.key[T - 1];
        x.n = x.n + 1;
    }

    public void Inserir(final int key) {
        No r = raiz;
        insertResult.IncrementCopyCount();
        if (r.n == 2 * T - 1) {
            No s = new No();
            raiz = s;
            s.eFolha = false;
            s.n = 0;
            s.filho[0] = r;
            insertResult.IncrementCopyCount();
            Dividi(s, 0, r);
            inserirValor(s, key);
        } else {
            inserirValor(r, key);
        }
    }

    final private void inserirValor(No x, int k) {

        if (x.eFolha) {
            int i = 0;
            for (i = x.n - 1; i >= 0 && k < x.key[i]; i--) {
                x.key[i + 1] = x.key[i];
            }
            x.key[i + 1] = k;
            x.n = x.n + 1;
        } else {
            int i = 0;

            for (i = x.n - 1; i >= 0 && k < x.key[i]; i--) {
                insertResult.IncrementComparisonCount();
            };
            i++;
            No tmp = x.filho[i];
            if (tmp.n == 2 * T - 1) {
                Dividi(x, i, tmp);
                if (k > x.key[i]) {
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
            System.out.print(x.key[i] + " ");
        }
        if (!x.eFolha) {
            for (int i = 0; i < x.n + 1; i++) {
                Mostrar(x.filho[i]);
            }
        }
    }

    public boolean Buscar(int k) {
        if (this.BuscaKey(raiz, k) != null) {
            return true;
        } else {
            return false;
        }
    }

    public void setTimeInsert(long t) {
        insertResult.IncrementProcessingTimeInMiliseconds(t);
    }

    public void setTimeBusca(long t) {
        buscaResult.IncrementProcessingTimeInMiliseconds(t);
    }
}
