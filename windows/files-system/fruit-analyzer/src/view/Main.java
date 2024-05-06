package view;

import java.io.IOException;

import controller.ArquivosController;
import controller.IArquivosController;

public class Main {

	/* 1) Fazer uma aplicação Java que leia o arquivo generic_food.csv, que deve estar na pasta 
	 * C:\TEMP (A existência da pasta e do arquivo na pasta devem ser validadas), selecione 
	 * cada linha, verifique se o alimento é do GROUP Fruits, se for, deve exibir no console, 
	 * em formato tabular:
	 * 
	 * FOOD NAME tabulação SCIENTIFIC NAME tabulação SUBGROUP
	 * Exemplo:
	 * Kiwi          Actinidia chinensis     Tropical fruits
	 * Pineapple     Ananas comosus          Tropical fruits */
	public static void main(String[] args) {
		
		IArquivosController arq = new ArquivosController();
		String path = "C:\\TEMP";
		String name = "generic_food.csv";
		
		try {
			arq.checkFile(path, name);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
