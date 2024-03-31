package controller;

import java.util.concurrent.Semaphore;

public class TransactionController extends Thread{
	
	Semaphore mutexSaldo;
	Semaphore mutexDeposito;
	private int saldo;
	private int idCliente;
	
	public TransactionController(int saldo, int idCliente, Semaphore mutexSaldo, Semaphore mutexDeposito) {
		this.saldo = saldo;
		this.idCliente = idCliente;
		this.mutexSaldo = mutexSaldo;
		this.mutexDeposito = mutexDeposito;
	}
	
	public void run() {
		transacao();
	}
	
	//Procedimento de  escolha de transação
	private void transacao() {
		int escolha = (int)((Math.random() * 2) + 1);
		if(escolha == 1) {
			try {
				mutexSaldo.acquire();
				saque();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			} finally {
				mutexSaldo.release();
			}
		} else {
			try {
				mutexDeposito.acquire();
				deposito();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			} finally {
				mutexDeposito.release();
			}
		}
	}
	
	//Procedimento de saldo
	private void saque() {
		int valorSaque = (int)((Math.random() * 500) + 1501);
		System.out.println("\n"
						 + "Cliente de ID" + (idCliente + 1) + " está fazendo um saque de " + valorSaque + " reais... \n"
						 + "Saldo inial: " + saldo + "\n"
						 + "Saldo após saque: " + (saldo - valorSaque));
		try {
			sleep((int)((Math.random() * 1001) + 1000));
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
	
	//Procedimento de depósito
	private void deposito() {
		int valorDeposito = (int)((Math.random() * 10000) + 100);
		System.out.println("\n"
				 		 + "Cliente de ID" + (idCliente + 1) + " está fazendo um depósito de " + valorDeposito + " reais. \n"
				 		 + "Saldo inial: " + saldo + "\n"
				 		 + "Saldo após depósito: " + (saldo + valorDeposito));
		try {
			sleep((int)((Math.random() * 1001) + 1000));
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
	

}
