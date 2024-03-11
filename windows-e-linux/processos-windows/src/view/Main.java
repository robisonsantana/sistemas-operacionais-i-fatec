package view;

import javax.swing.JOptionPane;
import controller.KillController;
import controller.RedesController;

public class Main {

	//Main Class com menu principal:
	public static void main(String[] args) {
		
		RedesController redes = new RedesController();
		KillController kill = new KillController();
		
		int controller = 0;
		while(controller != 9) {
			
			controller = Integer.parseInt(JOptionPane.showInputDialog("MENU DE OPÇÕES\n\n"
																	+ "1- Redes\n"
																	+ "2- Kill\n"
																	+ "9- Finalizar"));
			
			switch (controller) {
			case 1:
				int opc = 0;
				while(opc != 9) {
					opc = Integer.parseInt(JOptionPane.showInputDialog("MENU REDES\n\n"
							+ "1- IP\n"
							+ "2- Ping\n"
							+ "9- Voltar"));
					switch (opc) {
					case 1:
						redes.ip();
						break;
					case 2:
						redes.ping();
						break;
					}
				}
				break;
				
			case 2:
				int option = 0;
				while(option != 9) {
					option = Integer.parseInt(JOptionPane.showInputDialog("MENU KILL\n\n"
																		+ "1- Listar Processos\n"
																		+ "2- Matar processo PID\n"
																		+ "3- Matar processo Nome\n"
																		+ "9- Voltar"));
					switch(option) {
					case 1:
						kill.listProcess();
						break;
					case 2:
						String pid = JOptionPane.showInputDialog("Digite um PID:");
						kill.killPid(pid);
						break;
					case 3:
						String name = JOptionPane.showInputDialog(null, "Digite o nome do processo:");
						kill.killName(name);
						break;
					}
				}
				break;
			    
			case 9:
				JOptionPane.showMessageDialog(null, "Programa Finalizado!");
				break;
				
			default: JOptionPane.showMessageDialog(null, "Número Inválido!");
			}
		}

	}

}
