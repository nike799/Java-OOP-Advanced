package onehitdungeon.implementation.heros;

import onehitdungeon.interfaces.ArmorItem;
import onehitdungeon.interfaces.Hero;
import onehitdungeon.interfaces.OffhandItem;
import onehitdungeon.interfaces.WeaponItem;

public abstract class BaseHero implements Hero {
    // name (String), a weapon (WeaponItem), an offhand (OffhandItem) and an armor (ArmorItem).
    private String name;
    private WeaponItem weaponItem;
    private OffhandItem offhandItem;
    private ArmorItem armorItem;
    private double gold;

    protected BaseHero(String name, WeaponItem weaponItem, OffhandItem offhandItem, ArmorItem armorItem) {
        this.name = name;
        this.weaponItem = weaponItem;
        this.offhandItem = offhandItem;
        this.armorItem = armorItem;
    }

    @Override
    public String getHeroClass() {
        int index = this.getClass().getSimpleName().indexOf("Hero");
        String heroType = this.getClass().getSimpleName().substring(0, index);
        return heroType;
    }

    @Override
    public Double getGold() {
        return this.gold;
    }

    @Override
    public void earnGold(Double gold) {
        this.gold += gold;
    }

    @Override
    public void payGold(Double gold) {
        this.gold -= gold;
    }

    @Override
    public WeaponItem getWeapon() {
        return this.weaponItem;
    }

    @Override
    public OffhandItem getOffhand() {
        return this.offhandItem;
    }

    @Override
    public ArmorItem getArmor() {
        return this.armorItem;
    }

    @Override
    public abstract Integer getTotalBattlePower();

    @Override
    public Double getTotalPriceForUpgrade() {
        double price = this.weaponItem.getPriceForUpgrade() +
                this.offhandItem.getPriceForUpgrade() +
                this.armorItem.getPriceForUpgrade();
        return price;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
