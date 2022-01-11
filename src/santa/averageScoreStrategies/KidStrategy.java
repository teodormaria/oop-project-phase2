package santa.averageScoreStrategies;

import java.util.ArrayList;

/**
 * Class for strategy to be applied to children under 12 years old
 */
public final class KidStrategy implements Strategy {
    @Override
    public double getNiceScore(final ArrayList<Double> niceScores) {
        double average = 0;
        double count = 0;
        for (double score : niceScores) {
            average += score;
            count++;
        }
        average = average / count;
        return average;
    }
}
