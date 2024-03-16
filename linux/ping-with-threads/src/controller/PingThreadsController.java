package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PingThreadsController extends Thread{

	private double tempoTotal;
	private String servidor;
	
	//Construtor
	public PingThreadsController(String servidor) {
		this.servidor = servidor;
	}
	
	@Override
	public void run() {
		ping(servidor);
	}
	
	//Método para identificar sistema operacional
	private String os() {
		if(System.getProperty("os.name").contains("Linux")) {
			return "Linux";
		} else {
			return "Outro";
		}
	}
	
	//Método para verificar se sistema operacional é suportado e chamar método para ler processo
	private void ping(String servidor) {
		String sistemaOperacional = os();
		if(sistemaOperacional.equals("Linux")) {
			readProcessPing(servidor);
		} else {
			System.out.println("Sistema operacional não suportado.");
		}
	}
	
	@SuppressWarnings("deprecation")
	private void readProcessPing(String servidor) {
		try {
			//Recebendo e lendo processo
			Process processo = Runtime.getRuntime().exec("ping -4 -c 10 " + servidor);
			InputStream fluxo = processo.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			
			String linha;	
			int count = 0;
			//While para conferir linha por linha e capturar o tempo de ping
			while((linha = buffer.readLine()) != null) {
				if(linha.contains("time=")) {
					
					//Capturando os índices do tempo 
					int indiceInicio = linha.indexOf("time=") + 5; //Pegando o indice de "tempo=" e pulando +5 para que a o valor do tempo seja capturado
					int indiceFim = linha.indexOf(" ms"); //Pegando o indice do fim do tempo
					
					//Armazenando o valor encontrado em uma substring e convertendo para double
					String tempoStr = linha.substring(indiceInicio, indiceFim);
					
					//Convertendo o tempo String para double
					double tempo = Double.parseDouble(tempoStr);
					tempoTotal += tempo;
					count++;
				}
			}
			//Calculando a média do tempo e lançando no console
			double tempoMedio = tempoTotal / count;
			System.out.println("Tempo médio de ping do site  " + servidor + ": " + tempoMedio);
			fluxo.close();
			leitor.close();
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
