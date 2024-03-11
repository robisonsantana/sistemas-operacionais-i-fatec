package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Criar em Eclipse, um novo Java Project com uma classe chamada RedesController.java 
 * no package controller e uma classe Main.java no package view. */
public class RedesController {

	public RedesController() {
		super();
	}
	
	/* 1) O primeiro, chamado os, que identifica e retorna o nome do Sistema 
	 * Operacional (Fazê-lo privado) */
	private String os() {
		if(System.getProperty("os.name").contains("Windows")) {
			return "Windows";
		} else if(System.getProperty("os.name").contains("Linux")) {
			return "Linux";
		} else {
			return "Outro";
		}
	}
	
	/* 2) O segundo, chamado ip, que verifica o Sistema Operacional e, de acordo 
	 * com o S.O., faz a chamada de configuração de IP. 
	 * A leitura do processo chamado deve verificar cada linha e, imprimir, apenas, 
	 * o nome do adaptador de rede e o IPv4, portanto, adaptadores sem IPv4 não 
	 * devem ser mostrados*/
	public void ip() {
		String sistemaOperacional = os();
		String comando;
		if(sistemaOperacional.equals("Windows")) {
			comando = "ipconfig";
			readProcessIp(comando);
		} else if(sistemaOperacional.equals("Linux")) {
			comando = "ifconfig";
			readProcessIp(comando);
		} else {
			System.out.println("Sistema operacional não suportado!");
		}
	}
	
	/* 3) O terceiro, chamado ping, que verifica o Sistema Operacional e, de acordo com 
	 * o S.O. e, faz a chamada de ping em IPv4 com 10 iterações.
	 * A leitura do processo chamado deve verificar as linhas de saída e exibir, apenas, 
	 * o tempo médio do ping. O teste de ping deve ser feito com a URL www.google.com.br */
	public void ping() {
		String sistemaOpreracional = os();
		String comando;
		if(sistemaOpreracional.equals("Windows")) {
			comando = "ping -4 -n 10 www.google.com.br";
			readProcessPing(comando);
		} else if(sistemaOpreracional.equals("Linux")) {
			comando = "ping -4 -c 10 www.google.com.br";
			readProcessPing(comando);
		} else {
			System.out.println("Sistema operacional não suportado!");
		}
	}
	
	//Processo para ler os processos com IPv4.
	public void readProcessIp(String comando) {
		Process processo;
		System.out.println("Adaptadores de Rede com IPv4:\n");
		try {
			processo = Runtime.getRuntime().exec(comando);
			InputStream fluxo = processo.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha;
			String auxString = "";
			while((linha = buffer.readLine()) != null) {
				String[] partes = linha.split(" ");
				for(String parte : partes) {
					if(parte.contains("Adaptador")) {
						auxString = linha;
					} else if(parte.contains("IPv4")) {
						System.out.println(auxString);
						System.out.println(linha + "\n");
					}
				}
			}
			fluxo.close();
			leitor.close();
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Processo para ler a média do ping de 10 processos. 
	public void readProcessPing(String comando) {
		Process processo;
		try {
			processo = Runtime.getRuntime().exec(comando);
			InputStream fluxo = processo.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha;	
			int count = 0;
			int tempoTotal = 0;
			System.out.println("Aguarde...");
			while((linha = buffer.readLine()) != null) {
				String[] partes = linha.split("=");
				for(String parte : partes) {
					if(parte.contains("ms")) {
						Pattern pattern = Pattern.compile("\\d+");
						Matcher matcher = pattern.matcher(parte);
						while (matcher.find()) {
						    String number = matcher.group();
						    if(count < 10) {
						    	tempoTotal += Integer.parseInt(number);
						    	count++;
						    }
						}
					}
				}
			}
			int tempoMedio = tempoTotal / count;
			System.out.println("Tempo médio de ping: " + tempoMedio);
			fluxo.close();
			leitor.close();
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
