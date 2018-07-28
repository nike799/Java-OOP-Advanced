package panzer.factories;

import panzer.contracts.Part;
import panzer.entities.parts.ArsenalPart;
import panzer.entities.parts.EndurancePart;
import panzer.entities.parts.ShellPart;
import panzer.utilities.Constants;

import java.math.BigDecimal;
import java.util.List;

public class PartFactory {

    public Part createPart(List<String> arguments) {
        Part part = null;
        String partType = arguments.get(2);
        String model = arguments.get(3);
        double weight = Double.parseDouble(arguments.get(4));
        BigDecimal price = new BigDecimal(arguments.get(5));
        int additionalElement = Integer.parseInt(arguments.get(6));
        switch (partType) {
            case Constants.PART_ARSENAL:
                part = new ArsenalPart(model, weight, price, additionalElement);
                break;
            case Constants.PART_SHELL:
                part = new ShellPart(model, weight, price, additionalElement);
                break;
            case Constants.PART_ENDURANCE:
                part = new EndurancePart(model, weight, price, additionalElement);
                break;
        }
        return part;
    }
}
