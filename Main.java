import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Medicine> medicines = new PersistenceManager().load();
        ReminderScheduler scheduler = new ReminderScheduler();

        // Schedule reminders for loaded medicines
        for (Medicine m : medicines) {
            scheduler.scheduleReminder(m);
        }

        while (true) {
            System.out.println("\n=== Smart Medicine Reminder ===");
            System.out.println("1. Add Medicine");
            System.out.println("2. View Medicines");
            System.out.println("3. Delete Medicine");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter medicine name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter time (HH:mm): ");
                    String timeStr = scanner.nextLine();
                    LocalTime time = LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm"));
                    Medicine med = new Medicine(name, time);
                    medicines.add(med);
                    scheduler.scheduleReminder(med);
                    new PersistenceManager().save(medicines);
                    System.out.println("Medicine added and reminder scheduled.");
                    break;

                case 2:
                    System.out.println("Your Medicines:");
                    for (int i = 0; i < medicines.size(); i++) {
                        System.out.println((i + 1) + ". " + medicines.get(i));
                    }
                    break;

                case 3:
                    System.out.print("Enter medicine number to delete: ");
                    int index = scanner.nextInt();
                    if (index > 0 && index <= medicines.size()) {
                        medicines.remove(index - 1);
                        new PersistenceManager().save(medicines);
                        System.out.println("Deleted.");
                    } else {
                        System.out.println("Invalid index.");
                    }
                    break;

                case 4:
                    System.out.println("Goodbye! Stay healthy.");
                    System.exit(0);
            }
        }
    }
}
