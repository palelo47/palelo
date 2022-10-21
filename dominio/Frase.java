package dominio;

import java.util.ArrayList;
import java.util.Arrays;

public class Frase {
    private ArrayList<String> frase;

    public Frase(String frasepresep){
        frase=parseFrase(frasepresep);
    }

    static public ArrayList<String> parseFrase(String fraseprepsep){
        String[] retprev=fraseprepsep.split("\\w");
        return new ArrayList<>(Arrays.asList(retprev));
    }

}
