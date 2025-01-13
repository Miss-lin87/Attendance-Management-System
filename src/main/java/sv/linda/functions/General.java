package sv.linda.functions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class General {
    private final Scanner input;
    private final Logger log;

    public General(){
        this.input = new Scanner(System.in);
        this.log = Logger.getLogger(getClass().getName());
    }

    private String regex_spell_correct(String options, String textPlayer) {
        for (String elem : options.split(",")) {
            Matcher text = Pattern.compile(textPlayer.toLowerCase()).matcher(elem);
            if (text.find()) {
                return elem;
            }
        }
        return "not found";
    }

    public String askUser(String options, String question) {
        this.log.info(question);
        return regex_spell_correct(options, this.input.nextLine());
    }

    public String askUser(String question){
        this.log.info(question);
        return this.input.nextLine();
    }
}