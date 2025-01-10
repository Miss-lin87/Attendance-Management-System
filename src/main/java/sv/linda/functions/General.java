package sv.linda.functions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class General {
    Logger logger = Logger.getLogger(getClass().getName());

    private String[] Moptions(String input) {
        return input.split(",");
    }

    private String regex_spell_correct(String textOptions, String textPlayer) {
        for (String elem : Moptions(textOptions)) {
            Matcher text = Pattern.compile(textPlayer.toLowerCase()).matcher(elem);
            if (text.find()) {
                return elem;
            }
        }
        return "not found";
    }

    public String askUser(String options, String question) {
        Scanner in = new Scanner(System.in);
        logger.info(question);
        return regex_spell_correct(options, in.nextLine());
    }

    public String askUser(String question){
        Scanner in = new Scanner(System.in);
        logger.info(question);
        return in.nextLine();
    }

    public Map<String, Boolean> makeStudentmap(List<String> students) {
        Map<String, Boolean> studentMap = new HashMap<>();
        for (String student : students) {
            studentMap.put(student, false);
        }
        return studentMap;
    }
}