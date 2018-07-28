package panzer.entities.parts;

import panzer.contracts.HitPointsModifyingPart;

import java.math.BigDecimal;

public class EndurancePart extends AbstractPart implements HitPointsModifyingPart {
    private int hitPointsModifier;

    public EndurancePart(String model, double weight, BigDecimal price, int hitPointsModifier) {
        super(model, weight, price);
        this.setHitPointsModifier(hitPointsModifier);
    }

    protected void setHitPointsModifier(int hitPointsModifier) {
        this.hitPointsModifier = hitPointsModifier;
    }

    @Override
    public int getHitPointsModifier() {
        return this.hitPointsModifier;
    }
}
