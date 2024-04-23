package controller;

import voltaic.guy.Fila;
import model.Jogador;

public class Cassino extends Thread{

	private int count = 0;
	private int premio = 5000;
	private Fila<Jogador> filaJogadores;
	
	public Cassino(Fila filaJogadores) {
		this.filaJogadores = filaJogadores;
	}

	@Override
	public void run() {
		cadastraJogadores();
		partida();
	}
	
	//Cadastro de jogadores
	private void cadastraJogadores() {
		for(int i = 0; i < 10; i++) {
			int id = i + 1;
			Jogador jogador = new Jogador(id, 0);
			filaJogadores.insert(jogador);
		}
	}
	
	//Controle da partida
	private void partida() {
		while(count < 3) {
			
			int dado1 = (int)((Math.random() * 6) + 1);
			int dado2 = (int)((Math.random() * 6) + 1);
			int soma = dado1 + dado2;
			if(soma == 11) {
				try {
					Jogador jogador = filaJogadores.remove();
					jogador.pontos++;
					System.out.println("Jogador #" + jogador.id + " fez 1 ponto! \n"
							+ "dado1 = " + dado1 + " & dado2 = " + dado2 + " total = " + soma);
					if(jogador.pontos == 1) {
						count++;
						System.out.println(count + "º lugar: jogador #" + jogador.id + "; pontos = " 
										 + jogador.pontos + "; Prêmio = R$" + premio + ",00");
						premio -= 1000;
					}
					filaJogadores.insert(jogador);
					sleep((int) ((Math.random() * 1001) + 500));
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			} else {
				try {
					Jogador jogador = filaJogadores.remove();
					System.out.println("Jogador #" + jogador.id + " não fez ponto! \n"
							+ "dado1 = " + dado1 + " & dado2 = " + dado2 + " total = " + soma);
					filaJogadores.insert(jogador);
					sleep((int) ((Math.random() * 1001) + 500));
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}
	
}
