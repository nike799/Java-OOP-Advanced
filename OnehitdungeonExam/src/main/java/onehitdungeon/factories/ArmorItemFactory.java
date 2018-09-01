package onehitdungeon.factories;

import onehitdungeon.implementation.items.ArmorItem;

public final class ArmorItemFactory {
    public static ArmorItem createArmorItem(int battlePower, double priceForUpgrade){
        return new ArmorItem(battlePower,priceForUpgrade);
    }
}
