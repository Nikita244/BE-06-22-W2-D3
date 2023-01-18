package esercizio3;

public class Presenze {
	
	private String nome;
	private Integer numeroPresenze;
	
	public Presenze(String nome,Integer numeroPresenze ) {
		super();
		this.setNome(nome);
		this.setNumeroPresenze(numeroPresenze);
		
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
		
	}
	
	public Integer getNumeroPresenze() {
		return numeroPresenze;
	}
	

	public void setNumeroPresenze(Integer numeroPresenze) {
		this.numeroPresenze = numeroPresenze;
	}
	
	public static String toStringFile(Presenze presenze) {
		return presenze.nome + "@" + presenze.numeroPresenze;
	}
	
	public static Presenze fromStringFile(String stringFile) {
		String [] split = stringFile.split("@");
		String nomeDaFile = split[0];
		Integer presenzeDaFile = Integer.valueOf(split[1]);
		return new Presenze (nomeDaFile, presenzeDaFile);
	}
}
