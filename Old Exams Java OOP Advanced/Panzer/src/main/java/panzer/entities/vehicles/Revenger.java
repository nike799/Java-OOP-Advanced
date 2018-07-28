package panzer.entities.vehicles;



import java.math.BigDecimal;

public class Revenger extends AbstractVehicle {
    public Revenger (String model, double weight, BigDecimal price, int attack, int defense, int hitPoints) {
        super.setModel(model);
        super.setWeight(weight);
        super.setAttack((int) (attack * 2.5));
        super.setPrice(price.multiply(BigDecimal.valueOf(1.5)));
        super.setDefense((int) (defense * 0.5));
        super.setHitPoints((int) (hitPoints * 0.5));
    }
}
