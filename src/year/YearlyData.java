package year;

import common.Constants;
import enums.Category;
import enums.Cities;
import enums.CityStrategyEnum;
import enums.ElvesType;
import gifts.Gift;
import gifts.GiftsDatabase;
import santa.Child;
import santa.sortingStrategies.IdComparator;
import utils.Utils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Class that stores data relevant to each year
 */
public final class YearlyData {
    /**
     * List of children
     */
    private ArrayList<Child> children;
    /**
     * Gifts database
     */
    private final GiftsDatabase gifts;
    /**
     * Santa budget
     */
    private final double santaBudget;
    /**
     * Unit of budget used to compute children assigned budget
     */
    private double budgetUnit;
    /**
     * Strategy that determines gift assignation
     */
    private final CityStrategyEnum strategy;

    public YearlyData(final ArrayList<Child> children, final GiftsDatabase gifts,
                      final double santaBudget, final CityStrategyEnum strategy) {
        this.children = children;
        this.gifts = gifts;
        this.santaBudget = santaBudget;
        this.setBudgetUnit();
        for (Child child : children) {
            child.calculateAssignedBudget(this.budgetUnit);
        }
        this.strategy = strategy;
    }

    public YearlyData(final YearlyData previousYear, final AnnualChange changes) {
        this.children = new ArrayList<>();
        this.gifts = previousYear.gifts;
        this.santaBudget = changes.getNewSantaBudget();
        if (changes.getStrategy() == null) {
            this.strategy = previousYear.getStrategy();
        } else {
            this.strategy = changes.getStrategy();
        }
        for (Child child : previousYear.getChildren()) {
            if (child.getAge() < Constants.YOUNG_ADULT_THRESHOLD) {
                ArrayList<ChildUpdate> childUpdates = changes.childUpdatesById(child.getId());
                if (!childUpdates.isEmpty()) {
                    ArrayList<Double> newNiceScoreHistory = new ArrayList<>();
                    newNiceScoreHistory.addAll(child.getNiceScoreHistory());
                    ArrayList<Category> preferredCategories = new ArrayList<>();
                    ElvesType elf = null;
                    for (ChildUpdate childUpdate : childUpdates) {
                        if (childUpdate.getHasNewScore()) {
                            newNiceScoreHistory.add(childUpdate.getNiceScore());
                        }
                        if (!childUpdate.getGiftsPreferences().isEmpty()) {
                            for (Category category : childUpdate.getGiftsPreferences()) {
                                if (!preferredCategories.contains(category)) {
                                    preferredCategories.add(category);
                                }
                            }
                        }
                        elf = childUpdate.getElf();
                    }
                    if (elf == null) {
                        elf = child.getElf();
                    }
                    for (Category category : child.getGiftsPreferences()) {
                        if (!preferredCategories.contains(category)) {
                            preferredCategories.add(category);
                        }
                    }
                    Child newChild = new Child(child.getId(), child.getLastName(),
                            child.getFirstName(), child.getAge() + 1, child.getCity(),
                            newNiceScoreHistory, preferredCategories, child.getNiceScoreBonus(),
                            elf);
                    this.children.add(newChild);
                } else {
                    this.children.add(new Child(child));
                }
            }
        }
        for (Child child : changes.getNewChildren()) {
            if (child.getAge() <= Constants.YOUNG_ADULT_THRESHOLD) {
                this.children.add(child);
            }
        }
        for (Gift gift : changes.getNewGifts()) {
            this.gifts.addGift(gift);
        }
        this.setBudgetUnit();
        for (Child child : children) {
            child.calculateAssignedBudget(this.budgetUnit);
        }
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public GiftsDatabase getGifts() {
        return gifts;
    }

    public double getSantaBudget() {
        return santaBudget;
    }

    public double getBudgetUnit() {
        return budgetUnit;
    }

    public CityStrategyEnum getStrategy() {
        return strategy;
    }

    /**
     * Sets budget unit according to children's niceScores
     */
    public void setBudgetUnit() {
        double averageScoresSum = 0;
        for (Child child : children) {
            averageScoresSum += child.getAverageScore();
        }
        this.budgetUnit = this.santaBudget / averageScoresSum;
    }

    public double getCityAverageNiceScore(final Cities city) {
        double averageScore = 0;
        int counter = 0;
        for (Child child : children) {
            if (child.getCity().equals(city)) {
                averageScore += child.getAverageScore();
                counter++;
            }
        }
        if (counter != 0) {
            return averageScore / counter;
        } else {
            return 0;
        }
    }

    public ArrayList<Child> getChildrenByCity(final Cities city) {
        ArrayList<Child> childrenByCity = new ArrayList<>();
        for (Child child : children) {
            if (child.getCity().equals(city)) {
                childrenByCity.add(child);
            }
        }
        Collections.sort(childrenByCity,new IdComparator());
        return childrenByCity;
    }

    public void orderChildrenByNiceScoreCity() {
        LinkedHashMap<String, Double> scoreMap = new LinkedHashMap<>();
        for (Cities city : Cities.values()) {
            scoreMap.put(city.toString(), getCityAverageNiceScore(city));
        }
        ArrayList<String> keys = new ArrayList<>(scoreMap.keySet());
        ArrayList<Double> values = new ArrayList<>(scoreMap.values());
        Collections.sort(keys);
        Collections.sort(values, Collections.reverseOrder());
        LinkedHashMap<String, Double> sortedCitiesScores = new LinkedHashMap<>();
        for (Double value: values) {
            Iterator<String> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                String key = keyIterator.next();
                if (scoreMap.get(key).equals(value)) {
                    keyIterator.remove();
                    sortedCitiesScores.put(key, value);
                    break;
                }
            }
        }
        ArrayList<Child> sortedChildren = new ArrayList<>();
        for (Map.Entry<String, Double> mapEntry : sortedCitiesScores.entrySet()) {
            sortedChildren.addAll(getChildrenByCity(Utils.stringToCities(mapEntry.getKey())));
        }
        this.children = sortedChildren;
    }
}
