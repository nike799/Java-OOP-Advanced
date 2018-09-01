package onehitdungeon.factories;

import onehitdungeon.implementation.items.OffhandItem;

public final class OffhandItemFactory {
    public static OffhandItem createOffhandItem(int battlePower,double priceForUpgrade){
        return new OffhandItem(battlePower,priceForUpgrade);
    }

}
