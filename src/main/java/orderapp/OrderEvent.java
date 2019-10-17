package orderapp;

public class OrderEvent extends AbstractEvent<OrderEventType> {

    private String trackId;
    private String diagnostic;

    public OrderEvent(OrderEventType eventType) {
        super(eventType);
    }
    public OrderEvent(OrderEventType eventType,String trackId,String diagnostic) {
        super(eventType);
        this.trackId = trackId;
        this.diagnostic = diagnostic;
    }

    public OrderEvent(OrderEventType eventType,String trackId) {
        super(eventType);
        this.trackId = trackId;
        this.diagnostic = "";
    }


    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }
}
