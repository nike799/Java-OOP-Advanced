package panzer.utilities;

import java.util.ArrayList;
import java.util.Arrays;

public class InputParser {

    public ArrayList<String> parseInput (String input){
        return new ArrayList<>(Arrays.asList(input.split("\\s+")));
    }
}
