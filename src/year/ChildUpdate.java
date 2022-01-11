package year;

import enums.Category;
import fileio.input.ChildUpdateInputData;

import java.util.ArrayList;

/**
 * Class that stores information about children updates
 */
public final class ChildUpdate {
    /**
     * Child ID
     */
    private final int id;
    /**
     * NiceScore to be added to previous niceScores history
     */
    private final double niceScore;
    /**
     * List of new preferred categories
     */
    private final ArrayList<Category> giftsPreferences;
    /**
     * Boolean of whether niceScore has a relevant value
     */
    private final boolean hasNewScore;

    public ChildUpdate(final ChildUpdateInputData input) {
        this.id = input.getId();
        this.niceScore = input.getNiceScore();
        this.giftsPreferences = input.getGiftsPreferences();
        this.hasNewScore = input.getHasNewScore();
    }
    public int getId() {
        return id;
    }

    public double getNiceScore() {
        return niceScore;
    }

    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public boolean getHasNewScore() {
        return hasNewScore;
    }
}
