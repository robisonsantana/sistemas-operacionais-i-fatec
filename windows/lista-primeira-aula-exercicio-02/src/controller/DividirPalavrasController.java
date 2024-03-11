package controller;

public class DividirPalavrasController {
	
	//Método Construtor:
	public DividirPalavrasController() {
		super();
	}
	
	
	//Método de divisão da frase em posições de um vetor:
	public void DividirPalavras(String frase) {
		
		String[] vetorPalavras = frase.split(";");
		for(String palavra : vetorPalavras) {
			System.out.println(palavra);
		}
	}

}
