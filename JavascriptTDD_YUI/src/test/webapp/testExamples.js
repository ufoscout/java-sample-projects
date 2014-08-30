function sortArray(array) {
    if (array instanceof Array){
        array.sort();
    } else {
        throw new TypeError("Expected an array");
    }
}


function testExamples(Y) {
	return new Y.Test.Case({
			
		name: "Test examples",
		
		//---------------------------------------------
	    // Special instructions
	    //---------------------------------------------
	 
	    _should: {
	        ignore: {
	            testToIgnore: true //ignore this test
	        },
	        error: {
	            testSortArray: "Expected an array"
	        }
	    },
		
		
	    //---------------------------------------------
	    // Setup and tear down
	    //---------------------------------------------
	 
	    setUp : function () {
	        this.data = { name : "Nicholas", age : 28 };
	    },
	 
	    tearDown : function () {
	        delete this.data;
	    },
	 
	    //---------------------------------------------
	    // Tests
	    //---------------------------------------------
	
	    testToIgnore: function () {
	        Y.Assert.areEqual("Nicholas", "Nicholas", "This test will be ignored");
	    },
	 
	    testName: function () {
	        Y.Assert.areEqual("Nicholas", this.data.name, "Name should be 'Nicholas'");
	    },
	 
	    testAge: function () {
	        Y.Assert.areEqual(28, this.data.age, "Age should be 28");
	    },
	
	    testUsingAsserts : function () { 
	    	value = 5;
	    	flag = true;                           
	        Y.assert(value == 5, "The value should be five.");
	        Y.assert(flag, "Flag should be true.");
	    },
	
	    testEqualityAsserts : function () {
	        Y.Assert.areEqual(5, 5);     //passes
	        Y.Assert.areEqual(5, "5");     //passes
	        Y.Assert.areNotEqual(5, 6);  //passes
	        Y.Assert.areEqual(5, 6, "Five was expected."); //fails
	    },
	
	    testSamenessAsserts : function () {
	        Y.Assert.areSame(5, 5);      //passes
	        Y.Assert.areSame(5, "5");    //fails
	        Y.Assert.areNotSame(5, 6);   //passes
	        Y.Assert.areNotSame(5, "5"); //passes
	        Y.Assert.areSame(5, 6, "Five was expected."); //fails
	    },
	
	    testDataTypeAsserts : function () {            
	        Y.Assert.isString("Hello world");     //passes
	        Y.Assert.isNumber(1);                 //passes
	        Y.Assert.isArray([]);                 //passes
	        Y.Assert.isObject([]);                //passes
	        Y.Assert.isFunction(function(){});    //passes
	        Y.Assert.isBoolean(true);             //passes
	        Y.Assert.isObject(function(){});      //passes
	        Y.Assert.isNumber("1", "Value should be a number.");  //fails
	        Y.Assert.isString(1, "Value should be a string.");    //fails
	    },
	
	    testTypeOf : function () {
	 
	        Y.Assert.isTypeOf("string", "Hello world");   //passes
	        Y.Assert.isTypeOf("number", 1);               //passes
	        Y.Assert.isTypeOf("boolean", true);           //passes
	        Y.Assert.isTypeOf("number", 1.5);             //passes
	        Y.Assert.isTypeOf("function", function(){});  //passes
	        Y.Assert.isTypeOf("object", {});              //passes
	        Y.Assert.isTypeOf("undefined", this.blah);    //passes
	        Y.Assert.isTypeOf("number", "Hello world", "Value should be a number."); //fails
	    },
	
	    testInstanceOf : function () {
	        Y.Assert.isInstanceOf(Object, {});    //passes
	        Y.Assert.isInstanceOf(Array, []);     //passes            
	        Y.Assert.isInstanceOf(Object, []);     //passes            
	        Y.Assert.isInstanceOf(Function, function(){});  //passes
	        Y.Assert.isInstanceOf(Object, function(){});  //passes
	        Y.Assert.isTypeOf(Array, {}, "Value should be an array."); //fails
	    },
	    
	    testSpecialValues : function () {                            
	        Y.Assert.isFalse(false);      //passes
	        Y.Assert.isTrue(true);        //passes            
	        Y.Assert.isNaN(NaN);          //passes            
	        Y.Assert.isNaN(5 / "5");      //passes
	        Y.Assert.isNotNaN(5);         //passes
	        Y.Assert.isNull(null);        //passes
	        Y.Assert.isNotNull(undefined);    //passes
	        Y.Assert.isUndefined(undefined);  //passes
	        Y.Assert.isNotUndefined(null);    //passes
	        Y.Assert.isUndefined({}, "Value should be undefined."); //fails
	    },
	    
	    testForceFail : function () {
	        Y.Assert.fail("I decided this should fail.");
	    },
	
	    testSortArray: function () {
	        sortArray(12);  //this should throw an error
	    }
    });

}