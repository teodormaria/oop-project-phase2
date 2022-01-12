package fileio.input;

import enums.Category;
import enums.ElvesType;

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
     * New elf for child
     */
    private final ElvesType elf;

    /**
     * Builder class to help with cases when newScore has null value
     */
    public static final class Builder {
        private final int id;
        private double niceScore = -1;
        private final ArrayList<Category> giftsPreferences;
        private boolean hasNewScore = false;
        private final ElvesType elf;

        public Builder(final int id, final ArrayList<Category> giftsPreferences,
                       final ElvesType elf) {
            this.id = id;
            this.giftsPreferences = giftsPreferences;
            this.elf = elf;
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
        this.elf = builder.elf;
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

    public ElvesType getElf() {
        return elf;
    }
}
