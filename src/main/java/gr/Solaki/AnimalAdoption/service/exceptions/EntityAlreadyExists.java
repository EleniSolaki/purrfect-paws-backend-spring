package gr.Solaki.AnimalAdoption.service.exceptions;

public class EntityAlreadyExists extends Exception{

    private static final long serialVersionUID = 1L;

    public EntityAlreadyExists(Class<?> entityClass) {
        super("Entity " + entityClass.getSimpleName() + " already exist");
    }

    public EntityAlreadyExists(String message){
        super(message);
    }
}
