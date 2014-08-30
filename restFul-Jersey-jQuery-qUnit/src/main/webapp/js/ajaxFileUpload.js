// When the DOM loads, initailize the form to upload the files
// using an AJAX "like" call rather than a form submit.
$(document).ready(
	function(){
		// Get a reference to the form we are going to be
		// hooking into.
		var jForm = $( "form:first" );
 
		// Attach an event to the submit method. Instead of
		// submitting the actual form to the primary page, we
		// are going to be submitting the form to a hidden
		// iFrame that we dynamically create.
		jForm.submit(
			function( objEvent ){
				var jThis = $( this );
 
				// Create a unique name for our iFrame. We can
				// do this by using the tick count from the date.
				var strName = ("uploader" + (new Date()).getTime());
 
				// Create an iFrame with the given name that does
				// not point to any page - we can use the address
				// "about:blank" to get this to happen.
				var jFrame = $( "<iframe name=\"" + strName + "\" src=\"about:blank\" />" );
 
				// We now have an iFrame that is not attached to
				// the document. Before we attach it, let's make
				// sure it will not be seen.
				jFrame.css( "display", "none" );
 
				// Since we submitting the form to the iFrame, we
				// will want to be able to get back data from the
				// form submission. To do this, we will have to
				// set up an event listener for the LOAD event
				// of the iFrame.
				jFrame.load(
					function( objEvent ){
						// Get a reference to the body tag of the
						// loaded iFrame. We are doing to assume
						// that this element will contain our
						// return data in JSON format.
						var objUploadBody = window.frames[ strName ].document.getElementsByTagName( "body" )[ 0 ];
 
						// Get a jQuery object of the body so
						// that we can have better access to it.
						var jBody = $( objUploadBody );
 
						// Assuming that our return data is in
						// JSON format, evaluate the body html
						// to get our return data.
						// var objData = eval( "(" + jBody.html() + ")" );
						var objData = jBody.html();						

 
						// "Alert" the return data (this should
						// be an array of the server-side files
						// that were uploaded).
						alert( "Return Data: " + objData );
						 // Remove the iFrame from the document.
						// Because FireFox has some issues with
						// "Infinite thinking", let's put a small
						// delay on the frame removal.
						setTimeout(
							function(){
								jFrame.remove();
							},
							100
							);
					}
					);
 
 
				// Attach to body.
				$( "body:first" ).append( jFrame );
 
				// Now that our iFrame it totally in place, hook
				// up the frame to post to the iFrame.
				jThis
					.attr( "action", "../rest/uploadfile" )
					.attr( "method", "post" )
					.attr( "enctype", "multipart/form-data" )
					.attr( "encoding", "multipart/form-data" )
					.attr( "target", strName )
				;
			}
			);
	}
);