package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.persistence.UserNetworkManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserNetworkManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.Button;

public class FavoriteButton extends Button {
    private final String imgFavoriteOnLocation = "file:img/star.png";
    private final String imgFavoriteOffLocation = "file:img/emptyStar.png";
    private String name;

    private boolean favorite;

    public FavoriteButton(int x, int y, int dimension, String name) {
        super();
        Logger.vvlog("Creating FavoriteButton at (" + x + ", " + y + ")");

        this.name = name;

        relocate(x, y);
        setPrefSize(dimension, dimension);

        setOnAction(e -> changeStyle());

        // TAKE INFO FROM NEO4J
        favorite = isFavorite();
        setImage();
    }

    /**
     * Sets the image if the favorite is on or is off.
     */
    private void setImage() {
        Logger.vvlog("Setting favoriteStar image");
        if (favorite) {
            setStyle(" -fx-border-color: transparent;" +
                    " -fx-background-size: 35;" +
                    " -fx-background-color: trasparent; " +
                    "-fx-background-repeat: no-repeat;" +
                    " -fx-background-image: url(" + imgFavoriteOffLocation + ")");
        }
        else {
            setStyle(" -fx-border-color: transparent;" +
                    " -fx-background-size: 35;" +
                    " -fx-background-color: trasparent; " +
                    "-fx-background-repeat: no-repeat;" +
                    " -fx-background-image: url(" + imgFavoriteOnLocation + ")");
        }
    }

    private void changeStyle() {
        favorite = !favorite;
        setImage();
    }

    private boolean isFavorite() {
        UserNetworkManager userNetworkManager = UserNetworkManagerFactory.buildManager();
        return userNetworkManager.isFavorite(CurrentUI.getUser().getUsername(), name);
    }
}