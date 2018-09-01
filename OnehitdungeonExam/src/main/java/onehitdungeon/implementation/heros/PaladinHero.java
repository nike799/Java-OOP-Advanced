package onehitdungeon.implementation.heros;

import onehitdungeon.interfaces.ArmorItem;
import onehitdungeon.interfaces.OffhandItem;
import onehitdungeon.interfaces.WeaponItem;

public class PaladinHero extends BaseHero {

    public PaladinHero(String name, WeaponItem weaponItem, OffhandItem offhandItem, ArmorItem armorItem) {
        super(name, weaponItem, offhandItem, armorItem);

    }

    @Override
    public Integer getTotalBattlePower() {
        // Paladin – ((weaponBattlePower + offhandBattlePower + armorBattlePower) * 4) / 9
      return   ((super.getWeapon().getBattlePower() + super.getOffhand().getBattlePower() + super.getArmor().getBattlePower()) * 4)/9;
    }
    @Override
    public String toString() {
        return   String.format(
                "* Mace - %d (BP)\n" +
                        "* Shield - %d (BP)\n" +
                        "* Cuirass - %d (BP)\n" +
                        "####################\n" +
                        "Gold: %.2f\n" +
                        "Upgrade cost: %.2f",
                this.getWeapon().getBattlePower(),
                this.getOffhand().getBattlePower(),
                this.getArmor().getBattlePower(),
                super.getGold(),
                this.getTotalPriceForUpgrade());
    }
}
