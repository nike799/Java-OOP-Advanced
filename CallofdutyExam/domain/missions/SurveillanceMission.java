package callofduty.domain.missions;

public class SurveillanceMission extends BaseMission {
    public SurveillanceMission(String id, double rating, double bounty) {
        super(id);
        this.setRating(rating);
        this.setBounty(bounty);
    }

    @Override
    protected void setRating(double rating) {

        super.setRating(rating * 0.25);
    }

    @Override
    protected void setBounty(double bounty) {
        super.setBounty(bounty * 1.5);
    }
}
