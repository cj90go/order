package state;

import java.util.ArrayList;
import java.util.List;

public abstract class MultiStateTransitionListener
    <OPERAND, EVENT, STATE extends Enum<STATE>> implements
    StateTransitionListener<OPERAND, EVENT, STATE> {

  private final List<StateTransitionListener<OPERAND, EVENT, STATE>> listeners =
      new ArrayList<>();

  public void addListener(StateTransitionListener<OPERAND, EVENT, STATE>
      listener) {
    listeners.add(listener);
  }

  @Override
  public void preTransition(OPERAND op, STATE beforeState,
      EVENT eventToBeProcessed) {
    for (StateTransitionListener<OPERAND, EVENT, STATE> listener : listeners) {
      listener.preTransition(op, beforeState, eventToBeProcessed);
    }
  }

  @Override
  public void postTransition(OPERAND op, STATE beforeState, STATE afterState,
      EVENT processedEvent) {
    for (StateTransitionListener<OPERAND, EVENT, STATE> listener : listeners) {
      listener.postTransition(op, beforeState, afterState, processedEvent);
    }
  }
}
