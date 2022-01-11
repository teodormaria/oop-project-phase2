package fileio.input;

import java.util.List;

/**
 * Class that stores all input information
 */
public final class Input {
    /**
     * List of children
     */
    private final List<ChildInputData> children;
    /**
     * List of gifts
     */
    private final List<GiftInputData> gifts;
    /**
     * List of annual changes
     */
    private final List<AnnualChangeInputData> annualChanges;
    /**
     * Number of years
     */
    private final int numOfYears;
    /**
     * Budget for year 0
     */
    private final double annualBudget;

    public Input(final List<ChildInputData> children, final List<GiftInputData> gifts,
                 final List<AnnualChangeInputData> annualChanges,
                 final int numOfYears, final double annualBudget) {
        this.children = children;
        this.gifts = gifts;
        this.annualChanges = annualChanges;
        this.numOfYears = numOfYears;
        this.annualBudget = annualBudget;
    }

    public List<ChildInputData> getChildren() {
        return children;
    }

    public List<GiftInputData> getGifts() {
        return gifts;
    }

    public List<AnnualChangeInputData> getAnnualChanges() {
        return annualChanges;
    }

    public int getNumOfYears() {
        return numOfYears;
    }

    public double getAnnualBudget() {
        return annualBudget;
    }
}
