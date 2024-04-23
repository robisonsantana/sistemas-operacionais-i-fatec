package model;

public class Jogador {
	
	public int id;
	public int pontos;
	
	public Jogador(int id, int pontos) {
		this.id = id;
		this.pontos = pontos;
	}

	@Override
	public String toString() {
		return "id = " + id + " pontos = " + pontos;
	}
	
}
