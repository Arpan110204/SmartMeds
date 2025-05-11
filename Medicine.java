import java.io.Serializable;
import java.time.LocalTime;

public class Medicine implements Serializable {
    private String name;
    private LocalTime time;

    public Medicine(String name, LocalTime time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return name + " at " + time;
    }
}
