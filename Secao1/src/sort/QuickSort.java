package sort;

import model.SortResult;

public class QuickSort {

    private static long contagemComparacao, contagemCopia;

    public static SortResult Sort(String[] valores) {
        // Inicia contadores
        contagemComparacao = contagemCopia = 0;

        // Grava tempo de inicio
        long tempoInicial = System.currentTimeMillis();

        QuickSort(valores, 0, valores.length - 1);

        // Grava tempo do fim
        long tempoFinal = System.currentTimeMillis();

        // Calcula tempo total
        long tempoTotalMilissegundos = tempoFinal - tempoInicial;

        // Retorna as estatisticas
        return new SortResult(
                valores.length,
                contagemComparacao,
                contagemCopia,
                tempoTotalMilissegundos);
    }

    private static void QuickSort(String[] valores, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = Particionar(valores, inicio, fim);

            QuickSort(valores, inicio, posicaoPivo - 1);
            QuickSort(valores, posicaoPivo + 1, fim);
        }
    }

    private static int Particionar(String[] valores, int inicio, int fim) {
        // Valor copiado
        contagemCopia++;
        String pivo = valores[inicio];

        int inicioLocal = inicio + 1;
        int fimLocal = fim;

        while (inicioLocal <= fimLocal) {
            // Valor comparado
            if (AumentarContagemComparacao() && valores[inicioLocal].compareTo(pivo) <= 0) {
                inicioLocal++;
            }
            // Valor comparado
            else if (AumentarContagemComparacao() && pivo.compareTo(valores[fimLocal]) < 0) {
                fimLocal--;
            } else {
                // Permuta os valores do inicio e fim local
                Permutar(valores, inicioLocal, fimLocal);

                inicioLocal++;
                fimLocal--;
            }
        }

        // Valor copiado
        contagemCopia++;
        valores[inicio] = valores[fimLocal];

        valores[fimLocal] = pivo;

        return fimLocal;
    }

    private static void Permutar(String[] valores, int de, int para) {
        // Valor copiado
        contagemCopia++;
        String valor = valores[de];

        // Valor copiado
        contagemCopia++;
        valores[de] = valores[para];

        valores[para] = valor;
    }

    private static boolean AumentarContagemComparacao() {
        // Aumenta contador de comparacao
        contagemComparacao++;
        
        // Retorna true para comparacao acontecer
        return true;
    }
}
