package onehitdungeon.managers;

import onehitdungeon.constants.Config;
import onehitdungeon.core.HeroTrainerImpl;
import onehitdungeon.factories.HeroFactory;
import onehitdungeon.interfaces.Hero;
import onehitdungeon.interfaces.HeroTrainer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DungeonManager implements onehitdungeon.interfaces.DungeonManager {
    private HeroTrainer heroTrainer;
    private Map<String, Hero> heros;
    private Hero currentSelectedHero;
    private int currentDungeonLevelPower;
    private double currentDungeonLevelGold;
    private int level;
    private int numberOfHeros;

    public DungeonManager() {
        this.heroTrainer = new HeroTrainerImpl();
        this.heros = new LinkedHashMap<>();
        this.currentDungeonLevelPower = Config.INITIAL_DUNGEON_LEVEL_POWER;
        this.currentDungeonLevelGold = Config.INITIAL_DUNGEON_LEVEL_GOLD;
        this.level = Config.INITIAL_LEVEL;
    }

    @Override
    public String hero(List<String> arguments) {
        Hero hero = HeroFactory.createHero(arguments);
        this.heros.putIfAbsent(hero.getName(), hero);
        this.numberOfHeros++;
        if (this.numberOfHeros == Config.FIRST_CREATED_HERO_NUMBER) {
            this.currentSelectedHero = hero;
        }
        return String.format(Config.SUCCESSFULLY_ADDED_MESSAGE, hero.getHeroClass(), hero.getName());

    }

    @Override
    public String select(List<String> arguments) {
        String heroName = arguments.get(1);
        Hero hero = this.heros.get(heroName);
        this.currentSelectedHero = hero;
        String heroType = hero.getHeroClass();
        return String.format(Config.SUCCESSFULLY_SELECTED_MESSAGE, heroType, hero.getName());
    }

    @Override
    public String status(List<String> arguments) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(Config.STATUS_LEVEL_MESSAGE,
                this.currentSelectedHero.getName(),
                this.level,
                this.currentSelectedHero.getHeroClass()));
        sb.append(this.currentSelectedHero.toString());
        return sb.toString();
    }

    @Override
    public String fight(List<String> arguments) {
        String result;
        Hero hero = this.currentSelectedHero;
        int heroPower = hero.getTotalBattlePower();
        if (heroPower > this.currentDungeonLevelPower) {
            hero.earnGold(this.currentDungeonLevelGold);
            result = String.format(Config.FIGHT_WON_MESSAGE,
                    this.currentDungeonLevelGold);
        } else {
            this.currentDungeonLevelPower /= Config.DUNGEON_LEVEL_DOUBLED;
            this.currentDungeonLevelGold /= Config.DUNGEON_LEVEL_DOUBLED;
            this.level--;
            result = Config.FIGHT_LOST_MESSAGE;
        }
        return result;
    }

    @Override
    public String advance(List<String> arguments) {
        this.currentDungeonLevelPower *= Config.DUNGEON_LEVEL_DOUBLED;
        this.currentDungeonLevelGold *= Config.DUNGEON_LEVEL_DOUBLED;
        this.level++;
        return String.format(Config.SUCCESSFULLY_ADVANCED_MESSAGE, this.level);
    }

    @Override
    public String train(List<String> arguments) {
        String result;
        Hero hero = this.currentSelectedHero;
        double gold = hero.getGold();
        double price = hero.getTotalPriceForUpgrade();
        if (gold >= price) {
            hero.payGold(hero.getTotalPriceForUpgrade());
            this.heroTrainer.trainHero(hero);
            result = String.format(Config.SUCCESSFULLY_TRAINED_MESSAGE,
                    hero.getTotalBattlePower());
        } else {
            result = String.format(Config.INSUFFICIENT_GOLD_MESSAGE,
                    hero.getTotalPriceForUpgrade(), hero.getGold());
        }
        return result;
    }

    @Override
    public String quit(List<String> arguments) {
        StringBuilder sb = new StringBuilder();

        for (Hero hero : this.heros.values()) {
            sb.append(String.format(Config.HERO_BP_STATUS,
                    hero.getHeroClass(), hero.getName(),
                    hero.getTotalBattlePower()));
        }
        sb.append(Config.TWENTY_SIGNS)
                .append(String.format(Config.DUNGEON_LEVEL_REACHED, this.level));
        return sb.toString();
    }
}
