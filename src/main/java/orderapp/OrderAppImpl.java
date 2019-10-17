package orderapp;

import orderapp.state.OrderState;
import state.StateMachine;
import state.StateMachineFactory;

public class OrderAppImpl {
	private StateMachine stateMachine;

	public OrderAppImpl() {
		this.stateMachine = stateMachineFactory.make(this);
	}

	private static final StateMachineFactory<OrderAppImpl, OrderState, OrderEventType, OrderEvent> stateMachineFactory = new StateMachineFactory<OrderAppImpl, OrderState, OrderEventType, OrderEvent>(
	      OrderState.INIT).addTransition(OrderState.Order.CREATE,OrderState.Order.SCHOOLING,OrderEventType.Order.PAY,);

	public static void main(String[] args) {
		System.out.println(OrderState.Order.ALLOCATING.getStatus());
	}

	private class OrderPayEvent extends OrderEvent{


	}

}
