app.controller("cont", function($scope,$http,$location,Upload, $timeout) {
	$scope.a=2;
	$scope.formdata={};
	$scope.filterby={};
	$scope.alldata=null;
	$scope.contactusform={};
	$scope.showsubmit=true;
	$scope.adminuser=null;
	$scope.previousdates={};
	$scope.draftId=null;
	$scope.alldoctors={};
	$scope.citysss={};
	$scope.formdata.date=moment().format("YYYY-MM-DD");
	$scope.viewtreatment=function(serialnumber){
		window.location.href="treatment2.jsp?serialnumber="+serialnumber;
	}
	$scope.required=false;

	$scope.slideimages=["download1.jpg","download2.jpg","download3.jpg","download4.jpg","download5.jpg","download6.jpg","download7.jpg","download8.jpg"]
	
	
	
	
	
$scope.findrows=function(){
	
	
	$scope.formdata.fromdate=document.getElementById("from").value;
	$scope.formdata.todate=document.getElementById("to").value;
	$scope.formdata.loginuser=$scope.user;
	
	$http.post("rs/findrows",$scope.formdata).then(function(value) {
		
		$scope.previousdates=value.data;
		console.log($scope.previousdates);
		
		
			
		}, function(reason) {
			
		}, function(value) {
			$window.alert("dfj");
			
		})
	
	
		
		
	}
	
	
	
	$scope.datepp=function(){
		

		            $( "#amitg" ).datepicker();
		   
		
	}
	
	
	$scope.exportTableToExcel=function(tableID,filename=" "){
		
			var downloadLink;
		    var dataType = 'application/vnd.ms-excel';
		    var tableSelect = document.getElementById(tableID);
		    var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');
		    
		    // Specify file name
		    filename = filename?filename+'.xls':'excel_data.xls';
		    
		    // Create download link element
		    downloadLink = document.createElement("a");
		    
		    document.body.appendChild(downloadLink);
		    
		    if(navigator.msSaveOrOpenBlob){
		        var blob = new Blob(['\ufeff', tableHTML], {
		            type: dataType
		        });
		        navigator.msSaveOrOpenBlob( blob, filename);
		    }else{
		        // Create a link to the file
		        downloadLink.href = 'data:' + dataType + ', ' + tableHTML;
		    
		        // Setting the file name
		        downloadLink.download = filename;
		        
		        //triggering the function
		        downloadLink.click();
		    }
	
		
	}
	
	
	

	
	$scope.loadfunction=function(){
		
		document.getElementById("loadclick").click();
		
		
	}
	
	
	
	$scope.findrowscheck2=function(){
		
		console.log($scope.alldoctors);
		
		$scope.alldoctornamearray=[];
		var i=0;
		for(i=0;i<$scope.alldoctors.length;i++){
			
			$scope.alldoctornamearray.push($scope.alldoctors[i].name);
			
		}
	/*	
		if($scope.alldoctornamearray.includes(document.getElementById("doctornameforsearch1").value)){
		*/
		
		if(true){
		console.log($scope.alldoctornamearray);
		//alert(document.getElementById("checklogsselect").value);
		
			$scope.formdata.crmname=document.getElementById("crmnameforsearch").value;
		
			$scope.formdata.fromdate=document.getElementById("from").value;
			$scope.formdata.todate=document.getElementById("to").value;
			$scope.formdata.cityforsearch=document.getElementById("cityforsearch1").value;
			console.log($scope.formdata.cityforsearch);
			$scope.formdata.doctornameforsearch1=document.getElementById("doctornameforsearch1").value;
			$scope.formdata.checklogsselect1=document.getElementById("checklogsselect1").value;
			
		
			$scope.formdata.arraysearch1={};
			$scope.formdata.arraysearch2={};
			
			$scope.formdata.filterby1=document.getElementById("filterby11").value;
			$scope.formdata.filterby2=document.getElementById("filterby21").value;
			$scope.formdata.filterby3=document.getElementById("filterby31").value;
			$scope.formdata.sign1=document.getElementById("sign11").value;
			$scope.formdata.sign2=document.getElementById("sign21").value;
			$scope.formdata.sign3=document.getElementById("sign31").value;
			$scope.formdata.value1=document.getElementById("value11").value;
			$scope.formdata.value2=document.getElementById("value21").value;
			$scope.formdata.value3=document.getElementById("value31").value;
			$scope.formdata.andor1=document.getElementById("andor11").value;
			$scope.formdata.andor2=document.getElementById("andor21").value;
			
			
			
		$http.post("rs/findrowscheck2",$scope.formdata).then(function(value) {
			
			$scope.previousdates1=value.data;
			console.log($scope.previousdates1);
			
			
				
			}, function(reason) {
				
			}, function(value) {
				$window.alert("dfj");
				
			})
		
	
		}
		else{
			
			alert("Please Enter Valid doctor Or Add a new doctor");
		}
			
		}
	
	

	$scope.getEnquiryall=function(){
		
		
	
		$http.post("rs/getallenquiry",$scope.formdata).then(function(value) {
			
		
			$scope.getallenquiry=value.data;
			console.log($scope.getallenquiry);
			
			
				
			}, function(reason) {
				
			}, function(value) {
				$window.alert("dfj");
				
			})
		
	
			
			
		}
	
	
	$scope.converttocase=function(x){
		
		$scope.formdata.convertcaseid=x;
		
		  var txt;
		  var r = confirm("Confirm to convert to case !");
		  if (r == true) {
			 
			  
				$http.post("rs/converttocase",$scope.formdata).then(function(value) {
					
					
					alert("successfully converted");
					
					location.reload();
					
				}, function(reason) {
					
				}, function(value) {
					$window.alert("dfj");
					
				})
			  
			  
		  } else {
			  $(this).remove();
		  }
		  /*window.location = '#!viewdrafts';*/
		 
		
		
	}
	
	$scope.findrowscheck=function(){
		
		
		//alert(document.getElementById("checklogsselect").value);
		
			
			$scope.formdata.searchbytype=document.getElementById("checklogsselect").value;
			
		
		$scope.formdata.fromdate=document.getElementById("from").value;
		$scope.formdata.todate=document.getElementById("to").value;
		$scope.formdata.crmname=document.getElementById("crmnameforsearch").value;
		$scope.formdata.doctorname=document.getElementById("doctornameforsearch").value;
		$scope.formdata.loginuser=$scope.user;
		$scope.formdata.city=document.getElementById("cityforsearch").value;

		
		$scope.formdata.searchby1=[document.getElementById("filterby1").value,document.getElementById("sign1").value,document.getElementById("value1").value];;
		$scope.formdata.searchby2=[document.getElementById("filterby2").value,document.getElementById("sign2").value,document.getElementById("value2").value];;
		$scope.formdata.andor=document.getElementById("andor1").value;
		
		
		var query="";
		if(document.getElementById("filterby1").value!=""){
			
			query=query+document.getElementById("filterby1").value+document.getElementById("sign1").value+document.getElementById("value1").value;
			
			if(document.getElementById("filterby2").value!=""){
				
				query=query+" "+document.getElementById("andor1").value+" ";
				
			}
			
		}
	if(document.getElementById("filterby2").value!=""){
			
			query=query+document.getElementById("filterby2").value+document.getElementById("sign2").value+document.getElementById("value2").value;
			
			if(document.getElementById("filterby3").value!=""){
				
				query=query+" "+document.getElementById("andor2").value+" ";
				
			}
			
		}
	if(document.getElementById("filterby3").value!=""){
		
		query=query+document.getElementById("filterby3").value+document.getElementById("sign3").value+document.getElementById("value3").value;
		
	
	}
	$scope.formdata.query=query;
	
	
	alert(query);
		
		$http.post("rs/findrowscheck",$scope.formdata).then(function(value) {
			
			$scope.previousdates=value.data;
			console.log($scope.previousdates);
			
			
				
			}, function(reason) {
				
			}, function(value) {
				$window.alert("dfj");
				
			})
		
	
			
			
		}
	
	
	
	
	
	$scope.getdoctors=function(){
		
		
	
		$http.post("rs/getdoctors",$scope.formdata).then(function(value) {
			
			$scope.alldoctors=value.data;
			console.log($scope.alldoctors);
			
			
				
			}, function(reason) {
				
			}, function(value) {
				$window.alert("dfj");
				
			})
		
		
			
			
		}
	
	
	

	
	$scope.getcrm=function(){
		
		
	
		$http.post("rs/getcrm",$scope.formdata).then(function(value) {
			
			$scope.allcrms=value.data;
			console.log($scope.allcrms);
			
			
				
			}, function(reason) {
				
			}, function(value) {
				$window.alert("dfj");
				
			})
		
		
			
			
		}
	
	
	
	
	
	
	$scope.clearfilter=function(){
		
		location.reload();
		
		
	}
	
	
	
	
	$scope.addcrmdata=function(){
		
		//alert(document.getElementById("mydate").value);
		
		$scope.formdata.maindate=document.getElementById("maindate").value;
		
		$scope.formdata.expecteddate=document.getElementById("expecteddate").value;
		$scope.formdata.actualdate=document.getElementById("actualdate").value;
		$scope.formdata.timefrom=document.getElementById("timefrom").value;
		$scope.formdata.timeto=document.getElementById("timeto").value;
		$scope.formdata.typeofdoctor=document.getElementById("typeofdoctor1").value;
		
	//	alert($scope.formdata.typeofdoctor);
		
		//alert(JSON.stringify($scope.formdata));
		
		$.ajax({
			

		    type : 'POST',
			data : {x:JSON.stringify($scope.formdata)},
			
			url : 'addcrmdata1',
			success : function(responseText) {
				alert(responseText);
				location.reload();
	
			}

		  
			 
		});
		
			
	}
	
	$scope.orderdata={};
	$scope.rem={};	

	$scope.showmodal=function(x){
		
		//alert(x.status);
		$scope.orderdata=x;
		
		$('#fileuploadedmodal').modal('show');
		
	}
	$scope.showmodal2=function(x){
		
		//alert(x.status);
		$scope.dis=x;
		
		$('#fileuploadedmodal2').modal('show');
		
	}
	
$scope.showmodal1=function(x){
		
		//alert(x.status);
		$scope.rem=x;
		
		$('#fileuploadedmodal1').modal('show');
		
	}

$scope.getcooperatenames=function(){
	
	

	$http.post("rs/getcooperatename",$scope.formdata).then(function(value) {
		
		$scope.cooperatenames=value.data;
		console.log($scope.previousdates);
		
		
		
			
		}, function(reason) {
			
		}, function(value) {
			$window.alert("dfj");
			
		})

	
	
}





$scope.getdoctorbytype=function(x){
	console.log(x);
	//alert("jdvjhf");

	

	$http.post("rs/getdoctorbytype",x).then(function(value) {
			$scope.doctorbytype=value.data;
		//console.log($scope.previousdates);
		
		
		
			
		}, function(reason) {
			
		}, function(value) {
			$window.alert("dfj");
			
		})

	
	
}

$scope.getcitiesfromdoctor=function(x){
	console.log(x);
	//alert(x);

	

	$http.post("rs/getcitiesfromdoctor",x).then(function(value) {
		//alert(typeof value.city\);
		$scope.citysss=value.data;
		//console.log($scope.previousdates);
		
		}, function(reason) {
			
		}, function(value) {
			$window.alert("dfj");
			
		})

	
	
}

$scope.forgetPassword=function(){
	$http.post("rs/forgetPassword", JSON.stringify($scope.forgetPasswordJson)).then(function(value) {
		alert("Password Changed");
		window.location.reload();
	}, function(value) {
		alert("wrong user name or mobile number");
		window.location.reload();
	})
}

$scope.addenquirydata=function(x){


	$scope.formdata.edoctorid=document.getElementById("edoctorid").value;
	$http.post("rs/addenquirydata",$scope.formdata).then(function(value) {
	
			
			alert("Enquiry Added");
		location.reload();
		
			
		}, function(reason) {
			
		}, function(value) {
			$window.alert("failed to add");
			
		})
	
		
	
		
	}






$scope.adddoctordata=function(x){
//alert(document.getElementById("formdatacooperatename").value);
	if(typeof $scope.formdata.typelist!='undefined'){
		
	if(typeof $scope.formdata.addclinicname=='undefined'){
		
		$scope.formdata.addclinicname="";
		
	}
	
	if(typeof $scope.formdata.addphonenumber=='undefined'){
		
		$scope.formdata.addphonenumber="";
		
	}
		
		$scope.formdata.cooperatename=document.getElementById("formdatacooperatename").value;
		
	

$scope.formdata.dateofbirth=document.getElementById("doctordob").value;
	
	//alert($scope.formdata.addaddress);
	
	
	$http.post("rs/adddoctordata",$scope.formdata).then(function(value) {
	
			
			alert("doctor added");
		location.reload();
		
			
		}, function(reason) {
			
		}, function(value) {
			$window.alert("failed to add");
			
		})
	
		
	
	}	
	else{
		alert("Please Select Type");
	}
	}

















$scope.mrlogid=null;
$scope.deletemrlog=function(x){
	
	//alert(x.status);
	$scope.id=x;
	

	$http.post("rs/deletemrlog",$scope.id.mrlogid).then(function(value) {
		
		//alert("deleted");
		location.reload();
		
		
			
		}, function(reason) {
			
		}, function(value) {
			$window.alert("dfj");
			
		})


}
	
	
	$scope.fetchpreviousdatesforuser=function(){
		
		$scope.formdata.crmname=document.getElementById("ccrmnameforsearch").value;
		
		$scope.formdata.fromdate=document.getElementById("cfrom").value;
		$scope.formdata.todate=document.getElementById("cto").value;
		$scope.formdata.cityforsearch=document.getElementById("ccityforsearch1").value;
		//console.log($scope.formdata.cityforsearch);
		$scope.formdata.doctornameforsearch1=document.getElementById("cdoctornameforsearch1").value;
		
//	alert(typeof $scope.username);
		$http.post("rs/fetchmrlogs",$scope.formdata).then(function(value) {
			
			console.log(value.data);
			$scope.previousdates=value.data;
			console.log($scope.previousdates);
			
				
			}, function(reason) {
				
			}, function(value) {
				$window.alert("dfj");
				
			})
		
		
		
		
	}
	
	
	
			
			
			
		})
		
		
	function getdoctorbytyp(){
	
	//alert("first one");
	  angular.element(document.getElementById('amitid')).scope().getdoctorbytype(document.getElementById('typeofdoctor1').value);

	
	
}	

function getcitiesfromdoctor(){
	
	//alert("first one");
	  angular.element(document.getElementById('amitid')).scope().getcitiesfromdoctor(document.getElementById('selectdoctorname').value);

	
	
}	
function fun(){
	
	 $( "#datepicker-1" ).datepicker();
	
	
	
}
