package fileio.input;

import enums.Category;
import enums.Cities;
import enums.ElvesType;

import java.util.ArrayList;

/**
 * Class used to store information received as input about children
 */
public final class ChildInputData {
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
     * Initial niceScore
     */
    private final double niceScore;
    /**
     * List of preferred categories
     */
    private final ArrayList<Category> giftsPreferences;
    /**
     * Child's elf
     */
    private final ElvesType elf;
    /**
     * Bonus to be added to nice score
     */
    private final double niceScoreBonus;

    public ChildInputData(final int id, final String lastName, final String firstName,
                          final int age, final Cities city, final double niceScore,
                          final ArrayList<Category> giftsPreferences, final double niceScoreBonus,
                          final ElvesType elf) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
        this.niceScoreBonus = niceScoreBonus;
        this.elf = elf;
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

    public double getNiceScore() {
        return niceScore;
    }

    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    public ElvesType getElf() {
        return elf;
    }
}
