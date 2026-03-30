import java.time.LocalDateTime;
import java.util.UUID;

public abstract class ElevatorRequest {
    private final String requestId;
    private final RequestType requestType;
    private final int sourceFloor;
    private final LocalDateTime timestamp;

    public ElevatorRequest(RequestType requestType, int sourceFloor) {
        this.requestId = UUID.randomUUID().toString();
        this.requestType = requestType;
        this.sourceFloor = sourceFloor;
        this.timestamp = LocalDateTime.now();
    }

    public String getRequestId() {
        return requestId;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}