package it.unipi.dii.lsmsd.pokeMongo.utils;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.*;

public class FormValidatorPokeMongo {

    /**
     * In this section are present the event handler for the 'setOnKeyReleased' event in the form.
     */
    public static void handleName(TextField nameTF, Label invalidNameLabel){
        if(FormValidatorPokeMongo.isPersonNoun(nameTF.getText()))
            invalidNameLabel.setVisible(false);
        else
            invalidNameLabel.setVisible(true);
    }

    /**
     * Check if the string contains only letters, spaces, dots and apostrophes.
     */
    public static boolean isPersonNoun(String possibleNoun){
        Pattern pattern = Pattern.compile("^[a-zA-Z '.]*$");
        Matcher matcher = pattern.matcher(possibleNoun);
        return matcher.find();
    }

    public static void handleEmail(TextField emailTF, Label invalidEmailLabel){
        if(FormValidatorPokeMongo.isValidEmail(emailTF.getText()))
            invalidEmailLabel.setVisible(false);
        else
            invalidEmailLabel.setVisible(true);
    }

    /**
     * Check if the email follows the format example@domain.tld
     */
    public static boolean isValidEmail(String possibleEmail){
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(possibleEmail);
        return matcher.find();
    }

    public static void handlePassword(TextField passwordTF, Label invalidPasswordLabel){
        if(FormValidatorPokeMongo.isValidPassword(passwordTF.getText()))
            invalidPasswordLabel.setVisible(false);
        else
            invalidPasswordLabel.setVisible(true);
    }

    /**
     * Checks if the password contains minimum eight characters, at least one letter and one number.
     */
    public static boolean isValidPassword(String possiblePassword){
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        Matcher matcher = pattern.matcher(possiblePassword);
        return matcher.find();
    }

    public static void handleConfirmField(TextField fieldTF, TextField confirmFieldTF, Label invalidConfirmFieldLabel){
        String password = fieldTF.getText(), confirmPassword = confirmFieldTF.getText();

        if(password.equals(confirmPassword))
            invalidConfirmFieldLabel.setVisible(false);
        else
            invalidConfirmFieldLabel.setVisible(true);
    }

    /**
     * Checks if the birthday date selected is valid: future dates cannot be picked
     */
    public static void handleBirthday(DatePicker birthdayDP, Label invalidBirthdayLabel){
        LocalDate localDate = birthdayDP.getValue();
        LocalDate today = LocalDate.now();
        System.out.println(today);

        if(localDate.isAfter(today)){
            invalidBirthdayLabel.setVisible(true);
        } else {
            invalidBirthdayLabel.setVisible(false);
        }
    }
}