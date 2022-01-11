package santa.strategies;

import java.util.ArrayList;

public interface Strategy {
    /**
     *
     * @param niceScores
     * @return niceScore calculated through various means based on niceScore arrayList
     */
    double getNiceScore(ArrayList<Double> niceScores);
}
