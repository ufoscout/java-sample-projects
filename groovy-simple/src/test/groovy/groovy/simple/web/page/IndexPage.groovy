package groovy.simple.web.page

import geb.Page;

class IndexPage extends Page {
	static url = "index.jsp"
	
	static at = { waitFor { title == 'Groovy web test'}}
	static content = {
		
		applicationNameSpan {$('span', 0)}
		
	}
	
}
