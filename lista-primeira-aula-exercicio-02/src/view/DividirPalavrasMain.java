package view;

import java.util.Scanner;
import controller.DividirPalavrasController;

public class DividirPalavrasMain {

	/* 2. Fazer uma aplicação Java em Eclipse que tenha uma operação que
	 * se permita entrar com um texto, conforme exemplo abaixo, por Scanner
	 * ou JOptionPane, divida o texto em partes, com split e exiba quantas
	 * partes aquele texto tem. A aplicação deve ter uma classe de controle 
	 * com métodos para operações e uma classe de visão que instancie a classe
	 * de controle para a comunicação. 
	 * 
	 * Textos Exemplos:
	 * Texto 1: abóbora;abobrinha;alcachofra;aspargos;batata-doce;berinjela;beterraba
	 * Texto 2: abacate;ameixa;amora;banana;cajá;figo;maçã;melancia;uva;seriguela
	 * Texto 3: acelga;alface;alho-poró;coentro;endívia;escarola;repolho;rúcula
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite a frase:");
		String frase = sc.next();
		
		DividirPalavrasController dpc = new DividirPalavrasController();
		dpc.DividirPalavras(frase);
		
	}

}
