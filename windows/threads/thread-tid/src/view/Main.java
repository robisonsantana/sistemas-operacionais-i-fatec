package view;

import controller.TidController;

public class Main {

	/* 1) Fazer uma aplicação que rode 5 Threads que cada uma delas 
	 * imprima no console o seu número (TID). */
	public static void main(String[] args) {
		
		
		for(int i = 0; i < 5; i++) {
			TidController tid = new TidController(0);
			tid.start();
		}

	}

}
