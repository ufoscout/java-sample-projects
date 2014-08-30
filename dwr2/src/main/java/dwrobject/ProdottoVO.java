package dwrobject;

public class ProdottoVO{
	  String nome;
	  String immagine;
	  
	  public ProdottoVO(){ }
	  
	  public ProdottoVO(String nome, String immagine){
	    setImmagine(immagine);
	    setNome(nome);
	  }
	  
	  public String getNome(){
	    return nome;
	  }
	  
	  public void setNome(String nome) {
	    this.nome = nome;
	  }
	  
	  public String getImmagine() {
	    return immagine;
	  }
	  
	  public void setImmagine(String immagine) {
	    this.immagine = immagine;
	  }
	} 