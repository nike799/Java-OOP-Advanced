package pr0304Barracks.core.commands;

import pr0304Barracks.contracts.Executable;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.core.annotations.Inject;

public class RetireCommand implements Executable {
    @Inject
    private String[] data;
    @Inject
    private Repository repository;


    protected String[] getData() {
        return data;
    }

    protected Repository getRepository() {
        return repository;
    }

    @Override
    public String execute() {
        String unitType = this.getData()[1];
        try {
            this.getRepository().removeUnit(unitType);
        }catch (IllegalArgumentException ex){
            return ex.getMessage();
        }
        return unitType + " retired!";
    }
}
