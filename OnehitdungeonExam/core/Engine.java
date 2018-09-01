package onehitdungeon.core;

import onehitdungeon.constants.Config;
import onehitdungeon.interfaces.DungeonManager;
import onehitdungeon.interfaces.InputReader;
import onehitdungeon.interfaces.OutputWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Engine {

    private InputReader inputReader;
    private OutputWriter outputWriter;
    private DungeonManager dungeonManager;


    public Engine(InputReader inputReader, OutputWriter outputWriter, DungeonManager dungeonManager) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.dungeonManager = dungeonManager;
    }

    public void run() throws IOException {

        while (true) {
            String inputLine = inputReader.readLine();
            List<String> tokens = Arrays.stream(inputLine.split(Config.SPLIT_PATTERN)).collect(Collectors.toList());
            commandDispatcher(tokens);

            if (Config.QUIT_COMMAND.equals(tokens.get(0))) {
                break;
            }
        }
    }

    private void commandDispatcher(List<String> arguments) {

        String command = arguments.get(0);
        switch (command) {
            case Config.HERO_COMMAND:
                this.outputWriter.println(this.dungeonManager.hero(arguments));
                break;
            case Config.SELECT_COMMAND:
                this.outputWriter.println(this.dungeonManager.select(arguments));
                break;
            case Config.STATUS_COMMAND:
                this.outputWriter.println(this.dungeonManager.status(arguments));
                break;
            case Config.FIGHT_COMMAND:
                this.outputWriter.println(this.dungeonManager.fight(arguments));
                break;
            case Config.ADVANCE_COMMAND:
                this.outputWriter.println(this.dungeonManager.advance(arguments));
                break;
            case Config.TRAIN_COMMAND:
                this.outputWriter.println(this.dungeonManager.train(arguments));
                break;
            case Config.QUIT_COMMAND:
                this.outputWriter.print(this.dungeonManager.quit(arguments));
                break;
        }
    }
}
