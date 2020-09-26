package model;

public class SortResult {
    private long ComparisonCount;
    private long CopyCount;
    private long ProcessingTimeInMiliseconds;

    public SortResult(long ComparisonCount, long CopyCount, long ProcessingTimeInMiliseconds) {
        this.ComparisonCount = ComparisonCount;
        this.CopyCount = CopyCount;
        this.ProcessingTimeInMiliseconds = ProcessingTimeInMiliseconds;
    }

    /**
     * @return the ComparisonCount
     */
    public long getComparisonCount() {
        return ComparisonCount;
    }

    /**
     * @return the CopyCount
     */
    public long getCopyCount() {
        return CopyCount;
    }

    /**
     * @return the ProcessingTimeInMiliseconds
     */
    public long getProcessingTimeInMiliseconds() {
        return ProcessingTimeInMiliseconds;
    }
}
