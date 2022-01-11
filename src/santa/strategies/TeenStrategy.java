package santa.strategies;

import java.util.ArrayList;

/**
 * Class for strategy to be applied to teens
 */
public final class TeenStrategy implements Strategy {
    @Override
    public double getNiceScore(final ArrayList<Double> niceScores) {
        double average = 0;
        double totalWeight = 0;
        for (int i = 0; i < niceScores.size(); i++) {
            average += niceScores.get(i) * (i + 1);
            totalWeight += (i + 1);
        }
        average = average / totalWeight;
        return average;
    }
}
