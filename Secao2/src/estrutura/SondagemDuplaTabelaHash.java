package estrutura;

public class SondagemDuplaTabelaHash<T> {

    private ChaveValor[] entradas;
    private int m;
    private int entradasPreenchidas;

    public SondagemDuplaTabelaHash() {
        this(17);
    }

    private SondagemDuplaTabelaHash(int m) {
        this.entradas = new ChaveValor[m];
        this.m = m;
        this.entradasPreenchidas = 0;
    }

    public void inserir(int chave, T valor) throws Exception {
        var contadorColisoes = 0;
        var inserido = false;

        do {
            var hash = this.hash(chave, contadorColisoes);

            if (hash < 0) {
                throw new Exception("Muitas colisões causaram hash negativo.");
            }

            if (this.entradas[hash] == null) {
                this.entradas[hash] = new ChaveValor<>(chave, valor);
                inserido = true;
            } else {
                contadorColisoes++;
            }
        } while (inserido == false);

        if (++this.entradasPreenchidas >= 4 * this.m / 5) {
            this.aumentarTamanhoEReHash();
        }
    }

    public T buscar(int chave) {
        for (var contadorColisoes = 0; contadorColisoes < this.m; contadorColisoes++) {
            var hash = this.hash(chave, contadorColisoes);

            if (this.entradas[hash].chave == chave) {
                return (T) this.entradas[hash].valor;
            }
        }

        return null;
    }

    public ChaveValor[] buscarEntradas() {
        return this.entradas;
    }

    private int hash(int value, int i) {
        var h1 = value % this.m;
        var h2 = 7 + (value % (7));

        return (h1 + i * h2) % this.m;
    }

    private void aumentarTamanhoEReHash() throws Exception {
        this.m = this.m * 2;

        var novaTabelaHash = new SondagemDuplaTabelaHash<T>(this.m);

        for (var entrada : this.entradas) {
            if (entrada != null) {
                novaTabelaHash.inserir(entrada.chave, (T) entrada.valor);
            }
        }

        this.entradas = novaTabelaHash.entradas;
    }

    public class ChaveValor<E> {

        private int chave;
        private E valor;

        public ChaveValor(int chave, E valor) {
            this.chave = chave;
            this.valor = valor;
        }
        
        public E buscarValor() {
            return this.valor;
        }
    }
}
