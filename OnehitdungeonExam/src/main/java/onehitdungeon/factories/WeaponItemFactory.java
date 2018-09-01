package onehitdungeon.factories;

import onehitdungeon.implementation.items.WeaponItem;

public final class WeaponItemFactory {
    public static WeaponItem createWeapoItem(int battlePower,double priceForUpgrade){
        return new WeaponItem(battlePower,priceForUpgrade);
    }

}
