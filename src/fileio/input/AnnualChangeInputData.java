package fileio.input;

import enums.CityStrategyEnum;

import java.util.ArrayList;

/**
 * Class used to read data about annual changes
 */
public final class AnnualChangeInputData {
    /**
     * SantaBudget for the upcoming year
     */
    private final double newSantaBudget;
    /**
     * List of gifts to be added to giftDatabase
     */
    private final ArrayList<GiftInputData> newGifts;
    /**
     * List of new children
     */
    private final ArrayList<ChildInputData> newChildren;
    /**
     * List of updated for already existing children
     */
    private final ArrayList<ChildUpdateInputData> childrenUpdates;
    /**
     * Strategy that determines gift assignation
     */
    private final CityStrategyEnum strategy;

    public AnnualChangeInputData(final double newSantaBudget,
                                 final ArrayList<GiftInputData> newGifts,
                                 final ArrayList<ChildInputData> newChildren,
                                 final ArrayList<ChildUpdateInputData> childrenUpdates,
                                 final CityStrategyEnum strategy) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
        this.strategy = strategy;
    }

    public double getNewSantaBudget() {
        return newSantaBudget;
    }

    public ArrayList<GiftInputData> getNewGifts() {
        return newGifts;
    }

    public ArrayList<ChildInputData> getNewChildren() {
        return newChildren;
    }

    public ArrayList<ChildUpdateInputData> getChildrenUpdates() {
        return childrenUpdates;
    }

    public CityStrategyEnum getStrategy() {
        return strategy;
    }
}
