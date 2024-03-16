package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* No universo do Sistema operacional Linux existem diversas distribuições. Os comandos 
 * Java System.getProperty(“os.name”) e System.getProperty(“os.version”) trazem dados 
 * sobre o Kernel Linux, mas não sobre a distribuição. 
 * 
 * Criar em Eclipse, um novo Java Project com uma classe chamada DistroController.java no 
 * package controller e uma classe Main.java no package view. */
public class DistoController {

	public DistoController() {
		super();
	}
	
	/* 1) O primeiro, chamado os, que identifica e retorna o nome do Sistema Operacional (Fazê-lo privado) */
	private String os() {
		if(System.getProperty("os.name").contains("Linux")) {
			return "Linux";
		} else {
			return "Outro";
		}
	}
	
	/* 2) O segundo, chamado exibeDistro, que verifica o SO e, se for Linux, selecione o comando para exibir 
	 * as propriedades da distribuição. Deve-se exibir o nome e a versão da distribuição. Caso o SO não seja 
	 * Linux, exibir uma mensagem comunicando. */
	public void exibeDistro() {
		String sistemaOperacional = os();
		if(sistemaOperacional.equals("Linux")) {
			try {
				Process processo = Runtime.getRuntime().exec("cat /etc/os-release");
				InputStream fluxo = processo.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha;
				while((linha = buffer.readLine()) != null){
					System.out.println(linha);
					if(linha.contains("PRETTY_NAME=")) {
						System.out.println(linha);
					} else if(linha.contains("VERSION=")) {
						System.out.println(linha);
					}
				}
			} catch(IOException e) {
				e.printStackTrace();				
			}
		} else {
			System.out.println("Sistema operacional não  suportado!");
		}
	}

}
