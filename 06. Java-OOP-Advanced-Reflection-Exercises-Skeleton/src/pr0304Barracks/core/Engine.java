package pr0304Barracks.core;

import pr0304Barracks.contracts.Executable;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.Runnable;
import pr0304Barracks.contracts.UnitFactory;
import pr0304Barracks.core.annotations.Inject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {

    private static final String OUTPUT_DATA = "pr0304Barracks.core.commands.";
    private static final String COMMAND_SUFIX = "Command";

    private String[] data;
    private Repository repository;
    private UnitFactory unitFactory;

    public Engine(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            try {
                String input = reader.readLine();
                String[] data = input.split("\\s+");
                String commandName = data[0];
                String result = interpredCommand(data, commandName);
                if (result.equals("fight")) {
                    break;
                }
                System.out.println(result);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private String interpredCommand(String[] data, String commandName) {
        this.data = data;
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toUpperCase(commandName.charAt(0)))
                .append(commandName.substring(1)).append(COMMAND_SUFIX);

        Executable command = null;
        try {
            Class<?> commandClass = Class.forName(OUTPUT_DATA + sb.toString());
            Constructor<?> declaredConstructor = commandClass.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            command = (Executable) declaredConstructor.newInstance();
            declaredConstructor.setAccessible(false);
            injectDepencencies(command);
        } catch (ClassNotFoundException | NoSuchMethodException |
                IllegalAccessException | InvocationTargetException | InstantiationException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return command.execute();
    }

   private <T> void injectDepencencies(T command) throws IllegalAccessException {
       Field[] commandFields = command.getClass().getDeclaredFields();
       Field[] engineFields = this.getClass().getDeclaredFields();
       for (Field commandField : commandFields) {
           commandField.setAccessible(true);
           if (commandField.isAnnotationPresent(Inject.class)) {
               for (Field engineField : engineFields) {
                   engineField.setAccessible(true);
                   if (commandField.getType().equals(engineField.getType()) &&
                           commandField.getName().equals(engineField.getName())) {
                       commandField.set(command, engineField.get(this));
                   }
               }
           }
       }
   }
}
