package controller;

import java.util.concurrent.Semaphore;

public class TransactionMain {

	/* 3) Um banco deve controlar Saques e Depósitos. O sistema pode permitir um Saque e um Depósito Simultâneos, 
	 * mas nunca 2 Saques ou 2 Depósitos Simultâneos. Para calcular a transação (Saque ou Depósito), o método 
	 * deve receber o código da conta, o saldo da conta e o valor a ser transacionado. Deve-se montar um sistema 
	 * que considera 20 transações simultâneas enviadas ao sistema (aleatoriamente, essas transações podem ser qualquer 
	 * uma das opções) e tratar todas as transações. */
	public static void main(String[] args) {
		
		int[] saldoContas = new int[20];
		for(int i = 0; i < 20; i++) {
			saldoContas[i] = (int)((Math.random() * 5001) + 2501);
		}
		
		Semaphore mutexSaldo = new Semaphore(1);
		Semaphore mutexDeposito = new Semaphore(1);
		
		for(int idCliente = 0; idCliente < 20; idCliente++) {
			TransactionController transaction = new TransactionController(saldoContas[idCliente], idCliente, mutexSaldo, mutexDeposito);
			transaction.start();
		}

	}

}
