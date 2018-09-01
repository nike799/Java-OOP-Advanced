package onehitdungeon.factories;

import onehitdungeon.constants.Config;
import onehitdungeon.implementation.heros.MageHero;
import onehitdungeon.implementation.heros.PaladinHero;
import onehitdungeon.interfaces.ArmorItem;
import onehitdungeon.interfaces.Hero;
import onehitdungeon.interfaces.OffhandItem;
import onehitdungeon.interfaces.WeaponItem;

import java.util.List;

public final class HeroFactory {

    public static Hero createHero(List<String> arguments){
        // type (string), name (string).
        String type = arguments.get(1);
        String name = arguments.get(2);
        Hero hero =null;
        WeaponItem weaponItem = null;
        OffhandItem offhandItem =null;
        ArmorItem armorItem =null;

        switch (type){
            case Config.TYPE_HERO_MAGE:
                weaponItem = WeaponItemFactory.createWeapoItem(45,15);
                offhandItem = OffhandItemFactory.createOffhandItem(25,20);
                armorItem = ArmorItemFactory.createArmorItem(10,25);
                hero =new MageHero(name,weaponItem,offhandItem,armorItem);
                break;
            case Config.TYPE_HERO_PALADIN:
                weaponItem = WeaponItemFactory.createWeapoItem(20,10);
                offhandItem = OffhandItemFactory.createOffhandItem(10,10);
                armorItem = ArmorItemFactory.createArmorItem(25,20);
                hero = new PaladinHero(name,weaponItem,offhandItem,armorItem);
                break;
        }
        return hero;
    }

}
