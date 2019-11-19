var app=angular.module("adminapp", ['ngRoute','ngFileUpload','ngAnimate']);


app.config(function($routeProvider,$locationProvider) {
	
	
	$routeProvider
    .when("/", {
    	templateUrl : "views/register.html",
    	
    	})
    	
    	
    .when("/previous", {
    	templateUrl : "views/previous.html",
    		
    }).when("/generatenewform", {
    	templateUrl : "views/NewFile2.html",
    		
    }).when("/viewdrafts", {
        templateUrl : "views/viewdrafts.html"
    }).when("/draft", {
        templateUrl : "views/NewFile2.html"
    }).when("/viewtreatment1", {
    	templateUrl : "views/modifyplan.html"
    }).when("/existing", {
        templateUrl : "views/existing.html"
    })
    
    
    ;
});

app.filter('Stringdate', function() {
    return function(x) {
        
        
        
        return x.split("_")[0];
    };
});

app.filter('PatientDOB', function() {
    return function(x) {
        if(x==null){return x;}
        
        if(x.toString().indexOf("T")!=-1){return x.split("T")[0];}
        return x;
    };
});

app.filter('PatientDOBfilter', function() {
    return function(x) {
        if(x==null){return x;}
        else{ y=x.toString().split("-"); 
        return y[2]+"-"+y[1]+"-"+y[0];
        }
      
    };
});

