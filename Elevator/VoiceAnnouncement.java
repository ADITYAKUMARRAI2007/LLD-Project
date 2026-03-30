public class VoiceAnnouncement {
    private final Announcer announcer;

    public VoiceAnnouncement(Announcer announcer) {
        this.announcer = announcer;
    }

    public void speak(String message, Language language) {
        announcer.announce(message, language);
    }
}