package it.unipi.dii.lsmsd.pokeMongo.userRelated;

public class User {
    private String surname;
    private String name;
    private String username;
    private int numberOfPokeball = 10;

    public User(String username) {  //TODO: da eliminare quando si mette MongoDB
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public int getNumberOfPokeball() {
        return numberOfPokeball;
    }
}
