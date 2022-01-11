package santa;

import common.Constants;
import enums.Category;
import enums.CityStrategyEnum;
import enums.ElvesType;
import fileio.input.AnnualChangeInputData;
import fileio.input.ChildInputData;
import fileio.input.GiftInputData;
import fileio.input.Input;
import gifts.Gift;
import gifts.GiftsDatabase;
import santa.sortingStrategies.IdComparator;
import santa.sortingStrategies.NiceScoreComparator;
import year.AnnualChange;
import year.YearlyData;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that retains current year data and future changes
 */
public final class Santa {
    /**
     * Number of future years to be simulated
     */
    private final int numberOfYears;
    /**
     * Current year relevant data
     */
    private YearlyData yearlyData;
    /**
     * List of changes for each future year to be simulated
     */
    private ArrayList<AnnualChange> annualChanges;

    public Santa(final Input input) {
        this.numberOfYears = input.getNumOfYears();
        ArrayList<Child> initialChildren = new ArrayList<>();
        for (ChildInputData childInput : input.getChildren()) {
            if (childInput.getAge() <= Constants.YOUNG_ADULT_THRESHOLD) {
                initialChildren.add(new Child(childInput));
            }
        }
        GiftsDatabase giftsDatabase = GiftsDatabase.getDatabase();
        giftsDatabase.restartDatabase();
        for (GiftInputData giftInput : input.getGifts()) {
            giftsDatabase.addGift(new Gift(giftInput));
        }
        this.yearlyData = new YearlyData(initialChildren, giftsDatabase, input.getAnnualBudget(),
                CityStrategyEnum.ID);
        this.annualChanges = new ArrayList<>();
        for (AnnualChangeInputData annualChangeInput : input.getAnnualChanges()) {
            this.annualChanges.add(new AnnualChange(annualChangeInput));
        }
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public YearlyData getYearlyData() {
        return yearlyData;
    }

    /**
     * Gives each child their gifts
     */
    private void giveGifts() {
        if (yearlyData.getStrategy().equals(CityStrategyEnum.ID)) {
            Collections.sort(yearlyData.getChildren(), new IdComparator());
        } else if (yearlyData.getStrategy().equals(CityStrategyEnum.NICE_SCORE)) {
            Collections.sort(yearlyData.getChildren(), new NiceScoreComparator());
        } else {
            yearlyData.orderChildrenByNiceScoreCity();
        }
        for (Child child : yearlyData.getChildren()) {
            double remainingBudget = child.getAssignedBudget();
            for (Category category: child.getGiftsPreferences()) {
                ArrayList<Gift> suitableGifts = yearlyData.getGifts().getGiftsByCategory(category);
                for (Gift gift : suitableGifts) {
                    if (gift.getPrice() < remainingBudget) {
                        child.giveGift(gift);
                        remainingBudget -= gift.getPrice();
                        gift.wasGiven();
                        break;
                    }
                }
            }
        }
        for (Child child :yearlyData.getChildren()) {
            if (child.getReceivedGifts().isEmpty() && child.getElf().equals(ElvesType.YELLOW)) {
                ArrayList<Gift> suitableGifts = yearlyData.getGifts()
                        .getGiftsByCategory(child.getGiftsPreferences().get(0));
                if (!suitableGifts.isEmpty()) {
                    child.giveGift(suitableGifts.get(0));
                    suitableGifts.get(0).wasGiven();
                }
            }
        }
    }

    /**
     * given that information about the previous year is stored in yearlyData, executes next year
     * @param yearNumber
     */
    public void executeYear(final int yearNumber) {
        if (yearNumber != 0) {
            YearlyData previousYear = this.yearlyData;
            this.yearlyData = new YearlyData(previousYear, annualChanges.get(yearNumber - 1));
        }
        this.giveGifts();
    }
}
