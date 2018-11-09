package com.rest.template;

public class Carro {

	public void acelera() {
		System.out.println("acelera carro");
	}
	
	public static void main(String[] args) {
		Carro carro = new Carro();
		Passeio passeio = new Passeio();
		imprimir(passeio);
	}
	
	public static void imprimir(Carro carro){
		carro.acelera();
	}
	
}
