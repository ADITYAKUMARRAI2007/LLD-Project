public class AnnouncementService implements Announcer {
    @Override
    public void announce(String message, Language language) {
        System.out.println("[ANNOUNCEMENT][" + language + "] " + message);
    }
}