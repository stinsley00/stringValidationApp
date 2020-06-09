package tinsley;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main {

    /**
     * Main
     * call the validation service for each arg
     * this service will create a pojo for each arg
     * the pojo will contain parameters indicating string validity
     *
     * @param args - passed in when program is run
     */

    public static void main(String[] args) {
        String language = "en";
        String country;
        String[] langArr;
        //default to english
        Locale locale = new Locale(language);
        ResourceBundle resource = ResourceBundle.getBundle("i18N", locale);


        String t;
        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("-L")) {
                i++;
                if (args[i].contains("-")) {
                    langArr = args[i].split("-");
                    language = langArr[0];
                    country = langArr[1];
                    locale = new Locale(language, country);
                    resource = ResourceBundle.getBundle("i18N", locale);
                } else if (!args[i].contains("-")) {
                    //if no country is specified
                    language = args[i];
                    locale = new Locale(language);
                    resource = ResourceBundle.getBundle("i18N", locale);
                } else {
                    System.out.println("Please specify a language and region in the en-US format, or Simply a language like es");
                }

            } else {
                t = "T" + i;
                ThreadController x = new ThreadController("T" + t, args[i].trim(), resource);
                x.start();
            }

        }


    }
}
