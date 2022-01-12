package santa.sortingStrategies;

import santa.Child;

import java.util.Comparator;

public final class IdComparator implements Comparator<Child> {
    @Override
    public int compare(final Child firstChild, final Child secondChild) {
        return firstChild.getId() - secondChild.getId();
    }
}
