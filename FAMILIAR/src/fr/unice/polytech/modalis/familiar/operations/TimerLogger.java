package fr.unice.polytech.modalis.familiar.operations;

public class TimerLogger {

	private long _stop;
	private long _start;

	public void start() {
		_start = System.currentTimeMillis();
	}

	public void stop() {
		_stop = System.currentTimeMillis();
	}

	public long amountOfTime() {
		return _stop - _start;
	}

}
