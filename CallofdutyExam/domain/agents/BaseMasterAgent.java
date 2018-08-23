package callofduty.domain.agents;

import callofduty.interfaces.BountyAgent;

public abstract class BaseMasterAgent extends BaseAgent implements BountyAgent {
    private double bounty;

    BaseMasterAgent(String id, String name) {
        super(id, name);

    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }

    protected void setBounty(double bounty) {
        this.bounty = bounty;
    }

}
