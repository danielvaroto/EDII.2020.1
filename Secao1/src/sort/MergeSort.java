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

    public static void merge(String[] a, int from, int mid, int to) {
        int n = to - from + 1;       // size of the range to be merged
        String[] b = new String[n];   // merge both halves into a temporary array b
        int i1 = from;               // next element to consider in the first range
        int i2 = mid + 1;            // next element to consider in the second range
        int j = 0;                   // next open position in b

        // as long as neither i1 nor i2 past the end, move the smaller into b
        while (i1 <= mid && i2 <= to) {
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

        // note that only one of the two while loops below is executed
        // copy any remaining entries of the first half
        while (i1 <= mid) {
            CopyCount++;
            b[j] = a[i1];
            i1++;
            j++;
        }

        // copy any remaining entries of the second half
        while (i2 <= to) {
            CopyCount++;
            b[j] = a[i2];
            i2++;
            j++;
        }

        // copy back from the temporary array
        for (j = 0; j < n; j++) {
            CopyCount++;
            a[from + j] = b[j];
        }
    }//end merge

    private static boolean IncreaseComparisonCount() {
        ComparisonCount++;
        return true;
    }
}
