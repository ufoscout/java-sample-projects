<html>

<!-- questi due importano il motore di DWR -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script> 

<!-- Questi sono gli import delle mie classi gestite con dwr-->
<script type="text/javascript" src="dwr/interface/Prodotti.js"></script> 

<script type='text/javascript'>
function aggiornaPaginaConString(msg){
  DWRUtil.setEscapeHtml(false);
  DWRUtil.setValue("prodotti", msg);
} 

function aggiornaPaginaConArray(messages){
	  var html = "";
	  for (var data in messages){
	    html = html + "<div>" + messages[data].nome + "<img src=\"" + messages[data].immagine + "\"></div>";
	  }
	  DWRUtil.setEscapeHtml(false);
	  DWRUtil.setValue("prodottiArray", html);
} 
</script>

<body>

<h2>This is a demo of DWR using a String</h2>

<div id="box">
  <div id="macrocategorie">
    <ul>
      <li><a href="#" onclick="Prodotti.getProdottiString(1, aggiornaPaginaConString)">Monitor</a></li>
      <li><a href="#" onclick="Prodotti.getProdottiString(2, aggiornaPaginaConString)">Portatili</a></li>
      <li><a href="#" onclick="Prodotti.getProdottiString(3, aggiornaPaginaConString)">Stampanti</a></li>
    </ul>
  </div>
  <div id="prodotti"></div>
</div> 

<h2>This is a demo of DWR using an Array of Objects</h2>

<div id="box">
  <div id="macrocategorie">
    <ul>
      <li><a href="#" onclick="Prodotti.getProdottiArray(1, aggiornaPaginaConArray)">Monitor</a></li>
      <li><a href="#" onclick="Prodotti.getProdottiArray(2, aggiornaPaginaConArray)">Portatili</a></li>
      <li><a href="#" onclick="Prodotti.getProdottiArray(3, aggiornaPaginaConArray)">Stampanti</a></li>
    </ul>
  </div>
  <div id="prodottiArray"></div>
</div> 

</body>
</html>
