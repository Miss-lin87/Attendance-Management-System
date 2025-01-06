package sv.linda.functions;

import java.util.Scanner;

public class General {

    public String askUser(String question){
        Scanner in = new Scanner(System.in);
        System.out.println(question);
        return in.nextLine();
    }
}
