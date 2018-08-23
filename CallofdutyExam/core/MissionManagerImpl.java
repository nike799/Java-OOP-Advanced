package callofduty.core;

import callofduty.domain.agents.MasterAgent;
import callofduty.domain.agents.NoviceAgent;
import callofduty.interfaces.*;
import callofduty.utilities.ReflectField;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class MissionManagerImpl implements MissionManager {
    private MissionControl missionControl;
    private Map<String, Agent> noviceAgents;
    private Map<String, BountyAgent> masterAgents;
    private Map<String, Mission> completedMissions;
    private Map<String, Mission> assignedMissions;
    private int countCompletedMissions;

    public MissionManagerImpl() {
        this.missionControl = new MissionControlImpl();
        this.noviceAgents = new LinkedHashMap<>();
        this.masterAgents = new LinkedHashMap<>();
        this.completedMissions = new LinkedHashMap<>();
        this.assignedMissions = new LinkedHashMap<>();
    }

    @Override
    public String agent(List<String> arguments) {
        String id = arguments.get(1);
        String name = arguments.get(2);
        Agent agent = new NoviceAgent(id, name);
        this.noviceAgents.putIfAbsent(id, agent);
        return String.format("Registered Agent - %s:%s", name, id);
    }

    @Override
    public String request(List<String> arguments) {
        // String missionId, Double missionRating, Double missionBounty
        String missionId = arguments.get(2);
        double missionRating = Double.parseDouble(arguments.get(3));
        double missionBounty = Double.parseDouble(arguments.get(4));
        String agentId = arguments.get(1);
        String agentName = "";
        Mission mission = this.missionControl.generateMission(missionId, missionRating, missionBounty);

        if (this.noviceAgents.containsKey(agentId)) {
            this.noviceAgents.get(agentId).acceptMission(mission);
            agentName = this.noviceAgents.get(agentId).getName();
        } else if (this.masterAgents.containsKey(agentId)) {
            this.masterAgents.get(agentId).acceptMission(mission);
            agentName = this.masterAgents.get(agentId).getName();
        }
        this.assignedMissions.putIfAbsent(missionId, mission);
        int index = mission.getClass().getSimpleName().indexOf("Mission");
        String missionType = mission.getClass().getSimpleName().substring(0, index);
        return String.format("Assigned %s Mission - %s to Agent - %s", missionType, missionId, agentName);
    }

    @Override
    public String complete(List<String> arguments) {
        String agentName = null;
        String agentId = arguments.get(1);
        List<Mission> complMissions = null;
        if (this.noviceAgents.containsKey(agentId)) {
            this.noviceAgents.get(agentId).completeMissions();
            agentName = this.noviceAgents.get(agentId).getName();
            complMissions = ReflectField.getCollectionOfMissions(this.noviceAgents.get(agentId), 4);

            if (complMissions.size() >= 3) {
                BountyAgent masterAgent = new MasterAgent(agentId, agentName);
                ReflectField.setAgentFields(this.noviceAgents.get(agentId), masterAgent);
                this.masterAgents.putIfAbsent(agentId, masterAgent);
                this.noviceAgents.remove(agentId);
            }
        } else if (this.masterAgents.containsKey(agentId)) {
            this.masterAgents.get(agentId).completeMissions();
            agentName = this.masterAgents.get(agentId).getName();
            complMissions = ReflectField.getCollectionOfMissions(this.masterAgents.get(agentId), 4);
        }
        complMissions.forEach(x -> this.completedMissions.put(x.getId(), x));
        complMissions.forEach(x -> this.assignedMissions.remove(x.getId()));
        return String.format("Agent - %s:%s has completed all assigned missions.", agentName, agentId);
    }

    @Override
    public String status(List<String> arguments) {
        String id = arguments.get(1);
        if (this.noviceAgents.containsKey(id)) {
            return this.noviceAgents.get(id).toString();
        }
        if (this.masterAgents.containsKey(id)) {
            return this.masterAgents.get(id).toString();
        }

        if (this.assignedMissions.containsKey(id)) {
            int index = this.assignedMissions.get(id).getClass().getSimpleName().indexOf("Mission");
            String missionType = this.assignedMissions.get(id).getClass().getSimpleName().substring(0, index);
            return String.format(
                    "%s Mission - %s\n" +
                            "Status: Open\n" +
                            "Rating: %.2f\n" +
                            "Bounty: %.2f",
                    missionType,
                    this.assignedMissions.get(id).getId(),
                    this.assignedMissions.get(id).getRating(),
                    this.assignedMissions.get(id).getBounty());
        }
        if (this.completedMissions.containsKey(id)) {
            int index = this.completedMissions.get(id).getClass().getSimpleName().indexOf("Mission");
            String missionType = this.completedMissions.get(id).getClass().getSimpleName().substring(0, index);
            return String.format(
                    "%s Mission - %s\n" +
                            "Status: Completed\n" +
                            "Rating: %.2f\n" +
                            "Bounty: %.2f",
                    missionType,
                    this.completedMissions.get(id).getId(),
                    this.completedMissions.get(id).getRating(),
                    this.completedMissions.get(id).getBounty());
        }
        return null;
    }

    @Override
    public String over(List<String> arguments) {
        int noviceAgentsCount = this.noviceAgents.size();
        int masterAgentsCount = this.masterAgents.size();
        int totalAssignedMissionsCount = this.assignedMissions.size() + this.completedMissions.size();
        int totalCompletedMissionsCount = this.completedMissions.size();
        double totalRatingEarned = this.noviceAgents.values().stream().mapToDouble(Rateable::getRating).sum() +
                this.masterAgents.values().stream().mapToDouble(Rateable::getRating).sum();
        double totalBountyEarned = this.masterAgents.values().stream().mapToDouble(Bountyable::getBounty).sum();
        return String.format(
                "Novice Agents: %d\n" +
                        "Master Agents: %d\n" +
                        "Assigned Missions: %d\n" +
                        "Completed Missions: %d\n" +
                        "Total Rating Given: %.2f\n" +
                        "Total Bounty Given: $%.2f",
                noviceAgentsCount,
                masterAgentsCount,
                totalAssignedMissionsCount,
                totalCompletedMissionsCount,
                totalRatingEarned,
                totalBountyEarned);
    }
}
