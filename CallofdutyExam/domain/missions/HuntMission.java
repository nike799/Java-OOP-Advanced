package callofduty.domain.missions;

public class HuntMission extends BaseMission {

    public HuntMission (String id,double rating,double bounty) {
        super(id);
        this.setRating(rating);
        this.setBounty(bounty);
    }

    @Override
    protected void setRating(double rating) {
        super.setRating(rating * 1.5);
    }

    @Override
    protected void setBounty(double bounty) {
        super.setBounty(bounty * 2.0);
    }
}
