package fileio.input;

import java.util.List;

/**
 * Class that stores all input information
 */
public record Input(List<ChildInputData> children,
                    List<GiftInputData> gifts,
                    List<AnnualChangeInputData> annualChanges,
                    int numOfYears, double annualBudget) {

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
