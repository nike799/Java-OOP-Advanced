package onehitdungeon.io;

public class OutputWriter implements onehitdungeon.interfaces.OutputWriter {
    @Override
    public void print(String output) {
        System.out.print(output);
    }

    @Override
    public void println(String output) {
        System.out.println(output);
    }
}
