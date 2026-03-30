import java.util.List;

public class Dispatcher {
	private DispatchStrategy dispatchStrategy;

	public Dispatcher() {
		this.dispatchStrategy = new FCFSDispatchStrategy();
	}

	public Dispatcher(DispatchStrategy dispatchStrategy) {
		this.dispatchStrategy = dispatchStrategy;
	}

	public ElevatorCar dispatch(List<ElevatorCar> elevators, ExternalRequest request) {
		if (dispatchStrategy == null || elevators == null || request == null) {
			return null;
		}
		return dispatchStrategy.chooseElevator(elevators, request);
	}

	public void setDispatchStrategy(DispatchStrategy dispatchStrategy) {
		this.dispatchStrategy = dispatchStrategy;
	}

	public DispatchStrategy getDispatchStrategy() {
		return dispatchStrategy;
	}
}
