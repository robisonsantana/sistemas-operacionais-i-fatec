package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import voltaic.guy.lista.*;

public class SteamController {
	
	public SteamController() {
		super();
	}

	public void filtrarJogos(String ano, String mes, int mediaMinima) {
	    try {
	        FileReader fileReader = new FileReader("C:\\TEMP\\SteamCharts.csv");
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        bufferedReader.readLine(); // Pular a linha de cabeçalho
	        String linha = bufferedReader.readLine(); // Iniciar a leitura dos dados
	        while (linha != null) {
	            String[] dados = linha.split(",");
	            String anoRegistro = dados[1];
	            String mesRegistro = dados[2];
	            if (anoRegistro.equals(ano) && mesRegistro.equals(mes)) {
	                double mediaJogadores = Double.parseDouble(dados[3]);
	                if (mediaJogadores >= mediaMinima) {
	                    System.out.println(dados[0] + " | " + mediaJogadores);
	                }
	            }
	            linha = bufferedReader.readLine();
	        }
	        bufferedReader.close();
	        fileReader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

    public void gerarArquivoFiltrado(String ano, String mes, String diretorio, String nomeArquivo) throws Exception {
        try {
            File dir = new File(diretorio);
            if (!dir.exists() || !dir.isDirectory()) {
                throw new IOException("Diretório inválido.");
            }

            ILista<String> linhasFiltradas = new Lista<>();

            FileReader fileReader = new FileReader("C:\\TEMP\\SteamCharts.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linha = bufferedReader.readLine(); // Skip header line
            while (linha != null) {
                String[] dados = linha.split(",");
                String anoRegistro = dados[1];
	            String mesRegistro = dados[2];
	            if (anoRegistro.equals(ano) && mesRegistro.equals(mes)) {
                    linhasFiltradas.addFirst(dados[0] + ";" + dados[3]);
                }
                linha = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();

            FileWriter fileWriter = new FileWriter(new File(dir, nomeArquivo));
            for (int i = 0; i < linhasFiltradas.size(); i++) {
                fileWriter.write(linhasFiltradas.get(i) + "\r\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}