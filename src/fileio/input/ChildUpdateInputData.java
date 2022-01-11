package fileio.input;

import enums.Category;

import java.util.ArrayList;

/**
 * Class used to store input information about any child update
 */
public final class ChildUpdateInputData {
    /**
     * Child ID
     */
    private final int id;
    /**
     * New niceScore to be added
     */
    private final double niceScore;
    /**
     * List of new categories preferences
     */
    private final ArrayList<Category> giftsPreferences;
    /**
     * Boolean of whether the niceScore exists or not
     */
    private final boolean hasNewScore;

    /**
     * Builder class to help with cases when newScore has null value
     */
    public static final class Builder {
        private int id;
        private double niceScore = -1;
        private ArrayList<Category> giftsPreferences;
        private boolean hasNewScore = false;

        public Builder(final int id, final ArrayList<Category> giftsPreferences) {
            this.id = id;
            this.giftsPreferences = giftsPreferences;
        }

        /**
         * Adds niceScore value
         * @param givenNiceScore to be added
         * @return Builder with added niceScore
         */
        public Builder niceScore(final double givenNiceScore) {
            this.hasNewScore = true;
            this.niceScore = givenNiceScore;
            return this;
        }

        /**
         *
         * @return ChildUpdateInputData result
         */
        public ChildUpdateInputData build() {
            return new ChildUpdateInputData(this);
        }
    }

    public ChildUpdateInputData(final Builder builder) {
        this.id = builder.id;
        this.niceScore = builder.niceScore;
        this.giftsPreferences = builder.giftsPreferences;
        this.hasNewScore = builder.hasNewScore;
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
