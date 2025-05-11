import java.io.*;
import java.util.List;

public class PersistenceManager {
    private static final String FILE_NAME = "medicines.ser";

    public void save(List<Medicine> medicines) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(medicines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Medicine> load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Medicine>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
