package callofduty.domain.agents;

import callofduty.interfaces.Agent;
import callofduty.interfaces.Mission;
import callofduty.utilities.ReflectField;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAgent implements Agent {
    private String id;
    private String name;
    private double rating;
    private List<Mission> assignedMissions;
    private List<Mission> completedMissions;

    BaseAgent(String id, String name) {
        this.id = id;
        this.name = name;
        this.rating = 0;
        this.assignedMissions = new ArrayList<>();
        this.completedMissions = new ArrayList<>();
    }

    @Override
    public abstract void acceptMission(Mission mission);

    protected List<Mission> getAssignedMissions() {
        return assignedMissions;
    }

    protected List<Mission> getCompletedMissions() {
        return completedMissions;
    }

    protected void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public abstract void completeMissions();

    @Override
    public String getId() {

        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getRating() {
        return this.rating;
    }

    @Override
    public String toString() {
        List<Mission> assignedMissions = ReflectField.getCollectionOfMissions(this, 3);
        List<Mission> comletedmissions = ReflectField.getCollectionOfMissions(this, 4);

        return String.format(
                "Personal Code: %s\n" +
                        "Assigned Missions: %d\n" +
                        "Completed Missions: %d\n" +
                        "Rating: %.2f",
                this.getId(),
                assignedMissions.size(),
                comletedmissions.size(),
                this.getRating());
    }
}

