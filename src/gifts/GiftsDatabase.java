package gifts;

import java.util.ArrayList;
import java.util.Collections;

import enums.Category;

/**
 * Class that stores all gifts
 */
public final class GiftsDatabase {
    /**
     * List of possible gifts
     */
    private final ArrayList<Gift> gifts = new ArrayList<>();
    /**
     * Database instance
     */
    private final static GiftsDatabase database = new GiftsDatabase();

    private GiftsDatabase() {
    }

    public static GiftsDatabase getDatabase() {
        return database;
    }

    /**
     * Adds gift to database
     * @param gift
     */
    public void addGift(final Gift gift) {
        this.getGifts().add(gift);
    }

    public ArrayList<Gift> getGifts() {
        return gifts;
    }

    /**
     * Iterates through database and finds gifts of a given category
     * @param category
     * @return list of gifts
     */
    public ArrayList<Gift> getGiftsByCategory(final Category category) {
        ArrayList<Gift> suitableGifts = new ArrayList<>();
        for (Gift gift : this.getGifts()) {
            if (category.equals(gift.getCategory())) {
                suitableGifts.add(gift);
            }
        }
        Collections.sort(suitableGifts);
        return suitableGifts;
    }

    /**
     * After each simulation, database must be cleared
     */
    public void restartDatabase() {
        gifts.removeAll(gifts);
    }
}
