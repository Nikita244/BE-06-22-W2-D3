/*Esercizio #1
Creare un’applicazione con due thread figli. Ciascuno dei due thread ha associato un simbolo (ad es. * o #). Fare in modo che ciascun thread stampi su console il proprio simbolo per 10 volte, attendendo 1 secondo tra una stampa e l'altra.*/





package esercizio1;

import java.lang.System.Logger;

import org.slf4j.LoggerFactory;


public class Simboli implements Runnable {
	
	
	//private static final Logger logger = LoggerFactory.getLogger(Simboli.class);
	
	private  String simbolo;
	
	public Simboli (String simbolo) {
		this.simbolo = simbolo;
	}

	public static void main(String[] args) {
		Simboli runnable1 = new Simboli("#");
		Simboli runnable2 = new Simboli("*");
		
		Thread thread1 = new Thread(runnable1);
		Thread thread2 = new Thread(runnable2);
		
		thread1.start();
		thread2.start();
	}

	@Override
	public void run() {
		
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println(simbolo);
				Thread.sleep(1000);
			}
		}catch (InterruptedException e ) {
			
		}System.out.println("Errore");
		
	}

}
