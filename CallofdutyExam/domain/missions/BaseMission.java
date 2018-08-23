package callofduty.domain.missions;

import callofduty.interfaces.Mission;

public abstract class BaseMission implements Mission {
    private String id;
    private double rating;
    private double bounty;

     BaseMission(String id) {
        this.id = id;
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Double getRating() {
        return this.rating;
    }

    protected void setRating(double rating) {
        this.rating = rating;
    }

    protected void setBounty(double bounty) {
        this.bounty = bounty;
    }

}
