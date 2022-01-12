package fileio.input;

import enums.Category;
import enums.Cities;
import enums.ElvesType;

import java.util.ArrayList;

/**
 * Class used to store information received as input about children
 */
public record ChildInputData(int id, String lastName, String firstName, int age,
                             Cities city, double niceScore,
                             ArrayList<Category> giftsPreferences,
                             double niceScoreBonus, ElvesType elf) {

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
