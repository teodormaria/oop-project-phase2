package year;

import common.Constants;
import enums.Category;
import gifts.Gift;
import gifts.GiftsDatabase;
import santa.Child;

import java.util.ArrayList;

/**
 * Class that stores data relevant to each year
 */
public final class YearlyData {
    /**
     * List of children
     */
    private final ArrayList<Child> children;
    /**
     * Gifts database
     */
    private final GiftsDatabase gifts;
    /**
     * Santa budget
     */
    private final double santaBudget;
    /**
     * Unit of budget used to compute children assigned budget
     */
    private double budgetUnit;

    public YearlyData(final ArrayList<Child> children, final GiftsDatabase gifts,
                      final double santaBudget) {
        this.children = children;
        this.gifts = gifts;
        this.santaBudget = santaBudget;
        this.setBudgetUnit();
        for (Child child : children) {
            child.calculateAssignedBudget(this.budgetUnit);
        }
    }

    public YearlyData(final YearlyData previousYear, final AnnualChange changes) {
        this.children = new ArrayList<>();
        this.gifts = previousYear.gifts;
        this.santaBudget = changes.getNewSantaBudget();
        for (Child child : previousYear.getChildren()) {
            if (child.getAge() < Constants.YOUNG_ADULT_THRESHOLD) {
                ArrayList<ChildUpdate> childUpdates = changes.childUpdatesById(child.getId());
                if (!childUpdates.isEmpty()) {
                    ArrayList<Double> newNiceScoreHistory = new ArrayList<>();
                    newNiceScoreHistory.addAll(child.getNiceScoreHistory());
                    ArrayList<Category> preferredCategories = new ArrayList<>();
                    for (ChildUpdate childUpdate : childUpdates) {
                        if (childUpdate.getHasNewScore()) {
                            newNiceScoreHistory.add(childUpdate.getNiceScore());
                        }
                        if (!childUpdate.getGiftsPreferences().isEmpty()) {
                            for (Category category : childUpdate.getGiftsPreferences()) {
                                if (!preferredCategories.contains(category)) {
                                    preferredCategories.add(category);
                                }
                            }
                        }
                    }
                    for (Category category : child.getGiftsPreferences()) {
                        if (!preferredCategories.contains(category)) {
                            preferredCategories.add(category);
                        }
                    }
                    Child newChild = new Child(child.getId(), child.getLastName(),
                            child.getFirstName(), child.getAge() + 1, child.getCity(),
                            newNiceScoreHistory, preferredCategories, child.getNiceScoreBonus(),
                            child.getElf());
                    this.children.add(newChild);
                } else {
                    this.children.add(new Child(child));
                }
            }
        }
        for (Child child : changes.getNewChildren()) {
            if (child.getAge() <= Constants.YOUNG_ADULT_THRESHOLD) {
                this.children.add(child);
            }
        }
        for (Gift gift : changes.getNewGifts()) {
            this.gifts.addGift(gift);
        }
        this.setBudgetUnit();
        for (Child child : children) {
            child.setAssignedBudget(child.getAverageScore() * this.getBudgetUnit());
        }
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public GiftsDatabase getGifts() {
        return gifts;
    }

    public double getSantaBudget() {
        return santaBudget;
    }

    public double getBudgetUnit() {
        return budgetUnit;
    }

    /**
     * Sets budget unit according to children's niceScores
     */
    public void setBudgetUnit() {
        double averageScoresSum = 0;
        for (Child child : children) {
            averageScoresSum += child.getAverageScore();
        }
        this.budgetUnit = this.santaBudget / averageScoresSum;
    }
}
