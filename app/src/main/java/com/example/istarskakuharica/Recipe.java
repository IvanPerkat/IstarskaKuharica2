package com.example.istarskakuharica;

public class Recipe {
    private final String title;
    private final String[] steps;
    private final int image;
    private final String preparation;
    private final String background;
    private boolean favourite;

    public Recipe(String title, String[] steps, int image, String preparation, String background) {
        this.title = title;
        this.steps = steps;
        this.image = image;
        this.preparation = preparation;
        this.background = background;
        this.favourite = false;
    }

    public String getTitle() {
        return title;
    }

    public String[] getSteps() {
        return steps;
    }

    public int getImage() {
        return image;
    }

    public String getPreparation() {
        return preparation;
    }

    public String getBackground() {
        return background;
    }

    public boolean getFavourite() {
        return favourite;
    }

    public void toggleFavourite() {
        this.favourite = !this.favourite;
    }
}
