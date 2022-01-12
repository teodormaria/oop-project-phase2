package santa;

import common.Constants;
import enums.Category;
import enums.Cities;
import enums.ElvesType;
import fileio.input.ChildInputData;
import gifts.Gift;
import santa.averageScoreStrategies.StrategyFactory;

import java.util.ArrayList;

/**
 * Class that stores information about a child
 */
public final class Child implements Comparable<Child> {
    /**
     * Child ID
     */
    private final int id;
    /**
     * Last Name
     */
    private final String lastName;
    /**
     * First Name
     */
    private final String firstName;
    /**
     * Age
     */
    private final int age;
    /**
     * City enum
     */
    private final Cities city;
    /**
     * List of previous nice scores
     */
    private final ArrayList<Double> niceScoreHistory;
    /**
     * List of preferred categories
     */
    private final ArrayList<Category> giftsPreferences;
    /**
     * Budget assigned by Santa to be spent on child
     */
    private double assignedBudget;
    /**
     * Average niceScore
     */
    private final double averageScore;
    /**
     * List of gifts received by child
     */
    private final ArrayList<Gift> receivedGifts;
    /**
     * Child's elf
     */
    private final ElvesType elf;
    /**
     * Bonus to be added to nice score
     */
    private final double niceScoreBonus;

    public Child(final ChildInputData input) {
        this.id = input.getId();
        this.lastName = input.getLastName();
        this.firstName = input.getFirstName();
        this.age = input.getAge();
        this.city = input.getCity();
        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.add(input.getNiceScore());
        this.giftsPreferences = input.getGiftsPreferences();
        this.receivedGifts = new ArrayList<>();
        this.elf = input.getElf();
        this.niceScoreBonus = input.getNiceScoreBonus();
        double calculatedAverageScore = StrategyFactory.useStrategy(age)
                .getNiceScore(niceScoreHistory);
        calculatedAverageScore += calculatedAverageScore * this.niceScoreBonus
                / Constants.MAX_PERCENTAGE;
        if (calculatedAverageScore > Constants.MAX_NICE_SCORE) {
            this.averageScore = Constants.MAX_NICE_SCORE;
        } else {
            this.averageScore = calculatedAverageScore;
        }
    }

    public Child(final int id, final String lastName, final String firstName, final int age,
                 final Cities city, final ArrayList<Double> niceScoreHistory,
                 final ArrayList<Category> giftsPreferences, final double niceScoreBonus,
                 final ElvesType elf) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScoreHistory = niceScoreHistory;
        this.giftsPreferences = giftsPreferences;
        this.receivedGifts = new ArrayList<>();
        this.niceScoreBonus = niceScoreBonus;
        double calculatedAverageScore = StrategyFactory.useStrategy(age)
                .getNiceScore(niceScoreHistory);
        calculatedAverageScore += calculatedAverageScore
                * this.niceScoreBonus / Constants.MAX_PERCENTAGE;
        if (calculatedAverageScore > Constants.MAX_NICE_SCORE) {
            this.averageScore = Constants.MAX_NICE_SCORE;
        } else {
            this.averageScore = calculatedAverageScore;
        }
        this.elf = elf;
    }

    public Child(final Child child) {
        this.id = child.getId();
        this.lastName = child.getLastName();
        this.firstName = child.getFirstName();
        this.age = child.getAge() + 1;
        this.city = child.getCity();
        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.addAll(child.getNiceScoreHistory());
        this.giftsPreferences = child.getGiftsPreferences();
        this.receivedGifts = new ArrayList<>();
        this.elf = child.getElf();
        this.niceScoreBonus = child.getNiceScoreBonus();
        this.averageScore = child.getAverageScore();
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public Cities getCity() {
        return city;
    }

    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public double getAssignedBudget() {
        return assignedBudget;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public void setAssignedBudget(final double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public ElvesType getElf() {
        return elf;
    }

    public double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    /**
     * Given the value of a budgetUnit, calculates budget assigned to child
     * @param budgetUnit value of a single budget  unit
     */
    public void calculateAssignedBudget(final double budgetUnit) {
        double budget = budgetUnit * this.averageScore;
        if (this.elf.equals(ElvesType.BLACK)) {
            budget = budget - budget * Constants.ELF_BOOST / Constants.MAX_PERCENTAGE;
        } else if (this.elf.equals(ElvesType.PINK)) {
            budget = budget + budget * Constants.ELF_BOOST / Constants.MAX_PERCENTAGE;
        }
        this.assignedBudget = budget;
    }

    /**
     *
     * @return ArrayList of categories converted to strings
     */
    public ArrayList<String> getGiftsPreferencesToStrings() {
        ArrayList<String> categoriesNames = new ArrayList<>();
        for (Category category : giftsPreferences) {
            categoriesNames.add(category.toString());
        }
        return categoriesNames;
    }

    /**
     * adds gift to receivedGifts
     * @param gift to be given
     */
    public void giveGift(final Gift gift) {
        this.receivedGifts.add(gift);
    }

    @Override
    public int compareTo(final Child child) {
        return Integer.compare(this.id, child.getId());
    }
}
