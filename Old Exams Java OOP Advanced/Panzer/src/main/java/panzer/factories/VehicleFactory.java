package panzer.factories;

import panzer.contracts.Vehicle;
import panzer.entities.vehicles.Revenger;
import panzer.entities.vehicles.Vanguard;
import panzer.utilities.Constants;

import java.math.BigDecimal;
import java.util.List;

public class VehicleFactory {

    public Vehicle createVehicle(List<String> elements) {
        Vehicle vehicle = null;
        String vehicleType = elements.get(1);
        String model = elements.get(2);
        double weight = Double.parseDouble(elements.get(3));
        BigDecimal price = new BigDecimal(elements.get(4));
        int attack = Integer.parseInt(elements.get(5));
        int defense = Integer.parseInt(elements.get(6));
        int hitPoints = Integer.parseInt(elements.get(7));
        switch (vehicleType) {
            case Constants.VEHICLE_REVENGER:
                vehicle = new Revenger(model, weight, price, attack, defense, hitPoints);
                break;
            case Constants.VEHICLE_VANGUARD:
                vehicle = new Vanguard(model, weight, price, attack, defense, hitPoints);
                break;

        }
        return vehicle;
    }
}
