package santa.averageScoreStrategies;

import common.Constants;

/**
 * Class that uses Factory pattern to get suitable strategy
 */
public final class StrategyFactory {
    /**
     *
     * @param age
     * @return strategy used according to child age
     */
    public static Strategy useStrategy(final int age) {
        if (age < Constants.KID_THRESHOLD) {
            return new BabyStrategy();
        } else if (age < Constants.TEEN_THRESHOLD) {
            return new KidStrategy();
        } else {
            return new TeenStrategy();
        }
    }
}
