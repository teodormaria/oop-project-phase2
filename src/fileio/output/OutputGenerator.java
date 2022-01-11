package fileio.output;

import common.Constants;
import gifts.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import santa.Child;
import santa.Santa;

import java.util.ArrayList;

/**
 * Class that generates output
 */
public final class OutputGenerator {
    /**
     * Singleton instance
     */
    private static OutputGenerator instance = null;
    /**
     * Stores what to be written in output file
     */
    private JSONObject finalOutput;

    private OutputGenerator() {
        finalOutput = new JSONObject();
    }

    /**
     *
     * @return output instance
     */
    public static OutputGenerator getInstance() {
        if (instance == null) {
            instance = new OutputGenerator();
        }
        return instance;
    }

    public JSONObject getFinalOutput() {
        return finalOutput;
    }

    /**
     * restarts output for each file
     */
    public void reinitialiseContent() {
        finalOutput = new JSONObject();
    }

    /**
     * Turns array of gifts into JSONArray
     * @param gifts
     * @return gifts JSONArray
     */
    public JSONArray giftsToJSON(final ArrayList<Gift> gifts) {
        JSONArray giftsJSONArray = new JSONArray();
        for (Gift gift : gifts) {
            JSONObject giftJSON = new JSONObject();
            giftJSON.put(Constants.PRODUCT_NAME, gift.getProductName());
            giftJSON.put(Constants.PRICE, gift.getPrice());
            giftJSON.put(Constants.CATEGORY, gift.getCategory().toString());
            giftsJSONArray.add(giftJSON);
        }
        return giftsJSONArray;
    }

    /**
     * turns child into JSONObject
     * @param child
     * @return JSONObject with child information
     */
    public JSONObject childToJSON(final Child child) {
        JSONObject object = new JSONObject();
        object.put(Constants.ID, child.getId());
        object.put(Constants.LAST_NAME, child.getLastName());
        object.put(Constants.FIRST_NAME, child.getFirstName());
        object.put(Constants.CITY, child.getCity().toString());
        object.put(Constants.AGE, child.getAge());
        object.put(Constants.GIFTS_PREFERENCES, child.getGiftsPreferencesToStrings());
        object.put(Constants.AVERAGE_SCORE, child.getAverageScore());
        object.put(Constants.NICE_SCORE_HISTORY, child.getNiceScoreHistory());
        object.put(Constants.ASSIGNED_BUDGET, child.getAssignedBudget());
        object.put(Constants.RECEIVED_GIFTS, giftsToJSON(child.getReceivedGifts()));
        return object;
    }

    /**
     * Goes through all years and computes final output
     * @param santa, contains information about initial year and annual changes
     */
    public void computeAllYears(final Santa santa) {
        reinitialiseContent();
        JSONArray content = new JSONArray();
        for (int year = 0; year <= santa.getNumberOfYears(); year++) {
            santa.executeYear(year);
            JSONArray childrenJSON = new JSONArray();
            for (Child child : santa.getYearlyData().getChildren()) {
                childrenJSON.add(childToJSON(child));
            }
            JSONObject yearlyChildren = new JSONObject();
            yearlyChildren.put(Constants.CHILDREN, childrenJSON);
            content.add(yearlyChildren);
        }
        finalOutput.put(Constants.ANNUAL_CHILDREN, content);
    }
}
