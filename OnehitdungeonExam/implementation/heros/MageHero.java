package onehitdungeon.implementation.heros;

import onehitdungeon.interfaces.ArmorItem;
import onehitdungeon.interfaces.OffhandItem;
import onehitdungeon.interfaces.WeaponItem;

public class MageHero extends BaseHero {
    public MageHero(String name, WeaponItem weaponItem, OffhandItem offhandItem, ArmorItem armorItem) {
        super(name, weaponItem, offhandItem, armorItem);
    }

    @Override
    public Integer getTotalBattlePower() {
        // Paladin – ((weaponBattlePower + offhandBattlePower + armorBattlePower) * 4) / 9
        int result=  ((super.getWeapon().getBattlePower() + super.getArmor().getBattlePower()- super.getOffhand().getBattlePower() ) * 3) / 4;
        return result;
    }

    @Override
    public String toString() {
        return String.format(
                "* Staff - %d (BP)\n" +
                        "* Orb - %d (BP)\n" +
                        "* Cape - %d (BP)\n" +
                        "####################\n" +
                        "Gold: %.2f\n" +
                        "Upgrade cost: %.2f",
                this.getWeapon().getBattlePower(),
                this.getOffhand().getBattlePower(),
                this.getArmor().getBattlePower(),
                this.getGold(),
                this.getTotalPriceForUpgrade());
    }

}
