package view;

import javax.swing.JOptionPane;

import controller.DistoController;

public class Main {

	public static void main(String[] args) {
		
		DistoController dc = new DistoController();
		
		int controller = 0;
		while(controller != 9) {
			
			controller = Integer.parseInt(JOptionPane.showInputDialog("MENU PRINCIPAL\n\n"
																	+ "1- Mostrar distribuição"));
			switch(controller) {
			case 1:
				dc.exibeDistro();
				break;
			case 9:
				JOptionPane.showMessageDialog(null, "Programa Finalizado.");
				break;
			default: JOptionPane.showMessageDialog(null, "Comando Inválido!");
			}
		}
	}

}
