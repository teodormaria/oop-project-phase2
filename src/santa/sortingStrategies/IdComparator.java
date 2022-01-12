package santa.sortingStrategies;

import santa.Child;

import java.util.Comparator;

/**
 * Child comparator based of children's id
 */
public final class IdComparator implements Comparator<Child> {
    @Override
    public int compare(final Child firstChild, final Child secondChild) {
        return firstChild.getId() - secondChild.getId();
    }
}
