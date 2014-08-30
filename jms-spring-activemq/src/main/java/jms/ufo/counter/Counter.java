package jms.ufo.counter;

public class Counter {
	
	private static int COUNT = 0;
	
	public static synchronized int increase() {
		return ++COUNT;
	}
	
	public static synchronized int count() {
		return COUNT;
	}
}
