package year;

import enums.CityStrategyEnum;
import fileio.input.AnnualChangeInputData;
import fileio.input.ChildInputData;
import fileio.input.ChildUpdateInputData;
import fileio.input.GiftInputData;
import gifts.Gift;
import santa.Child;

import java.util.ArrayList;

/**
 * Class that stores information about yearly changes
 */
public final class AnnualChange {
    /**
     * New Santa budget
     */
    private final double newSantaBudget;
    /**
     * New Gifts
     */
    private final ArrayList<Gift> newGifts;
    /**
     * New Children
     */
    private final ArrayList<Child> newChildren;
    /**
     * Updated for existing children
     */
    private final ArrayList<ChildUpdate> childrenUpdates;
    /**
     * Strategy that determines gift assignation
     */
    private final CityStrategyEnum strategy;

    public AnnualChange(final AnnualChangeInputData annualChangeInput) {
        this.newSantaBudget = annualChangeInput.getNewSantaBudget();
        this.newGifts = new ArrayList<>();
        for (GiftInputData giftInput : annualChangeInput.getNewGifts()) {
            this.newGifts.add(new Gift(giftInput));
        }
        this.newChildren = new ArrayList<>();
        for (ChildInputData childInput : annualChangeInput.getNewChildren()) {
            this.newChildren.add(new Child(childInput));
        }
        this.childrenUpdates = new ArrayList<>();
        for (ChildUpdateInputData childUpdateInput : annualChangeInput.getChildrenUpdates()) {
            this.childrenUpdates.add(new ChildUpdate(childUpdateInput));
        }
        this.strategy = annualChangeInput.getStrategy();
    }

    public double getNewSantaBudget() {
        return newSantaBudget;
    }

    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    public ArrayList<Child> getNewChildren() {
        return newChildren;
    }

    public ArrayList<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    public CityStrategyEnum getStrategy() {
        return strategy;
    }

    /**
     *
     * @param id
     * @return list of updates for child with a given ID
     */
    public ArrayList<ChildUpdate> childUpdatesById(final int id) {
        ArrayList<ChildUpdate> result = new ArrayList<>();
        for (ChildUpdate childUpdate : childrenUpdates) {
            if (childUpdate.getId() == id) {
                result.add(childUpdate);
            }
        }
        return result;
    }
}
