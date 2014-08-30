package dwrobject;

import java.util.ArrayList;

public class GestisciUtenti {

	private static int quanti = 0;
	private int id = 0;
	private static ArrayList<Utente> listaUtenti = new ArrayList<Utente>();
	
	public GestisciUtenti(){
		id = ++quanti;
		//System.out.println("reference: " + this);
		System.out.println("GestisciUtenti " + id + " creato");
	}
	
	public void addUtente(Utente utente){
		System.out.println("GestisciUtenti " + id + " addUtente chiamato");
		listaUtenti.add(utente);
	}
	
	public Utente getUtente(int index){
		Utente temp;
		if ( (listaUtenti.size() == 0) || (listaUtenti.size() <= index ) ){
			temp = new Utente();
		}
		else {
			temp = listaUtenti.get(index);
		}
		
		System.out.println("GestisciUtenti " + id + " getUltimoUtente chiamato, restituito utente " + listaUtenti.size() + " utenti");
		return temp;
	}
	
	public ArrayList<Utente> getListaUtenti(){
		System.out.println("GestisciUtenti " + id + " getListaUtenti chiamato, contiene " + listaUtenti.size() + " utenti");
		return listaUtenti;
	}
	
}
