package controller;

public class TidController extends Thread{
	
	private long tid;
	
	//Construtor
	public TidController(long tid) {
		this.tid = tid;
	}
	
	@Override
	public void run() {
		getTid(tid);
	}
	
	@SuppressWarnings("deprecation")
	private void getTid(long tid) {
		tid = getId();
		System.out.println("TID #" + tid);
	}


}
