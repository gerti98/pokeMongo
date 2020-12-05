package it.unipi.dii.lsmsd.pokeMongo.bean;

public class Pokemon {
    private String name;
    private String[] types;
    private int generation;
    private int id;
    private double capture_rate;
    private int points;
    private int height;
    private int weight;
    private String biology;
    private String portrait;
    private String sprite;

    private int slot;

    public Pokemon(String name, String[] types, int generation, int pokedexIndex, double catchRate,
                   int points, int height, int weight, String bio, String portrait, String sprite){
        this.name=name;
        this.types=types;
        this.generation=generation;
        this.id=pokedexIndex;
        this.capture_rate=catchRate;
        this.points=points;
        this.height=height;
        this.weight=weight;
        this.biology=bio;
        this.portrait=portrait;
        this.sprite=sprite;
    }

    public Pokemon(String name, String[] types, String sprite, double capture_rate, int slot) {
        this.name=name;
        this.types=types;
        this.sprite=sprite;
        this.slot = slot;
        this.capture_rate = capture_rate;
    }

    public String getName() {
        return name;
    }

    public double getCapture_rate() {
        return capture_rate;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getGeneration() {
        return generation;
    }

    public int getPoints() {
        return points;
    }

    public int getId() {
        return id;
    }

    public String[] getTypes() {
        return types;
    }

    public String getBiology() {
        return biology;
    }

    public String getPortrait() {
        return portrait;
    }

    public String getSprite() {
        return sprite;
    }

    public int getSlot() {
        return slot;
    }

    public void setBiology(String biology) {
        this.biology = biology;
    }

    public void setCapture_rate(double capture_rate) {
        this.capture_rate = capture_rate;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getTypeSingleString() {
        if (types.length > 1)
            return types[0] + ", " + types[1];
        return types[0];
    }

    public String getTypesSingleStringForCipher() {
        if (types.length > 1)
            return "\"" + types[0] + "\", \"" + types[1] + "\"";
        return "\"" + types[0] + "\"";
    }
}
