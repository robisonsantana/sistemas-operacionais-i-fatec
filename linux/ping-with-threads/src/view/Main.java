package view;

import controller.PingThreadsController;

public class Main {

	/* 5) No Sistema Operacional Linux, o comando para realizar uma operação de ping com 10 iterações 
	 * é ping -4 -c 10 <servidor>. Fazer uma aplicação Java que rode 3 Thread em S.O. Linux fazendo 
	 * operação de ping para os servidores UOL (www.uol.com.br), Terra (www.terra.com.br) e 
	 * Google (www.google.com.br). Cada thread deve ler a saída do ping imprimindo, a cada iteração, 
	 * o nome do servidor (usar fixo: UOL, Terra ou Google) e o tempo daquela iteração. Ao fim, deve-se 
	 * exibir o nome do servidor (usar fixo: UOL, Terra ou Google) e o tempo médio obtido pela operação. 
	 * Outros Sistemas Operacionais devem ser descartados. */
	public static void main(String[] args) {
		
		System.out.println("Aguarde...");
		
		PingThreadsController uolThread = new PingThreadsController("www.uol.com.br");
		PingThreadsController googleThread = new PingThreadsController("www.google.com.br");
		PingThreadsController terraThread = new PingThreadsController("www.terra.com.br");
		
		uolThread.start();
		googleThread.start();
		terraThread.start();

	}

}
