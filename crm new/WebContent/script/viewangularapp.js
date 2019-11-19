var viewapp=angular.module("viewapp", []);

viewapp.controller("viewcontroller", function($scope,$http) {
	$scope.formdata={}
	var matches = /name=([^&#=]*)/.exec(window.location.search);
	var refid = matches[1];
	if(refid!=null){
		$scope.formdata.name=refid;
	}
	
	
	$http.post("rest/viewuploads", JSON.stringify($scope.formdata)).then(function(value) {
		$scope.uploadarray=value.data;
	}, function(reason) {
		
	})
	
})