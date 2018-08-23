package callofduty.domain.missions;

public class EscortMission extends BaseMission {

    public EscortMission(String id,double rating,double bounty) {
        super(id);
        this.setRating(rating);
        this.setBounty(bounty);

    }

    @Override
    protected void setRating(double rating) {
        super.setRating(rating*0.75);
    }

    @Override
    protected void setBounty(double bounty) {
        super.setBounty(bounty*1.25);
    }
}
