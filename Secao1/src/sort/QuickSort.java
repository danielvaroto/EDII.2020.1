package sort;

import model.SortResult;

public class QuickSort {

    private static long comparisonCount, copyCount;

    public static SortResult Sort(String[] values) {
        comparisonCount = copyCount = 0;
        
        long startMiliseconds = System.currentTimeMillis();

        quickSort(values, 0, values.length - 1);

        long endMiliseconds = System.currentTimeMillis();

        return new SortResult(
                values.length,
                comparisonCount,
                copyCount,
                endMiliseconds - startMiliseconds);
    }

    private static void quickSort(String[] values, int start, int end) {
        if (IncreaseComparisonCount() && start < end) {
            int pivotPosition = partition(values, start, end);
            quickSort(values, start, pivotPosition - 1);
            quickSort(values, pivotPosition + 1, end);
        }
    }

    private static int partition(String[] values, int initialStart, int initialEnd) {
        String pivot = values[initialStart];
        copyCount++;
        int currentStart = initialStart + 1;
        int currentEnd = initialEnd;

        while (currentStart <= currentEnd) { // <= isso é para ser contabilizado também?
            if (values[currentStart].compareTo(pivot) <= 0) { // <= ou somente esse?
                currentStart++;
            } else if (IncreaseComparisonCount() && pivot.compareTo(values[currentEnd]) < 0) {
                currentEnd--;
            } else {
                String swap = values[currentStart];
                copyCount++;
                values[currentStart] = values[currentEnd];
                copyCount++;
                values[currentEnd] = swap;
                copyCount++;
                currentStart++;
                currentEnd--;
            }
        }

        values[initialStart] = values[currentEnd];
        copyCount++;
        values[currentEnd] = pivot;
        copyCount++;

        return currentEnd;
    }

    private static boolean IncreaseComparisonCount() {
        comparisonCount++;
        return true;
    }
}
