package dwrobject;

public class Utente {
	private String nome = "";
	private String cognome = "";
	private String frasePreferita = "";
	
	public Utente(){
		System.out.println("Utente creato");
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		System.out.println("Utente.setNome " + nome);
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		System.out.println("Utente.setCognome " + cognome);
		this.cognome = cognome;
	}
	public String getFrasePreferita() {
		return frasePreferita;
	}
	public void setFrasePreferita(String frasePreferita) {
		System.out.println("Utente.setFrasePreferita " + frasePreferita);
		this.frasePreferita = frasePreferita;
	}
	
	
}
