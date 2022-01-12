package santa.sortingStrategies;

import santa.Child;

import java.util.Comparator;

/**
 * Child comparator based on children's nice score
 */
public final class NiceScoreComparator implements Comparator<Child> {
    @Override
    public int compare(final Child firstChild, final Child secondChild) {
        if (firstChild.getAverageScore() == secondChild.getAverageScore()) {
            return firstChild.getId() - secondChild.getId();
        } else {
            return Double.compare(secondChild.getAverageScore(), firstChild.getAverageScore());
        }
    }
}
