
function testFullname(Y) {
	return new Y.Test.Case({
		
		name: "Test fullname",
	
	    //---------------------------------------------
	    // Setup and tear down
	    //---------------------------------------------
	 
	    setUp : function () {
	    },
	 
	    tearDown : function () {
	    },
	 
	    //---------------------------------------------
	    // Tests
	    //---------------------------------------------
	 
	    //traditional test names
	    testFirstname : function () {
	        Y.Assert.areEqual("Nicholas", fullname( "Nicholas", "" ), "--This test will fail--");
	    },
	 
	    testLastname : function () {
	        Y.Assert.areEqual(" Nicholas", fullname( "" , "Nicholas"));
	    },
	    
	    testFullname : function () {
	        Y.Assert.areEqual("Mago Wizard", fullname( "Mago" , "Wizard"));
	    }
	});
}