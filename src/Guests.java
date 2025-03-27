import java.util.ArrayList;

public class Guests {
    protected int id;
    protected ArrayList<String> ownedAttributes;
    protected ArrayList<String> desiredAttributes;

    public Guests(int id, ArrayList<String> ownedAttributes, ArrayList<String> desiredAttributes) {
        this.id = id;
        this.ownedAttributes = ownedAttributes;
        this.desiredAttributes = desiredAttributes;
    }

    @Override
    public String toString() {
        return "Participant: " + "id = " + id +", ownedAttributes = " + ownedAttributes +
                ", desiredAttributes = " + desiredAttributes +';';
    }
}
