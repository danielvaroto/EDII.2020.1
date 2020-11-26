package model;

public class ArvoreResult {

    private final int EntriesCount;
    private long ComparisonCount;
    private long CopyCount;
    private long ProcessingTimeInMiliseconds;

    
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
    
    public boolean IncrementComparisonCount() {
        this.ComparisonCount++;
        
        return true;
    }

    /**
     * @return the CopyCount
     */
    public long getCopyCount() {
        return CopyCount;
    }
    
    public void IncrementCopyCount() {
        this.CopyCount++;
    }

    /**
     * @return the ProcessingTimeInMiliseconds
     */
    public long getProcessingTimeInMiliseconds() {
        return ProcessingTimeInMiliseconds;
    }
    
    public void IncrementProcessingTimeInMiliseconds(final long ProcessingTimeInMiliseconds ) {
        this.ProcessingTimeInMiliseconds += ProcessingTimeInMiliseconds;
    }
}
