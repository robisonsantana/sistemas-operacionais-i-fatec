package controller;

public class ScoreboerdController {
	
	public ScoreboerdController() {
		super();
	}

	public void quickSort(int[] vetor, int inicio, int fim, int[] vetor2) {
		if(inicio < fim) {
			int pivoFixo = dividir(vetor, inicio, fim, vetor2);
			quickSort(vetor, inicio, pivoFixo - 1, vetor2); 
			quickSort(vetor, pivoFixo + 1, fim, vetor2);
		}
	}
 
	private int dividir(int[] vetor, int inicio, int fim, int[] vetor2) {
		int pivo = vetor[inicio];
		int ponteiroEsquerda = inicio + 1;
		int ponteiroDireita = fim;
		/* Enquanto o ponteiro da esquerda se mantiver à esquerda do ponteiro da direita, validar: */
		while(ponteiroEsquerda <= ponteiroDireita) {
			/* Enquanto o ponteiro da esquerda continuar à esquerda (menor ou igual) do ponteiro da 
			 * direita e o valor do ponteiro da esquerda for maior ou igual ao valor do pivô, o 
			 * ponteiro da esquerda incrementa 1. Se alguma condição falhar, o incremento cessa. */
			while(ponteiroEsquerda <= ponteiroDireita && vetor[ponteiroEsquerda] >= pivo) {
				ponteiroEsquerda++;
			}
			/* Enquanto o ponteiro da direita continuar à direita (maior ou igual) do ponteiro da esquerda 
			 * e o valor do ponteiro da direita for menor que o pivô e, o ponteiro da direita decrementa 1. 
			 * Se alguma condição falhar, o decremento cessa. */
			while(ponteiroDireita >= ponteiroEsquerda && vetor[ponteiroDireita] < pivo) {
				ponteiroDireita--;
			}
			/* Se incremento do ponteiro da esquerda e decremento do ponteiro da direita cessarem, mas o ponteiro 
			 * da esquerda continuar à esquerda (menor) do ponteiro da direita, os valores cujos índices forem, 
			 * ponteiro da esquerda e ponteiro da direita, mudam de lugar. Incrementa-se o ponteiro da esquerda 
			 * e decrementase o ponteiro da direita */
			if(ponteiroEsquerda < ponteiroDireita) {
				trocar(vetor, ponteiroEsquerda, ponteiroDireita, vetor2);
				ponteiroEsquerda++;
				ponteiroDireita--;
			}
		}
		trocar(vetor, inicio, ponteiroDireita, vetor2);
		return ponteiroDireita;
	}
 
	private void trocar(int[] vetor, int i, int j, int[] vetor2) {
		int aux = vetor[j];
		vetor[j] = vetor[i];
		vetor[i] = aux;
		
		aux = vetor2[j];
		vetor2[j] = vetor2[i];
		vetor2[i] = aux;
	}

}
