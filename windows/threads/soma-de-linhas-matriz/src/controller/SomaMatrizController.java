package controller;

public class SomaMatrizController extends Thread {

	private int[][] matriz;
	private int linha;
	
	//Construtor
	public SomaMatrizController(int[][] matriz, int linha) {
		this.matriz = matriz;
		this.linha = linha;
	}
	
	@Override
	public void run() {
		soma(matriz, linha);
	}
	
	//Método para somar cada linha da matriz:
	private void soma(int[][] matriz, int linha) {
		int soma = 0;
		for(int valor : matriz[linha]) {
			soma += valor;
		}
		System.out.println("Soma da linha " + linha + " = " + soma);
	}

}
