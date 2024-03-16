package view;

import controller.SomaMatrizController;

public class Main {
	
	/* 2) Fazer uma aplicação que insira números aleatórios em uma matriz 3 x 5 e 
	 * tenha 3 chamadas de Threads, onde cada uma calcula a soma dos valores de 
	 * cada linha, imprimindo a identificação da linha e o resultado da soma. */
	public static void main(String[] args) {
		
		int[][] matriz = new int[3][5];
		
		//Preenchendo matriz
		for(int linha = 0; linha < 3; linha++) {
			System.out.println("");
			for(int coluna = 0; coluna < 5; coluna++) {
				matriz[linha][coluna] = (int) (Math.random() * 100);
				System.out.print(matriz[linha][coluna] + " | ");
			}
		}
		
		System.out.println("\n");
		for(int linha = 0; linha < 3; linha++) {
			SomaMatrizController sm = new SomaMatrizController(matriz, linha);
			sm.start();
		}
	}

}
