package panzer.entities.vehicles;

import panzer.contracts.Part;
import panzer.contracts.Vehicle;
import panzer.entities.parts.ArsenalPart;
import panzer.entities.parts.EndurancePart;
import panzer.entities.parts.ShellPart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractVehicle implements Vehicle {
    private String model;
    private double weight;
    private BigDecimal price;
    private int attack;
    private int defense;
    private int hitPoints;
    private List<Part> parts;

    public AbstractVehicle() {
        this.parts = new ArrayList<>();
    }

    @Override
    public double getTotalWeight() {
        return this.parts.stream().mapToDouble(Part::getWeight).sum() + this.weight;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return this.parts.stream().map(Part::getPrice).reduce(BigDecimal::add).get().add(this.price);
    }

    @Override
    public long getTotalAttack() {
        long totalArsenalAttack = 0;
        List<Part> arsenalPart = this.parts.stream()
                .filter(p -> p.getClass().getSimpleName().equals("ArsenalPart")).collect(Collectors.toList());
        for (Part part : arsenalPart) {
            totalArsenalAttack += ((ArsenalPart) part).getAttackModifier();
        }
        return totalArsenalAttack + this.attack;
    }

    @Override
    public long getTotalDefense() {
        long totalDefence = 0;
        List<Part> shellPart = this.parts.stream()
                .filter(p -> p.getClass().getSimpleName().equals("ShellPart")).collect(Collectors.toList());
        for (Part part : shellPart) {
            totalDefence += ((ShellPart) part).getDefenseModifier();
        }
        return totalDefence + this.defense;
    }

    @Override
    public long getTotalHitPoints() {
        long totalHitPoints = 0;
        List<Part> endurancePart = this.parts.stream()
                .filter(p -> p.getClass().getSimpleName().equals("EndurancePart")).collect(Collectors.toList());
        for (Part part : endurancePart) {
            EndurancePart arsenalPart1 = (EndurancePart) part;
            totalHitPoints += ((EndurancePart) part).getHitPointsModifier();
        }
        return totalHitPoints + this.hitPoints;
    }

    @Override
    public void addArsenalPart(Part arsenalPart) {
        this.parts.add(arsenalPart);
    }

    @Override
    public void addShellPart(Part shellPart) {
        this.parts.add(shellPart);

    }

    @Override
    public void addEndurancePart(Part endurancePart) {
        this.parts.add(endurancePart);
    }

    @Override
    public Iterable<Part> getParts() {
        return this.parts;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    protected void setModel(String model) {
        this.model = model;
    }

    protected void setWeight(double weight) {
        this.weight = weight;
    }

    protected void setPrice(BigDecimal price) {
        this.price = price;
    }

    protected void setAttack(int attack) {
        this.attack = attack;
    }

    protected void setDefense(int defense) {
        this.defense = defense;
    }

    protected void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s - %s\n" +
                        "Total Weight: %.3f\n" +
                        "Total Price: %.3f\n" +
                        "Attack: %d\n" +
                        "Defense: %d\n" +
                        "HitPoints: %d\n",
                this.getClass().getSimpleName(),
                this.model,
                this.getTotalWeight(),
                this.getTotalPrice(),
                this.getTotalAttack(),
                this.getTotalDefense(),
                this.getTotalHitPoints()));
        sb.append(String.format("Parts: "));

        if (this.parts.isEmpty()) {
            sb.append("none");
        } else {
            sb.append(this.parts.stream()
                    .map(Part::getModel).collect(Collectors.joining(", ")));
        }
        return sb.toString();
    }
}
