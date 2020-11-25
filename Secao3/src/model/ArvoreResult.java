package model;

public class ArvoreResult {

    private final int EntriesCount;
    private final long ComparisonCount;
    private final long CopyCount;
    private final long ProcessingTimeInMiliseconds;

    public ArvoreResult(int EntriesCount, long ComparisonCount, long CopyCount, long ProcessingTimeInMiliseconds) {
        this.EntriesCount = EntriesCount;
        this.ComparisonCount = ComparisonCount;
        this.CopyCount = CopyCount;
        this.ProcessingTimeInMiliseconds = ProcessingTimeInMiliseconds;
    }

    /**
     * @return the EntriesCount
     */
    public int getEntriesCount() {
        return EntriesCount;
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
