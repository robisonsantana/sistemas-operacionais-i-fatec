package controller;

public class FatThread extends Thread {
	
	private int numero;
	private int id;
	
	public FatThread(int numero, int id) {
		this.numero = numero;
		this.id = id;
	}

	@Override
	public void run() {
		if(id == 0) {
			long tempoInicial = System.nanoTime();
			System.out.println("Fatorial com recursiva: " + fatorialRec(numero));
			long tempoFinal = System.nanoTime();
			double tempoTotal = (tempoFinal - tempoInicial) / (1 * Math.pow(10, 9));
			System.out.println("Tempo com recursiva: " + tempoTotal);
		} else {
			long tempoInicial = System.nanoTime();
			System.out.println("Fatorial sem recursiva: " + fatorialCalc(numero));
			long tempoFinal = System.nanoTime();
			double tempoTotal = (tempoFinal - tempoInicial) / (1 * Math.pow(10, 9));
			System.out.println("Tempo sem recursiva: " + tempoTotal);
		}
	}
	
	private long fatorialRec(int numero) {
		
		if(numero == 1) {
			return 1;
		} else {
			return numero * fatorialRec(numero - 1);
		}
	}
	
	private long fatorialCalc(int numero) {
		long resultado = 1;
		while(numero != 1) {
			resultado *= numero;
			numero--;
		}
		return resultado;
	}

	
	
	

}
