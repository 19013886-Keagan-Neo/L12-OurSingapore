package sg.edu.rp.c346.id19013886.l12_oursingapore;

import java.io.Serializable;

public class Island implements Serializable {

    private int id;
    private String name;
    private String description;
    private int squarekm;
    private int stars;

    public Island(int id, String name, String description, int squarekm, int stars) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.squarekm = squarekm;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public Island setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Island setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Island setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getSquarekm() {
        return squarekm;
    }

    public Island setSquarekm(int squarekm) {
        this.squarekm = squarekm;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public Island setStars(int stars) {
        this.stars = stars;
        return this;
    }

    @Override
    public String toString() {
        String starsString = "";
        if (stars == 5) {
            starsString = "*****";
        } else if (stars == 4) {
            starsString = "****";
        } else if (stars == 3) {
            starsString = "***";
        } else if (stars == 2) {
            starsString = "**";
        } else {
            starsString = "*";
        }

        return starsString;
    }

}
