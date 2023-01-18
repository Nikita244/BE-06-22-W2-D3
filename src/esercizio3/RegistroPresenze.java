package esercizio3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

import ch.qos.logback.classic.Logger;

public class RegistroPresenze {
	
	public static final String FILE_PATH = "C:\\filetxteclipse\\registropresenze.txt";
	private List<Presenze> listaPresenze;

	public static void main(String[] args) {

		RegistroPresenze registroOriginale = new RegistroPresenze();
		
		registroOriginale.getListaPresenze().add(new Presenze ("Mario Rossi", 5));
		registroOriginale.getListaPresenze().add(new Presenze ("Giorgio Bianchi", 7));
		registroOriginale.getListaPresenze().add(new Presenze ("Gianni Verdi", 8));
		
		try {
			registroOriginale.salvaListaPresenze();
			
			RegistroPresenze registroCaricato = new RegistroPresenze();
			
			for (Presenze pres : registroCaricato.getListaPresenze()) {
				System.out.println(pres.getNome() + " - Num. presenze " + pres.getNumeroPresenze());
			}
		}catch(IOException e) {
			//Logger.error("Errore", e);
		}

	}
	
	public RegistroPresenze() {
		this.listaPresenze = new ArrayList<Presenze>();
	}
	
	public List<Presenze> getListaPresenze() {
		
		return listaPresenze;
	}
	
	public void setListaPresenze(List<Presenze> listaPresenze) {
		this.listaPresenze = listaPresenze;
	}

	private void salvaListaPresenze() throws IOException {
		String fileString = "";
		
		for(Presenze curPresenze : listaPresenze) {
			if(fileString.length() !=0) {
				fileString += "#";
			}
			fileString += Presenze.toStringFile(curPresenze);
		}
		
		File file = new File (FILE_PATH);
		FileUtils.writeStringToFile(file, fileString, "UTF-8");
		System.out.println("Dati salvati correttamente sul file " + FILE_PATH);
		
	}
	
	public void caricaListaPresenze()throws IOException{
		this.listaPresenze.clear();
		
		File file = new File (FILE_PATH);
		
		String fileString = FileUtils.readFileToString(file, "UTF-8");
		
		List <String> splitPresenzeString = Arrays.asList(fileString.split("#"));
		
		for (String curString : splitPresenzeString) {
			this.listaPresenze.add(Presenze.fromStringFile(curString));
		}
		System.out.println("Dati caricati correttamente dal file " + FILE_PATH);
	}

}
