package panzer.entities.vehicles;

import java.math.BigDecimal;

public class Vanguard extends AbstractVehicle {
    public Vanguard(String model, double weight, BigDecimal price, int attack, int defense, int hitPoints) {
        super.setModel(model);
        super.setWeight(weight * 2);
        super.setAttack((int) (attack * 0.75));
        super.setPrice(price);
        super.setDefense((int) (defense * 1.5));
        super.setHitPoints((int) (hitPoints * 1.75));
    }

}
