<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>


<button type="button" onclick="testajax()">click</button>

<script>



function testajax(){
	alert("dhfbj");
	
	$.ajax({
		

	    type : 'POST',
		data : {}, 
		url : 'sample',
		success : function(data) {
			
			
			
			
			
			
		    $.each(data.jsonArray, function(index) {
                $.each(data.jsonArray[index], function(key, value) {
                    var point = [];

                        point.push(key);
                        point.push(value);
                        receivedData.push(point);

                    }); 
            });
            alert(typeof recievedData)
            
			
			
			
			
			
			
			
			
		}

	  
		 
	});
	
	
	
	
	
}


</script>
</body>
</html>