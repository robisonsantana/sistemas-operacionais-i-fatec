package view;

import javax.swing.JOptionPane;
import controller.SteamController;

public class Main {
	
	/* 2) O Arquivo SteamCharts.csv contém informações sobre popularidade dos jogos na plataforma Steam, estão 
	 * contempladas as informações e divididas por ano e por mês. São mais de 83 mil registros de jogos. São 
	 * interessantes para a nossa análise o nome do jogo, o ano, o mês e a média de jogadores ativos (avg). 
	 * Fazer uma solução em java que tenha, na classe de controle (SteamController), um método que receba como 
	 * parâmetros o ano, o mês e um valor esperado para a média e exiba no console, no seguinte formato: (Nome 
	 * do jogo | Média de jogadores ativos) desde que o ano e o mês estejam de acordo com os parâmetros e a 
	 * média de jogadores ativos seja maior ou igual ao parâmetro passado. A classe de controle deve ter um 
	 * outro método que receba o ano, o mês, um caminho de diretório válido e um nome de arquivo válido. O 
	 * método deve filtrar as linhas de acordo com o mês e o ano inseridos pelo usuário e deve gerar um 
	 * arquivo (nome.csv), no diretório válido (A existência do diretório deve ser validada), com o seguinte formato: 
	 * 
	 * nome do jogo ; média dos jogadores ativos
	 * 
	 * Uma classe de visão (Principal.java) deve ser criada com um método Main que permita realizar as operações 
	 * */
	public static void main(String[] args) throws Exception {
		
		SteamController controller = new SteamController();
		
		int control = 0;
		while(control != 9) {
			control = Integer.parseInt(JOptionPane.showInputDialog("Menu \n\n"
																	+ "1- Pesquisar \n"
																	+ "2- Criar csv"));
			switch(control) {
			case 1:
				String dados = JOptionPane.showInputDialog(null, "Digite o mês, ano e média de jogadores ativos que deseja filtrar \n(Separe-os por virgula)", "Entrada de dados", JOptionPane.INFORMATION_MESSAGE);
				String[] separar = dados.split(", ");
				String mes = separar[0];
				String ano = separar[1];
				int media = Integer.parseInt(separar[2]);
				System.out.println("Jogos com média de jogadores ativos >= " + media + " em " + mes + " de " + ano + ":");
				controller.filtrarJogos(ano, mes, media);
				break;
			case 2:
				dados = JOptionPane.showInputDialog(null, "Digite o mês, ano e o nome do arquivo que deseja ser criado \n(Separe-os por virgula)", "Entrada de dados", JOptionPane.INFORMATION_MESSAGE);
				separar = dados.split(", ");
				mes = separar[0];
				ano = separar[1];
				String nome = separar[2];
				System.out.println("Gerando arquivo filtrado para " + mes + " de " + ano + "...");
				controller.gerarArquivoFiltrado(ano, mes, "C:\\TEMP", nome + ".csv");
				System.out.println("Arquivo gerado com sucesso.");
				break;
			case 9:
				JOptionPane.showMessageDialog(null, "Programa finalizado.");
				break;
			default: JOptionPane.showMessageDialog(null, "Comando inválido.");
			}
		}
	}

}
