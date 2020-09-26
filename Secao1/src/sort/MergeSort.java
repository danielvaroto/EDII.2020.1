package sort;

import model.SortResult;

public class MergeSort {
    public static SortResult Sort(String[] values) {
        long startMiliseconds = System.currentTimeMillis();
        long ComparisonCount = 0;
        long CopyCount = 0;

        // Sort
        
        long endMiliseconds = System.currentTimeMillis();
        
        return new SortResult(ComparisonCount, CopyCount, endMiliseconds-startMiliseconds);
    }
}
