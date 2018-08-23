package callofduty.domain.agents;

import callofduty.interfaces.Mission;

public class MasterAgent extends BaseMasterAgent {

    public MasterAgent(String id, String name) {
        super(id, name);
    }

    @Override
    public void acceptMission(Mission mission) {
        super.getAssignedMissions().add(mission);
    }

    @Override
    public void completeMissions() {
        {
            for (Mission assignedMission : super.getAssignedMissions()) {
                super.setRating(super.getRating() + assignedMission.getRating());
                super.setBounty(super.getBounty() + assignedMission.getBounty());
                super.getCompletedMissions().add(assignedMission);
            }
            super.getAssignedMissions().clear();
        }
    }

    @Override
    public String toString() {
        return String.format("Master Agent - %s\n%s\n" +
                "Bounty Earned: $%.2f",super.getName(),super.toString(),super.getBounty());
    }

    @Override
    public Double getBounty() {
        return super.getBounty();
    }
}
