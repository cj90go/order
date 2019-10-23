package orderapp;

import orderapp.state.OrderState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import state.*;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class OrderAppImpl implements OrderApp {
    private static final Logger LOG = LoggerFactory.getLogger(OrderAppImpl.class);
    private StateMachine<OrderState, OrderEventType, OrderEvent> stateMachine;
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final ReentrantReadWriteLock.WriteLock writeLock;
    private final ReentrantReadWriteLock.ReadLock readLock;
    private static final String STATE_CHANGE_MESSAGE = "%s State change from %s to %s on event = %s";
    private static final String RECOVERY_MESSAGE = "Recovering app: %s with %d attempts and final state = %s";

    public OrderAppImpl() {
        this.stateMachine = stateMachineFactory.make(this);
        OrderState state = this.stateMachine.getCurrentState();
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();
    }

    /**
     * 定义状态内部流转
     */
    private static final StateMachineFactory<OrderAppImpl, OrderState, OrderEventType, OrderEvent> stateMachineFactory =
        new StateMachineFactory<OrderAppImpl, OrderState, OrderEventType, OrderEvent>(OrderState.INIT)
            .addTransition(OrderState.INIT, OrderState.ALLOCATING, OrderEventType.PAY, new OrderCreate());

    public static void main(String[] args) {
        System.out.println(OrderState.ALLOCATING.getStatus());

        ApplicationId applicationId = ApplicationId.newInstance(System.currentTimeMillis(), 1);
        OrderApp app = new OrderAppImpl();

        OrderEvent createEvent = new OrderEvent(applicationId, OrderEventType.PAY, "");
        app.handle(createEvent);
    }

    public void handle(OrderEvent event) {
        this.writeLock.lock();

        try {
            ApplicationId appID = event.getApplicationId();
            LOG.debug("Processing event for " + appID + " of type " + event.getType());
            final OrderState oldState = getState();
            try {
                this.stateMachine.doTransition(event.getType(), event);
            } catch (InvalidStateTransitionException e) {
                LOG.error("App: " + appID + " can't handle this event at current state value={}", oldState.name(), e);
                onInvalidStateTransition(event, oldState);
            }
            // 记录状态转换日志
            if ((oldState != getState())) {
                LOG.info(String.format(STATE_CHANGE_MESSAGE, appID, oldState, getState(), event.getType()));
            }
        } finally {
            this.writeLock.unlock();
        }
    }

    protected void onInvalidStateTransition(OrderEvent event, OrderState state) {

    }

    private final static class OrderCreate extends OrderTransaction {
        @Override
        public void transition(OrderAppImpl orderApp, OrderEvent event) {
            System.out.println("OrderCreate transaction...");
        }
    }

    private static class OrderTransaction implements SingleArcTransition<OrderAppImpl, OrderEvent> {
        @Override
        public void transition(OrderAppImpl orderApp, OrderEvent event) {

        }
    }

    public OrderState getState() {
        this.readLock.lock();
        try {
            return this.stateMachine.getCurrentState();
        } finally {
            this.readLock.unlock();
        }
    }

}