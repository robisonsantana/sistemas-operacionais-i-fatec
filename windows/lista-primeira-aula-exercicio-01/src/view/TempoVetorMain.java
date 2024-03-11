package view;

import controller.TempoVetorController;
import javax.swing.JOptionPane;

public class TempoVetorMain {
	
	/*  1. Fazer uma aplicação Java em Eclipse que tenha uma operação que receba
	 *  um vetor de 1000 posições inteiras (Preencher todas as posições com valor 0)
	 *  e gere o tempo gasto, em Segundos, para percorrer o vetor. Repetir para
	 *  10000 e 100000 posições. A aplicação deve ter uma classe de controle com
	 *  métodos para operações e uma classe de visão que instancie a classe de
	 *  controle para a comunicação.*/
	public static void main(String[] args) {
		
		TempoVetorController tvc = new TempoVetorController();
		int tamanho = 0;
		int opcao = 0;
		while(opcao != 9) {
			
			//Menu de opções de tamanho para o vetor:
			opcao = Integer.parseInt(JOptionPane.showInputDialog(" Escolha o tamanho do vetor:"
															+ "\n 1) 1 000"
															+ "\n 2) 10 000"
															+ "\n 3) 100 000"
															+ "\n 9) Finalizar Programa"));
			
			switch(opcao) {
				
			case(1): tamanho = 1000;
			tvc.tempoGasto(tamanho);
			break;
			
			case(2): tamanho = 10000;
			tvc.tempoGasto(tamanho);
			break;
			
			case(3): tamanho = 100000;
			tvc.tempoGasto(tamanho);
			break;
			
			case(9): JOptionPane.showMessageDialog(null, "Programa Finalizado!");
			break;
			
			default: JOptionPane.showMessageDialog(null, "Comando Inválido!");
			
			}
			
		}
		
	}

}
