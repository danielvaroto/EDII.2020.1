package sort;

import model.SortResult;

public class MergeSort {

    private static long ComparisonCount, CopyCount;

    public static SortResult Sort(String[] values) {
        ComparisonCount = CopyCount = 0;
        
        long startMiliseconds = System.currentTimeMillis();

        mergeSort(values, 0, values.length - 1);
        long endMiliseconds = System.currentTimeMillis();

        return new SortResult(
                values.length,
                ComparisonCount,
                CopyCount,
                endMiliseconds - startMiliseconds);
    }

    public static void mergeSort(String vetor[], int inicio, int fim) {
        int meio;
        if (inicio < fim) {
            meio = (inicio + fim) / 2;
            mergeSort(vetor, inicio, meio);
            mergeSort(vetor, meio + 1, fim);
            merge(vetor, inicio, meio, fim);
        }
    }

    public static void merge(String[] a, int inicio, int meio, int fim) {
        int n = fim - inicio + 1;       // tamanho do array do merge
        String[] b = new String[n];   // matriz temporária b
        int i1 = inicio;               // proximo elemento do primeira metade
        int i2 = meio + 1;            // proximo elemento da segunda metade
        int j = 0;                   // posição na matriz b

        // enquando as matrizes não chegarem ao fim move o menos para o b
        while (i1 <= meio && i2 <= fim) {
            CopyCount++;
            if (IncreaseComparisonCount() && a[i1].compareTo(a[i2]) < 0) {
                b[j] = a[i1];
                i1++;
            } else {
                b[j] = a[i2];
                i2++;
            }
            j++;
        }

        // copia o que sobrar do primeiro array
        while (i1 <= meio) {
            CopyCount++;
            b[j] = a[i1];
            i1++;
            j++;
        }

        // copia o que sobrar do segundo array
        while (i2 <= fim) {
            CopyCount++;
            b[j] = a[i2];
            i2++;
            j++;
        }

        // retorna os valores para o array principal
        for (j = 0; j < n; j++) {
            a[inicio + j] = b[j];
        }
    }

    private static boolean IncreaseComparisonCount() {
        ComparisonCount++;
        return true;
    }
}
