<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body ng-controller="productcontroller">

<button type="button" ng-click="fun()">click</button>

<script type="text/javascript">

var app = angular.module('myApp', []);
app.controller('productcontroller', function($scope, $http) {
	
	$scope.fun=function(){
		
		 $http.get("product")
		  .then(function(response) {
		      $scope.myWelcome = response.data;
		  });
		
		
		
	}

	
})


</script>

		
	




</body>
</html>