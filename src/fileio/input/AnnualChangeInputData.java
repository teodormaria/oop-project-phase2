package fileio.input;

import enums.CityStrategyEnum;

import java.util.ArrayList;

/**
 * Class used to read data about annual changes
 */
public record AnnualChangeInputData(double newSantaBudget,
                                    ArrayList<GiftInputData> newGifts,
                                    ArrayList<ChildInputData> newChildren,
                                    ArrayList<ChildUpdateInputData> childrenUpdates,
                                    CityStrategyEnum strategy) {

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
