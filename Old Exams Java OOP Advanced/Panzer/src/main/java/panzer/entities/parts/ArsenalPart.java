package panzer.entities.parts;

import panzer.contracts.AttackModifyingPart;

import java.math.BigDecimal;

public class ArsenalPart extends AbstractPart implements AttackModifyingPart {
    private int attackModifier;

    public ArsenalPart(String model, double weight, BigDecimal price, int attackModifier) {
        super(model, weight, price);
        this.setAttackModifier(attackModifier);
    }

    @Override
    public int getAttackModifier() {

        return this.attackModifier;
    }
    protected void setAttackModifier(int attackModifier) {
        this.attackModifier = attackModifier;
    }
}
