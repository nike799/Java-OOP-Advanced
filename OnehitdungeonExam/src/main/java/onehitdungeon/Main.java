package onehitdungeon;

import onehitdungeon.core.Engine;
import onehitdungeon.interfaces.DungeonManager;
import onehitdungeon.interfaces.InputReader;
import onehitdungeon.interfaces.OutputWriter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        InputReader inputReader = new onehitdungeon.io.InputReader();
        OutputWriter outputWriter = new onehitdungeon.io.OutputWriter();
        DungeonManager dungeonManager =new onehitdungeon.managers.DungeonManager();
        Engine engine = new Engine(inputReader,outputWriter,dungeonManager);
        engine.run();
    }
}
