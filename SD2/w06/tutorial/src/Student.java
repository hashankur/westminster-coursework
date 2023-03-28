import java.util.ArrayList;

public class Student {
    private String name;
    private String ID;
    private ArrayList<Module> modules;

    public Student(String name, String ID, Module modules) {
        this.name = name;
        this.ID = ID;
        this.modules = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void addModule(ArrayList<Module> m) {
        this.modules = m;
    }
}
