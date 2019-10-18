package orderapp;

import orderapp.state.OrderState;

public interface OrderApp extends EventHandler<OrderEvent>{
    public OrderState getState();
}
