package edu.wgu.d387_sample_code.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLanguageMessage {
    private ResourceBundle bundle;

    public String getWelcomeMessages() {
        return bundle.getString("welcomeMessage");
    }
    public DisplayLanguageMessage() {

    }
    public DisplayLanguageMessage(String lang, String ctry){
        Locale locale = new Locale(lang, ctry);
        bundle = ResourceBundle.getBundle("welcome", locale);
        System.out.printf("%s, %s%n",lang, ctry);
    }
}
