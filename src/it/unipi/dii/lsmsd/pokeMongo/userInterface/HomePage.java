package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class HomePage extends PokeSceneWithHeaderAndAggregateBlastoiseCharizard {
    private int buttonPosition = 0;

    public HomePage() {
        displayPokedexButton();

        if(!CurrentUI.isAdmin()) {
            displayTeamButton();
            displayCatchemAllButton();
        }

        displayRankingButton();

        if(!CurrentUI.isAdmin())
            displaySettingsButton();
        else
            displayAddRemovePokemonButton();

        displayLogOutButton();

        setSceneMusic("professor_oak_theme.mp3");
    }

    private void setCSS(Button b) {
        b.setStyle("-fx-font-size: 20px; " +
                "-fx-font-family: 'Arial'; " +
                "-fx-font-weight: bold; " +
                "-fx-background-color: transparent; " +
                "-fx-border-color: #000000; " +
                "-fx-min-width: 280;" +
                "-fx-min-height: 40;");
        b.relocate(490, 230 + buttonPosition*50);
        buttonPosition++;
    }

    private void displayPokedexButton() {
        Button pokedexButton = new Button("POKEDEX");
        //pokedexButton.relocate(500, 200);

        setCSS(pokedexButton);

        sceneNodes.getChildren().add(pokedexButton);
    }

    private void displayTeamButton() {
        Button teamButton = new Button("TEAM");
        //teamButton.relocate(500, 250);

        setCSS(teamButton);

        sceneNodes.getChildren().add(teamButton);
    }

    private void displayCatchemAllButton() {
        Button catchemAllButton = new Button("CATCH'EM ALL");
        //catchemAllButton.relocate(500, 300);

        setCSS(catchemAllButton);

        sceneNodes.getChildren().add(catchemAllButton);
    }

    private void displayRankingButton() {
        Button rankingButton = new Button("RANKING");
        //rankingButton.relocate(500, 350);

        setCSS(rankingButton);

        sceneNodes.getChildren().add(rankingButton);
    }

    private void displaySettingsButton() {
        Button settingButton = new Button("SETTINGS");
        //settingButton.relocate(500, 400);

        setCSS(settingButton);

        sceneNodes.getChildren().add(settingButton);
    }

    private void displayAddRemovePokemonButton() {
        Button logOutButton = new Button("ADD/REMOVE POKEMON");
        //logOutButton.relocate(500, 400);

        setCSS(logOutButton);

        logOutButton.setOnAction((ActionEvent ev)-> logOutButtonAction());

        sceneNodes.getChildren().add(logOutButton);
    }

    private void displayLogOutButton() {
        Button logOutButton = new Button("LOG OUT");
        //logOutButton.relocate(500, 450);

        setCSS(logOutButton);

        logOutButton.setOnAction((ActionEvent ev)-> logOutButtonAction());

        sceneNodes.getChildren().add(logOutButton);
    }

    private void logOutButtonAction() {
        CurrentUI.changeScene(SceneNames.LOGIN);

        // TODO: exit from the account
    }
}
