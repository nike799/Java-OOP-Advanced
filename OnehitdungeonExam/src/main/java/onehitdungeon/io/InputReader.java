package onehitdungeon.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader implements onehitdungeon.interfaces.InputReader {
    private BufferedReader bufferedReader;

    public InputReader() {
        this.bufferedReader =new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String readLine() throws IOException {
        return this.bufferedReader.readLine();
    }
}
