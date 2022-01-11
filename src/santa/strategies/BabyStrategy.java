package santa.strategies;

import common.Constants;

import java.util.ArrayList;

/**
 * Class for strategy to be applied to children under 5 years old
 */
public final class BabyStrategy implements Strategy {
    @Override
    public double getNiceScore(final ArrayList<Double> niceScores) {
        return Constants.MAX_NICE_SCORE;
    }
}
