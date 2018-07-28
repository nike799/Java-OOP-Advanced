package panzer.entities.parts;

import panzer.contracts.DefenseModifyingPart;

import java.math.BigDecimal;

public class ShellPart extends AbstractPart implements DefenseModifyingPart {

   private int defenseModifier;

    public ShellPart(String model, double weight, BigDecimal price, int defenseModifier) {
        super(model, weight, price);
        this.setDefenseModifier(defenseModifier);
    }

    @Override
    public int getDefenseModifier() {
        return this.defenseModifier;
    }

    protected void setDefenseModifier(int defenseModifier) {
        this.defenseModifier = defenseModifier;
    }

}
