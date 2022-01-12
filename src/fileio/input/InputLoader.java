package fileio.input;

import common.Constants;
import enums.CityStrategyEnum;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that loads input into an Input instance
 */
public class InputLoader {
    /**
     * The path to the input file
     */
    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * The method reads the database
     * @return an Input object
     */
    public Input readData() {
        JSONParser jsonParser = new JSONParser();
        List<ChildInputData> children = new ArrayList<>();
        List<GiftInputData> gifts = new ArrayList<>();
        List<AnnualChangeInputData> annualChanges = new ArrayList<>();
        int numOfYears = 0;
        double annualBudget = 0;

        try {
            // Parsing the contents of the JSON file
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));
            numOfYears = Integer.parseInt(jsonObject.get(Constants.NUMBER_OF_YEARS).toString());
            annualBudget = Double.parseDouble(jsonObject.get(Constants.SANTA_BUDGET).toString());
            JSONObject initialData = (JSONObject) jsonObject.get(Constants.INITIAL_DATA);
            JSONArray jsonAnnualChanges = (JSONArray) jsonObject.get(Constants.ANNUAL_CHANGES);
            JSONArray childrenJSON = (JSONArray) initialData.get(Constants.CHILDREN);
            for (Object jsonChild : childrenJSON) {
                children.add(new ChildInputData(
                        Integer.parseInt(((JSONObject) jsonChild).get(Constants.ID).toString()),
                        (String) ((JSONObject) jsonChild).get(Constants.LAST_NAME),
                        (String) ((JSONObject) jsonChild).get(Constants.FIRST_NAME),
                        Integer.parseInt(((JSONObject) jsonChild).get(Constants.AGE).toString()),
                        Utils.stringToCities((String)
                                ((JSONObject) jsonChild).get(Constants.CITY)),
                        Double.parseDouble(((JSONObject) jsonChild).get(Constants.NICE_SCORE)
                                .toString()),
                        Utils.stringArrayToCategoryArray(Utils.convertJSONArray((JSONArray)
                                ((JSONObject) jsonChild).get(Constants.GIFTS_PREFERENCES))),
                        Double.parseDouble(((JSONObject) jsonChild).get(Constants.NICE_SCORE_BONUS)
                                .toString()),
                        Utils.stringToElf((String) ((JSONObject) jsonChild).get(Constants.ELF))
                        ));
            }
            JSONArray jsonGifts = (JSONArray) initialData.get(Constants.SANTA_GIFTS_LIST);
            for (Object jsonGift : jsonGifts) {
                gifts.add(new GiftInputData(
                        (String) ((JSONObject) jsonGift).get(Constants.PRODUCT_NAME),
                        Integer.parseInt(((JSONObject) jsonGift).get(Constants.PRICE).toString()),
                        Utils.stringToCategory((String) ((JSONObject) jsonGift)
                                .get(Constants.CATEGORY)),
                        Integer.parseInt(((JSONObject) jsonGift).get(Constants.QUANTITY).toString())
                ));
            }
            for (Object jsonAnnualChange : jsonAnnualChanges) {
                JSONArray jsonNewGifts = (JSONArray) ((JSONObject) jsonAnnualChange)
                        .get(Constants.NEW_GIFTS);
                JSONArray jsonNewChildren = (JSONArray) ((JSONObject) jsonAnnualChange)
                        .get(Constants.NEW_CHILDREN);
                JSONArray jsonChildrenUpdates = (JSONArray) ((JSONObject) jsonAnnualChange)
                        .get(Constants.CHILDREN_UPDATES);
                ArrayList<GiftInputData> newGifts = new ArrayList<>();
                ArrayList<ChildInputData> newChildren = new ArrayList<>();
                ArrayList<ChildUpdateInputData> childrenUpdates = new ArrayList<>();
                double newBudget = Double.parseDouble(((JSONObject) jsonAnnualChange)
                        .get(Constants.NEW_SANTA_BUDGET).toString());
                for (Object newGift : jsonNewGifts) {
                    newGifts.add(new GiftInputData(
                            (String) ((JSONObject) newGift).get(Constants.PRODUCT_NAME),
                            Integer.parseInt(((JSONObject) newGift).get(Constants.PRICE)
                                    .toString()),
                            Utils.stringToCategory((String) ((JSONObject) newGift)
                                    .get(Constants.CATEGORY)),
                            Integer.parseInt(((JSONObject) newGift).get(Constants.QUANTITY)
                                    .toString())
                    ));
                }
                for (Object newChild : jsonNewChildren) {
                    newChildren.add(new ChildInputData(
                            Integer.parseInt(((JSONObject) newChild).get(Constants.ID).toString()),
                            (String) ((JSONObject) newChild).get(Constants.LAST_NAME),
                            (String) ((JSONObject) newChild).get(Constants.FIRST_NAME),
                            Integer.parseInt(((JSONObject) newChild).get(Constants.AGE).toString()),
                            Utils.stringToCities((String)
                                    ((JSONObject) newChild).get(Constants.CITY)),
                            Double.parseDouble(((JSONObject) newChild).get(Constants.NICE_SCORE)
                                    .toString()),
                            Utils.stringArrayToCategoryArray(Utils.convertJSONArray((JSONArray)
                                    ((JSONObject) newChild).get(Constants.GIFTS_PREFERENCES))),
                            Double.parseDouble(((JSONObject) newChild).get(Constants.NICE_SCORE_BONUS)
                                    .toString()),
                            Utils.stringToElf((String) ((JSONObject) newChild).get(Constants.ELF))
                    ));
                }
                for (Object childUpdate : jsonChildrenUpdates) {
                    if (((JSONObject) childUpdate).get(Constants.NICE_SCORE) != null) {
                        ChildUpdateInputData singleChildUpdate = new ChildUpdateInputData.Builder(
                                Integer.parseInt(((JSONObject) childUpdate).get(Constants.ID)
                                        .toString()),
                                Utils.stringArrayToCategoryArray(Utils.convertJSONArray((JSONArray)
                                        ((JSONObject) childUpdate)
                                                .get(Constants.GIFTS_PREFERENCES))),
                                Utils.stringToElf((String) ((JSONObject) childUpdate)
                                .get(Constants.ELF)))
                                .niceScore(Double.parseDouble(((JSONObject) childUpdate)
                                        .get(Constants.NICE_SCORE).toString())).build();
                        childrenUpdates.add(singleChildUpdate);
                    } else {
                        ChildUpdateInputData singleChildUpdate = new ChildUpdateInputData.Builder(
                                Integer.parseInt(((JSONObject) childUpdate).get(Constants.ID)
                                        .toString()),
                                Utils.stringArrayToCategoryArray(Utils.convertJSONArray((JSONArray)
                                        ((JSONObject) childUpdate)
                                                .get(Constants.GIFTS_PREFERENCES))),
                                Utils.stringToElf((String) ((JSONObject) childUpdate)
                                        .get(Constants.ELF))).build();
                        childrenUpdates.add(singleChildUpdate);
                    }
                }
                CityStrategyEnum strategy = Utils.stringToCityStrategy(
                        ((JSONObject) jsonAnnualChange).get(Constants.STRATEGY).toString());
                annualChanges.add(new AnnualChangeInputData(newBudget, newGifts, newChildren,
                        childrenUpdates, strategy));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new Input(children, gifts, annualChanges, numOfYears, annualBudget);
    }
}
