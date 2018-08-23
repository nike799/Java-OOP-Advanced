package callofduty;

import callofduty.core.Engine;
import callofduty.core.MissionManagerImpl;
import callofduty.interfaces.InputReader;
import callofduty.interfaces.MissionManager;
import callofduty.interfaces.OutputWriter;
import callofduty.io.ConsoleInputReader;
import callofduty.io.ConsoleOutputWriter;

public class Main {
    public static void main(String[] args)  {
        InputReader inputReader = new ConsoleInputReader();
        OutputWriter outputWriter = new ConsoleOutputWriter();
        MissionManager missionManager = new MissionManagerImpl();
        Engine engine = new Engine(inputReader,outputWriter,missionManager);
        engine.run();
    }
}




