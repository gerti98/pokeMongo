package it.unipi.dii.lsmsd.pokeMongo.bean;

import java.util.Date;

public class User {
    private boolean admin;
    private String surname;
    private String name;
    private String username;
    private String password;
    private String email;
    private Date birthDay;
    private String country;
    private String teamName;
    //private int[] Team;
    private Date lastLogin;
    private int dailyPokeball;

    public User(boolean admin, String surname, String name, String username, String password, String email,
                Date birthDay, String country){
        this.admin=admin;
        this.surname=surname;
        this.name=name;
        this.username=username;
        this.password=password;
        this.email=email;
        this.birthDay=birthDay;
        this.country=country;
        this.teamName="Inserisci il nome della tua squadra";
        this.lastLogin=new Date();
        this.dailyPokeball=10;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getSurname(){
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public String getTeamName() {
        return teamName;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public int getDailyPokeball() {
        return dailyPokeball;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void resetDailyPokeball() {
        this.dailyPokeball = 10;
    }

    public void decrementDailyPokeball() {
        this.dailyPokeball--;
    }
}
