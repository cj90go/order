package state;

public interface StateTransitionListener
    <OPERAND, EVENT, STATE extends Enum<STATE>> {


  void preTransition(OPERAND op, STATE beforeState, EVENT eventToBeProcessed);


  void postTransition(OPERAND op, STATE beforeState, STATE afterState,
                      EVENT processedEvent);
}
