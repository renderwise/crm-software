var app=angular.module("supportapp", ['ngRoute','ngFileUpload','ngAnimate']);


app.config(function($routeProvider,$locationProvider) {
	
	
	$routeProvider
    .when("/", {
    	templateUrl : "views/previous.html",
    		
    }).when("/contactus", {
        templateUrl : "views/contactus.html"
    }).when("/changepassword", {
        templateUrl : "views/changepassword.html"
    }).when("/viewdrafts", {
        templateUrl : "views/viewdrafts.html"
    }).when("/draft", {
        templateUrl : "views/supportviewform.html"
    }).when("/viewprevious", {
        templateUrl : "views/previous.html"
    }).when("/viewtreatment", {
        templateUrl : "views/viewtreatmentforuser.html"
    }).when("/generatenewform", {
    	templateUrl : "views/NewFile2.html"
    }).when("/viewtreatment1", {
    	templateUrl : "views/modifyplan.html"
    }).when("/generatenewform1", {
        templateUrl : "views/NewFile2.html"
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

