package onehitdungeon.implementation.items;

import onehitdungeon.interfaces.Item;

public abstract class BaseItem implements Item {
    private int  battlePower;
    private  double priceForUpgrade;

    protected BaseItem(int battlePower, double priceForUpgrade) {
        this.setBattlePower(battlePower);
        this.setPriceForUpgrade(priceForUpgrade);
    }

    @Override
    public Integer getBattlePower() {
        return this.battlePower;
    }

    @Override
    public Double getPriceForUpgrade() {
        return this.priceForUpgrade;
    }

    protected void setBattlePower(int battlePower) {
        this.battlePower = battlePower;
    }

    protected void setPriceForUpgrade(double priceForUpgrade) {
        this.priceForUpgrade = priceForUpgrade;
    }
}
