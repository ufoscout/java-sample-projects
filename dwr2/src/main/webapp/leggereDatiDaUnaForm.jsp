<html>
<header>
Ma a che serve sto header!?!
<br />
</header>


<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="dwr/interface/GestisciUtenti.js"></script>


<script type="text/javascript">
function inserisciUtente(){
	//alert("chiamato InserisciUtente");
	
	var p = {
			  nome:DWRUtil.getValue("nome"),
			  cognome:DWRUtil.getValue("cognome"),
			  frasePreferita:DWRUtil.getValue("frase")
			};
	
	GestisciUtenti.addUtente(p);
		
	GestisciUtenti.getUtente( 1, 
            {
                callback : function (objx){
                    //alert(objx.nome);
                },
                preHook : function() {
                  	// funzione chiamata prima dell'esecuzione del metodo
                },
                postHook: function() {
					// funzione chiamata dopo l'esecuzione del metodo
                }
            });

	GestisciUtenti.getListaUtenti(
            {
                callback : function (objx){
            		aggiornaPagina(objx);
                },
                preHook : function() {
                  	// funzione chiamata prima dell'esecuzione del metodo
                },
                postHook: function() {
					// funzione chiamata dopo l'esecuzione del metodo
                }
            });

	//dwr.engine.endBatch();   
}

function aggiornaPagina(messages){
	//alert("chiamato aggiornaPagina " + messages);
	  var html = "utenti registrati: <br/>";
	  for (var data in messages){
		  //alert("leggo utente");
	    html = html + "<div>" + messages[data].nome + " " + messages[data].cognome + " " + messages[data].frasePreferita + "</div>";
	  }
	  DWRUtil.setEscapeHtml(false);
	  DWRUtil.setValue("divresult", html);
} 
</script>

<body>

<div id="divform">
	nome: <input type="text" id="nome" value=""><br />
	cognome: <input type="text" id="cognome" value=""><br />
	frase preferita <input type="text" id="frase" value=""><br />
	<input type=button value="ok" onclick="inserisciUtente()">
</div>
<div id="divresult"></div>

</body>

</html>