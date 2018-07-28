package panzer.manager;

import panzer.contracts.Manager;
import panzer.contracts.Part;
import panzer.contracts.Vehicle;
import panzer.core.PanzerBattleOperator;
import panzer.factories.PartFactory;
import panzer.factories.VehicleFactory;
import panzer.utilities.Constants;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProgramManager implements Manager {
    VehicleFactory vehicleFactory = new VehicleFactory();
    PartFactory partFactory = new PartFactory();
    Map<String, Vehicle> vehicles = new LinkedHashMap<>();
    Map<String, Vehicle> defeatedVehicles = new LinkedHashMap<>();
    PanzerBattleOperator panzerBattleOperator = new PanzerBattleOperator();


    @Override
    public String addVehicle(List<String> arguments) {
        Vehicle vehicle = vehicleFactory.createVehicle(arguments);
        this.vehicles.putIfAbsent(arguments.get(2), vehicle);
        return String.format("Created %s Vehicle - %s",
                vehicle.getClass().getSimpleName(), vehicle.getModel());
    }

    @Override
    public String addPart(List<String> arguments) {
        Part part = partFactory.createPart(arguments);
        Vehicle vehicle = null;
        if (this.vehicles.containsKey(arguments.get(1))) {
            vehicle = vehicles.get(arguments.get(1));

            switch (arguments.get(2)) {
                case Constants.PART_ARSENAL:
                    vehicle.addArsenalPart(part);
                    break;
                case Constants.PART_SHELL:
                    vehicle.addShellPart(part);
                    break;
                case Constants.PART_ENDURANCE:
                    vehicle.addEndurancePart(part);
                    break;
            }
        }
        return String.format("Added %s - %s to Vehicle - %s",
                arguments.get(2), arguments.get(3), arguments.get(1));
    }

    @Override
    public String inspect(List<String> arguments) {
        StringBuilder sb = new StringBuilder();
        if (this.vehicles.containsKey(arguments.get(1))) {
            sb.append(vehicles.get(arguments.get(1)).toString());
        }
        return sb.toString();
    }

    @Override
    public String battle(List<String> arguments) {

        Vehicle attacker = vehicles.get(arguments.get(1));
        Vehicle target = vehicles.get(arguments.get(2));
        String winner = panzerBattleOperator.battle(attacker, target);
        if (attacker.getModel().equals(winner)) {
            this.vehicles.remove(target.getModel());
            this.defeatedVehicles.putIfAbsent(target.getModel(), target);
        } else {
            this.vehicles.remove(attacker.getModel());
            this.defeatedVehicles.putIfAbsent(attacker.getModel(), attacker);
        }
        return String.format("%s versus %s -> %s Wins! Flawless Victory!",
                attacker.getModel(), target.getModel(), winner);
    }

    @Override
    public String terminate(List<String> arguments) {
        StringBuilder sb = new StringBuilder();
        String remainingVehicles = "None";
        String defeatedVehicles = "None";
        if (this.vehicles.size() > 0) {
            remainingVehicles = this.vehicles.entrySet().stream()
                    .map(Map.Entry::getKey).collect(Collectors.joining(", "));
        }
        if (this.defeatedVehicles.size() > 0) {
            defeatedVehicles = this.defeatedVehicles.entrySet().stream()
                    .map(Map.Entry::getKey).collect(Collectors.joining(", "));
        }
        int partsCount = 0;
        for (Map.Entry<String, Vehicle> entry : this.vehicles.entrySet()) {
            for (Part part : entry.getValue().getParts()) {
                partsCount++;
            }
        }
        return String.format("Remaining Vehicles: %s\n" +
                        "Defeated Vehicles: %s\n" +
                        "Currently Used Parts: %d",
                remainingVehicles,
                defeatedVehicles,
                partsCount);
    }
}
