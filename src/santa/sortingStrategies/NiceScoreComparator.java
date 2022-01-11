package santa.sortingStrategies;

import santa.Child;

import java.util.Comparator;

public class NiceScoreComparator implements Comparator<Child> {
    @Override
    public int compare(final Child firstChild, final Child secondChild) {
        if (firstChild.getAverageScore() == secondChild.getAverageScore()) {
            return firstChild.getId() - secondChild.getId();
        } else {
            return (int) (firstChild.getAverageScore() - secondChild.getAverageScore());
        }
    }
}
