package callofduty.core;

import callofduty.constants.Config;
import callofduty.interfaces.InputReader;
import callofduty.interfaces.MissionManager;
import callofduty.interfaces.OutputWriter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Engine {
    private InputReader inputReader;
    private OutputWriter outputWriter;
    private MissionManager missionManager;

    public Engine(InputReader inputReader, OutputWriter outputWriter, MissionManager missionManager) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.missionManager = missionManager;
    }

    public void run() {

        while (true) {
            String inputLine = inputReader.readLine();
            List<String> tokens = Arrays.stream(inputLine.split("\\s+")).collect(Collectors.toList());
            commandDispatcher(tokens);

            if (Config.TERMINATE_COMMAND.equals(tokens.get(0))) {
                break;
            }
        }
    }

    private void commandDispatcher(List<String> arguments) {

        String command = arguments.get(0);
        switch (command){
            case Config.AGENT_COMMAND:
                this.outputWriter.println(this.missionManager.agent(arguments));
                break;
            case Config.REQUEST_COMMAND:
                this.outputWriter.println(this.missionManager.request(arguments));
                break;
            case Config.STATUS_COMMAND:
                this.outputWriter.println(this.missionManager.status(arguments));
                break;
            case Config.COMPLETE_COMMAND:
                this.outputWriter.println(this.missionManager.complete(arguments));
                break;
            case Config.TERMINATE_COMMAND:
                this.outputWriter.println(this.missionManager.over(arguments));
                break;
        }

    }
}
