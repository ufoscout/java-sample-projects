package dwrobject;

import java.util.ArrayList;

public class Prodotti {
  public String getProdottiString(int idMacrocategoria){
    StringBuffer htmlCode = new StringBuffer(0);
    
    switch (idMacrocategoria){
      case 1:{
        htmlCode.append("<div>monitor 1 <img src=\"images/monitor.jpg\"></div>");
        htmlCode.append("<div>monitor 2 <img src=\"images/monitor.jpg\"></div>");
        htmlCode.append("<div>monitor 3 <img src=\"images/monitor.jpg\"></div>");
        break;
      }
      case 2:{
        htmlCode.append("<div>notebook 1 <img src=\"images/notebook.jpg\"></div>");
        htmlCode.append("<div>notebook 2 <img src=\"images/notebook.jpg\"></div>");
        htmlCode.append("<div>notebook 3 <img src=\"images/notebook.jpg\"></div>");
        break;
      }
      case 3:{
        htmlCode.append("<div>stampante 1 <img src=\"images/stampante.jpg\"></div>");
        htmlCode.append("<div>stampante 2 <img src=\"images/stampante.jpg\"></div>");
        htmlCode.append("<div>stampante 3 <img src=\"images/stampante.jpg\"></div>");
        break;
      }
    }
    return htmlCode.toString();
  }
  
  public ArrayList<ProdottoVO> getProdottiArray(int idMacrocategoria){
	    
	  ArrayList<ProdottoVO> listaProdotti = new ArrayList<ProdottoVO>();
	    
	    switch (idMacrocategoria){
	      case 1:{
	        listaProdotti.add(new ProdottoVO("monitor 1", "images/monitor.jpg"));
	        listaProdotti.add(new ProdottoVO("monitor 2", "images/monitor.jpg"));
	        listaProdotti.add(new ProdottoVO("monitor 3", "images/monitor.jpg"));
	        break;
	      }
	      case 2:{
		        listaProdotti.add(new ProdottoVO("notebook 1", "images/notebook.jpg"));
		        listaProdotti.add(new ProdottoVO("notebook 2", "images/notebook.jpg"));
		        listaProdotti.add(new ProdottoVO("notebook 3", "images/notebook.jpg"));
	        break;
	      }
	      case 3:{
		        listaProdotti.add(new ProdottoVO("stampante 1", "images/stampante.jpg"));
		        listaProdotti.add(new ProdottoVO("stampante 2", "images/stampante.jpg"));
		        listaProdotti.add(new ProdottoVO("stampante 3", "images/stampante.jpg"));
	        break;
	      }
	    }
	    return listaProdotti;
	  }
  
  
} 
