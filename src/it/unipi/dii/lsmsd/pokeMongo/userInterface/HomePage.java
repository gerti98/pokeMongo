package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.HomePageCentralButton;
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

    private void displayPokedexButton() {
        HomePageCentralButton pokedexButton = new HomePageCentralButton("POKEDEX");

        sceneNodes.getChildren().add(pokedexButton);
    }

    private void displayTeamButton() {
        HomePageCentralButton teamButton = new HomePageCentralButton("TEAM");
        teamButton.setOnAction((ActionEvent ev)-> teamButtonAction());

        sceneNodes.getChildren().add(teamButton);
    }

    private void displayCatchemAllButton() {
        HomePageCentralButton catchemAllButton = new HomePageCentralButton("CATCH'EM ALL");
        catchemAllButton.setOnAction((ActionEvent ev)-> catchemAllButtonAction());

        sceneNodes.getChildren().add(catchemAllButton);
    }

    private void displayRankingButton() {
        HomePageCentralButton rankingButton = new HomePageCentralButton("RANKING");

        sceneNodes.getChildren().add(rankingButton);
    }

    private void displaySettingsButton() {
        HomePageCentralButton settingButton = new HomePageCentralButton("SETTINGS");

        settingButton.setOnAction((ActionEvent ev)-> settingButtonAction());

        sceneNodes.getChildren().add(settingButton);
    }

    private void displayAddRemovePokemonButton() {
        HomePageCentralButton addRemoveButton = new HomePageCentralButton("ADD/REMOVE POKEMON");

        sceneNodes.getChildren().add(addRemoveButton);
    }

    private void displayLogOutButton() {
        HomePageCentralButton logOutButton = new HomePageCentralButton("LOG OUT");

        logOutButton.setOnAction((ActionEvent ev)-> logOutButtonAction());

        sceneNodes.getChildren().add(logOutButton);
    }

    ///////////////// ACTIONS /////////////////
    private void logOutButtonAction() {
        CurrentUI.userExit();
        CurrentUI.changeScene(SceneNames.LOGIN);

        // TODO: exit from the account
    }

    private void teamButtonAction() {
        CurrentUI.changeScene(SceneNames.TEAM);
    }

    private void settingButtonAction() {
        CurrentUI.changeScene(SceneNames.SETTINGS);
    }

    private void catchemAllButtonAction() {
        CurrentUI.changeScene(SceneNames.CATCHEMALL);
    }
}