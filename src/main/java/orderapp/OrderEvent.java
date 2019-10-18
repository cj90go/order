package orderapp;

import state.ApplicationId;

public class OrderEvent extends AbstractEvent<OrderEventType>{

    private final ApplicationId appId;
    private final String diagnosticMsg;

    public OrderEvent(ApplicationId appId, OrderEventType type) {
        this(appId, type, "");
    }

    public OrderEvent(ApplicationId appId, OrderEventType type,
                      String diagnostic) {
        super(type);
        this.appId = appId;
        this.diagnosticMsg = diagnostic;
    }

    public OrderEvent(ApplicationId appId, OrderEventType type, long timeStamp) {
        super(type, timeStamp);
        this.appId = appId;
        this.diagnosticMsg = "";
    }

    public ApplicationId getApplicationId() {
        return this.appId;
    }

    public String getDiagnosticMsg() {
        return this.diagnosticMsg;
    }

}
