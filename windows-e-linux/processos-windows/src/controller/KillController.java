package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

/* Fazer, em java, uma aplicação que liste os processos ativos, permita ao usuário 
 * entrar com o nome ou o PID do processo e o mate.
 * A aplicação deverá funcionar, minimamente em Windows e Linux (Alunos com Mac podem 
 * fazer para os 3 SO).
 * 
 * É notório que cada SO tem comandos diferentes para as ações supracitadas, portanto:
 * Criar em Eclipse, um novo Java Project com uma classe chamada KillController.java no 
 * package controller e uma classe Main.java no package view. */
public class KillController {

	public KillController() {
		super();
	}
	
	/* 1) O primeiro, chamado os, que identifica e retorna o nome do Sistema 
	 * Operacional (Fazê-lo privado). */
	private String os() {
		if(System.getProperty("os.name").contains("Windows")) {
			return "Windows";
		} else if(System.getProperty("os.name").contains("Linux")) {
			return "Linux";
		} else {
			return "Outro";
		}
	}
	
	/* 2) O segundo, chamado listaProcessos, que verifica o SO e, de acordo 
	 * com SO, selecione o comando para listar os processos ativos.
	 * O método deve receber todas as linhas de saída do processo de listagem 
	 * e exibi-las em console. */
	public void listProcess() {
		String sistemaOperacional = os();
		String comando;
		if(sistemaOperacional.equals("Windows")) {
			comando = "TASKLIST /FO TABLE";
			readProcessList(comando);
		} else if(sistemaOperacional.equals("Linux")) {
			comando = "ps -ef";
			readProcessList(comando);
		} else {
			System.out.println("Sistema operacional não suportado!");
		}
	}
	
	/* 3) O terceiro, chamado mataPid, que recebe um PID como parâmetro de entrada, 
	 * verifica o SO e, de acordo com SO, selecione o comando para matar o processo 
	 * e o finalize. */
	public void killPid(String pid) {
		String sistemaOperacional = os();
		String comando;
		if(sistemaOperacional.equals("Windows")) {
			comando = "TASKKILL /PID";
			killProcess(comando, pid);
		} else if(sistemaOperacional.equals("Linux")) {
			comando = "kill -9";
			
		} else {
			System.out.println("Sistema operacional não suportado!");
		}
	}
	
	/* 4) O quarto, chamado mataNome, que recebe um nome de processo como parâmetro de 
	 * entrada, verifica o SO e, de acordo com SO, selecione o comando para matar o 
	 * processo e o finalize. */
	public void killName(String name) {
		String sistemaOperacional = os();
		String comando;
		if(sistemaOperacional.equals("Windows")) {
			comando = "TASKKILL /IM";
			killProcess(comando, name);
		} else if(sistemaOperacional.equals("Linux")) {
			comando = "pkill -f";
			
		} else {
			System.out.println("Sistema operacional não suportado!");
		}
	}
	
	//Processo para ler os processos ativos.
	public void readProcessList(String comando) {
		Process processo;
		try {
			processo = Runtime.getRuntime().exec(comando);
			InputStream fluxo = processo.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha;
			while((linha = buffer.readLine()) != null) {
				System.out.println(linha);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Processo para matar processos, seja por nome ou PID.
	
	public void killProcess(String comando, String nameOrPid) {
		StringBuffer buffer = new StringBuffer();
		int pid = 0;
		try {
			pid = Integer.parseInt(nameOrPid);
			buffer.append(comando);
			buffer.append(" ");
			buffer.append(pid);
			
		} catch(NumberFormatException e) {
			buffer.append(comando);
			buffer.append(" ");
			buffer.append(nameOrPid);
		}
		
		try {
			Runtime.getRuntime().exec(buffer.toString());
			JOptionPane.showMessageDialog(null, "Prograna fechado com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
