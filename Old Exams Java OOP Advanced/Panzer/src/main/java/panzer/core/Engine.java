package panzer.core;

import panzer.io.Reader;
import panzer.io.Writer;
import panzer.manager.ProgramManager;
import panzer.utilities.Constants;
import panzer.utilities.InputParser;

import java.io.IOException;
import java.util.ArrayList;

public class Engine {
    private Reader reader;
    private Writer writer;
    private InputParser inputParser;
    private ProgramManager programManager;

    public Engine() {
        this.reader = new Reader();
        this.writer = new Writer();
        this.inputParser = new InputParser();
        this.programManager = new ProgramManager();
    }

    public void run() throws IOException {
        String input;

        while (true) {
            input = this.reader.readLine();
            ArrayList<String> commandParams = this.inputParser.parseInput(input);
            dispatchCommand(commandParams);
            if (Constants.TERMINATE_COMMAND.equals(input)) {
                break;
            }
        }
    }

    private void dispatchCommand(ArrayList<String> commandParams) {
        String command = commandParams.get(0);

        switch (command) {
            case Constants.VEHICLE_COMMAND:
                writer.println(programManager.addVehicle(commandParams));
                break;
            case Constants.PART_COMMAND:
                writer.println(programManager.addPart(commandParams));
                break;
            case Constants.INSPECT_COMMAND:
                writer.println(programManager.inspect(commandParams));
                break;
            case Constants.BATTLE_COMMAND:
                writer.println(programManager.battle(commandParams));
                break;
            case Constants.TERMINATE_COMMAND:
                writer.println(programManager.terminate(commandParams));
                break;
        }
    }

}
