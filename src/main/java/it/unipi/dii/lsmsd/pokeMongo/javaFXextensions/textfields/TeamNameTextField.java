package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Specific TextField for the Team name in the Team scene.
 */
public class TeamNameTextField extends TextField {
    private final int limit = 9;
    /**
     * Instantiates a TextField and associates it with a class style
     * @param text placeholder
     * @param x the x axis position
     * @param y the y axis position
     */
    public TeamNameTextField(String text, int x, int y) {
        super(text);
        relocate(x, y);

        getStyleClass().add("TeamNameTextField");
    }

    @Override
    public void replaceText(int start, int end, String text) {
        super.replaceText(start, end, text);
        verify();
    }

    @Override
    public void replaceSelection(String text) {
        super.replaceSelection(text);
        verify();
    }

    private void verify() {
        if (getText().length() > limit) {
            setText(getText().substring(0, limit));
        }
    }
}
