Install needed npm packages:
npm install -yo grunt-cli bower

install webapp generator
npm install -generator-webapp

install angularjs generator
npm install generator-angular

Generate a new angular project
yo angular --minsafe
minsafe has to be added if minifycation in required.

Add angular blocks:
yo angular:controller myController
yo angular:directive myDirective
yo angular:filter myFilter
yo angular:service myService