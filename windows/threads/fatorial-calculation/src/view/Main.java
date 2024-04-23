package view;

import controller.FatThread;

public class Main {

	/* 4) Considere que o cálculo de um número fatorial (usando varáveis tipo long) podem ser 
	 * calculados, em Java, de maneira recursiva ou não. Faça duas threads (Uma que calcule 
	 * Fatorial de maneira recursiva e outra não recursiva), onde cada Thread vai receber o 
	 * número que se quer calcular o fatorial, vai exibir o valor do fatorial do número e o 
	 * tempo que cada thread leva para realizar a operação. Considere usar o System.nanoTime() 
	 * ao invés do System.currentTimeMillis(), o resultado sairá em nano segundos. */
	public static void main(String[] args) {
		
		int numero = 5;
		
		for(int id = 0; id < 2; id++) {
			FatThread ft = new FatThread(numero, id);
			ft.start();
		}

	}

}
