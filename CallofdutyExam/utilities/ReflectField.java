package callofduty.utilities;


import callofduty.domain.agents.BaseAgent;
import callofduty.interfaces.Agent;
import callofduty.interfaces.Mission;

import java.lang.reflect.Field;
import java.util.List;

public final class ReflectField {

    public static List<Mission> getCollectionOfMissions(Agent agent, int fieldIndex) {
        List<Mission> comletedmissions = null;
        Class classBaseAgent = BaseAgent.class;
        Field field = classBaseAgent.getDeclaredFields()[fieldIndex];
        field.setAccessible(true);
        try {
            comletedmissions = (List<Mission>) field.get(agent);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        field.setAccessible(false);
        return comletedmissions;
    }

    public static void setAgentFields(Agent novice, Agent master) {

        Class classBaseAgent = BaseAgent.class;
        Field rating = classBaseAgent.getDeclaredFields()[2];
        Field missions = classBaseAgent.getDeclaredFields()[4];
        missions.setAccessible(true);
        rating.setAccessible(true);
        try {
            missions.set(master, getCollectionOfMissions(novice, 4));
            rating.set(master, novice.getRating());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        missions.setAccessible(false);
        rating.setAccessible(false);
    }

}
