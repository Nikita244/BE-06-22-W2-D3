/*Esercizio #2
Scrivere un programma multithread in Java che dato un array di 3000 valori numerici generati casualmente ne esegue la somma in modo parallelo. Creare 3 thread e assegnare ad ogni thread una partizione dell’array (1000 valori). Ogni thread esegue la somma dei propri valori e mettere il risultato in una struttura dati del thread main contenente i risultati parziali. Il thread main deve sincronizzarsi sulla terminazione (join) dei thread figli dopodiché somma i risultati parziali e li stampa su console.*/


package esercizio2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ch.qos.logback.classic.Logger;

public class Main implements Runnable {
	
	private int[] arrayElementi;
	private int indiceIniziale;
	private int numeroElementi;
	private List<Integer> listaRisultati;
	
	public Main(int[] arrayElementi, int indiceIniziale, int numeroElementi,  List<Integer> listaRisultati ) {
		super();
		this.arrayElementi=arrayElementi;
		this.indiceIniziale=indiceIniziale;
		this.numeroElementi=numeroElementi;
		this.listaRisultati=listaRisultati;
	}
	
	@Override
	public void run() {
		int sommaParziale = 0;
		for(int i = indiceIniziale; i < indiceIniziale; numeroElementi ++) {
			sommaParziale += arrayElementi[i];
		}
		listaRisultati.add(sommaParziale);
	}
	


	public static void main(String[] args) {
		int[] elementiDaSommare = inizializzaArrayDaSommare(3000);
		
		int sommaSequenziale = 0;
		for(int i = 0; i< elementiDaSommare.length; i++) {
			sommaSequenziale += elementiDaSommare[i];
		}
		System.out.println("Totale calcolo sequenziale: " + sommaSequenziale);
		
		List<Integer> listaRisultati = new ArrayList<Integer>();
		
		List <Runnable> listaRunnable = Arrays.asList(new Main (elementiDaSommare, 0, 1000,listaRisultati),
				new Main (elementiDaSommare, 1000, 2000,listaRisultati),
				new Main (elementiDaSommare, 2000, 1000,listaRisultati));
		
		List<Thread> listaThread = new ArrayList<Thread>();
		for(Runnable curRun : listaRunnable) {
			Thread curThread= new Thread (curRun);
			listaThread.add(curThread);
 		}
		for(Thread curThread : listaThread) {
			curThread.start();
		}
		
		try {
			for(Thread curThread : listaThread) {
				curThread.join();
			}
			System.out.println("Esecuzione parallela terminata - calcolo totale");
			
			
			int totale = 0;
			
			for(Integer curParziale : listaRisultati) {
				totale += curParziale;
			}
			System.out.println("Totale calcolo parallelo: " + totale);
			
		}catch (InterruptedException e) {
			System.out.println("Errore" + e);
		}
	
		
	}

	private static int[] inizializzaArrayDaSommare(int size) {
		Random randomGenerator = new Random();
		
		int[] result = new int [size];
		for(int i = 0; i< size; i++) {
			result[i] = randomGenerator.nextInt(100);
		}
		return result;
		
	}
}
