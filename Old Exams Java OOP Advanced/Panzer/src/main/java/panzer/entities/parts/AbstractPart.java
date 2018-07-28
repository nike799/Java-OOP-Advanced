package panzer.entities.parts;

import panzer.contracts.Part;

import java.math.BigDecimal;

public abstract class AbstractPart implements Part {
    private String model;
    private double weight;
    private BigDecimal price;

    public AbstractPart(String model, double weight, BigDecimal price) {
        this.setModel(model);
        this.setWeight(weight);
        this.setPrice(price);
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
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
}
