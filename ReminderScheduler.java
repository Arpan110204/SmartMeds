import java.util.*;
import java.time.*;
import java.util.Timer;
import java.util.TimerTask;

public class ReminderScheduler {
    public void scheduleReminder(Medicine medicine) {
        long delay = computeDelay(medicine.getTime());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("🔔 Reminder: Take your medicine - " + medicine.getName());
            }
        }, delay);
    }

    private long computeDelay(LocalTime time) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime reminderTime = now.withHour(time.getHour()).withMinute(time.getMinute()).withSecond(0);
        if (reminderTime.isBefore(now)) {
            reminderTime = reminderTime.plusDays(1);  // Schedule for next day
        }
        return Duration.between(now, reminderTime).toMillis();
    }
}
