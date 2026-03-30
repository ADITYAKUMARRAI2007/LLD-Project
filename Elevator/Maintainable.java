public interface Maintainable {
    void enterMaintenance();
    void exitMaintenance();
    boolean isUnderMaintenance();
}