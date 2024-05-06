package controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ArquivosController implements IArquivosController {
	
	public ArquivosController() {
		super();
	}
	
	//Método para conferir se a comida digitada está no grupo das frutas
	@Override
	public void checkFile(String path, String name) throws IOException {
		File arq = new File(path, name);
		if(arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leito = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leito);
			String linha = buffer.readLine();
			String food = JOptionPane.showInputDialog(null, "Digite uma fruta:", "Pesquisa por frutas", JOptionPane.INFORMATION_MESSAGE);
			while(linha != null) {
				if(linha.contains("Fruits") && linha.contains(food)) {
					String[] partes = linha.split(",");
					for(String parte : partes) {
						if(!parte.contains("Fruits")) {
							System.out.print(parte + " | ");
						}
					}
					System.out.println("");
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leito.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo inválido.");
		}
	}

	
	
	@Override
	public void readDir(String path) throws IOException {
		File dir = new File(path);
		if(dir.exists() && dir.isDirectory()) {
			File[] files = dir.listFiles();
			for(File f : files) {
				if(f.isFile()) {
					System.out.println("     \t" + f.getName());
				} else {
					System.out.println("<DIR>\t" + f.getName());
				}
			}
		} else {
			throw new IOException("Diretório inválido.");
		}
	}

	@Override
	public void createFile(String path, String name) throws IOException {
		File dir = new File(path);
		File arq = new File(path, name);
		if(dir.exists() && dir.isDirectory()) {
			boolean exist = false;
			if(arq.exists()) {
				exist = true;
			}
			String conteudo = geraTxt();
			FileWriter fileWriter = new FileWriter(arq, exist);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(conteudo);
			print.flush();
			print.close();
			fileWriter.close();
		} else {
			throw new IOException("Diretório inválido.");
		}
	}

	private String geraTxt() {
		StringBuffer buffer = new StringBuffer();
		String linha = "";
		while(!linha.equalsIgnoreCase("fim")) {
			linha = JOptionPane.showInputDialog(null, "Digite uma frase", "Entrada de texto", JOptionPane.INFORMATION_MESSAGE);
			if(!linha.equalsIgnoreCase("fim")) {
				buffer.append(linha + "\r\n");
			}
		}
		return buffer.toString();
	}

	@Override
	public void readFile(String path, String name) throws IOException {
		File arq = new File(path, name);
		if(arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leito = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leito);
			String linha = buffer.readLine();
			while(linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leito.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo inválido.");
		}
	}

	@Override
	public void openFile(String path, String name) throws IOException {
		File arq = new File(path, name);
		if(arq.exists() && arq.isFile()) {
			Desktop desktop = Desktop.getDesktop();
			desktop.open(arq);
		}
	}


}
