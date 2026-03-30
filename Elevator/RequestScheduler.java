import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RequestScheduler {
    private final Queue<ElevatorRequest> requests = new ConcurrentLinkedQueue<>();

    public void submit(ElevatorRequest request) {
        requests.offer(request);
    }

    public ElevatorRequest poll() {
        return requests.poll();
    }

    public boolean hasRequests() {
        return !requests.isEmpty();
    }
}