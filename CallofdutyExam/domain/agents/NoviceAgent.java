package callofduty.domain.agents;

import callofduty.interfaces.Mission;

public class NoviceAgent extends BaseAgent {
    public NoviceAgent(String id, String name) {
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
                super.setRating(super.getRating() + assignedMission.getRating() );
                super.getCompletedMissions().add(assignedMission);
            }
            super.getAssignedMissions().clear();
        }
    }

    @Override
    public String toString() {
        return String.format("Novice Agent - %s\n%s",super.getName(),super.toString());
    }
}
