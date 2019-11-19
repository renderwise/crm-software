<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>

<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>CRM Login Portal</title>


<!--  <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
 --> <link rel="stylesheet" type="text/css" href="CSS/viewslider.css">
 <link rel="stylesheet" type="text/css" href="CSS/scrollbar.css">
  <script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
      <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
      
  <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
         rel = "stylesheet">
      

<script type="text/javascript" src="tableToExcel.js" ></script>

<script type="text/javascript" src="script/angular.min.js" ></script>
<script type="text/javascript" src="script/ng-file-upload.min.js" ></script>
<script type="text/javascript" src="script/ng-file-upload-shim.min.js" ></script>
<script type="text/javascript" src="script/FileSaver.js" ></script>
<script src="script/angular-route.js"></script>
 <script src="script/angular-animate.js"></script>

<script src="https://code.jquery.com/jquery-3.1.1.js" > </script>
<script type="text/javascript" src="script/angularapp.js"></script>
<script type="text/javascript" src="script/moment.js" ></script>
<script type="text/javascript" src="script/controller.js"></script>
<link href="CSS/font-awesome.css" rel="stylesheet" />
<!-- <script src="script/tether.js"></script>
<script src="script/bootstrap.js"></script>
 -->
 <link rel="stylesheet" href="CSS/bootstrap.css">
 <link rel="stylesheet" type="text/css" href="CSS/style.css">
 <link rel="icon" type="image/ico" href="images/favicon.ico" sizes="32x32" />
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

  <script src="scripts/bootstrap.min.js"></script>

    



<style type="text/css">
.dropdown:hover>.dropdown-menu {
  display: block;
}







</style>

<style>
body {
  
  transition: background-color .5s;
}

.sidenav {
  height: 100%;
  width: 0;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: #225f9e;
  overflow-x: hidden;
  transition: 0.5s;
  padding-top: 60px;
}

.sidenav a {
  padding: 8px 8px 8px 32px;
  text-decoration: none;
  font-size: 25px;
  color: #818181;
  display: block;
  transition: 0.3s;
}

.sidenav a:hover {
  color: #f1f1f1;
}

.sidenav .closebtn {
  position: absolute;
  top: 0;
  right: 25px;
  font-size: 36px;
  margin-left: 50px;
}

#main {
  transition: margin-left .5s;
  padding: 16px;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
</style>





</head>
<body id="amitid" ng-app="myapp" ng-controller="cont" ng-init="initiate()">
<% 

if(session.getAttribute("password")==null||session.getAttribute("username")==null){
	response.sendRedirect("login.jsp");
	
	
} 


else{
	JSONObject jobj= (JSONObject) session.getAttribute("userjson");
	
	out.println("<div ng-init=\"user='"+session.getAttribute("username")+"'; formdata.user=user; formdata.crmname='"+session.getAttribute("name")+"';formdata.loginid='"+session.getAttribute("loginid")+"'; formdata.PhoneNumber='"+jobj.get("phoneNumber")+"'; formdata.typeofuser='"+session.getAttribute("typeofuser")+"' \" >  </div>");
	out.println("<div ng-init=\"password='"+session.getAttribute("password")+"'; formdata.password=password; \" >  </div>");
}

System.out.println("this is for admin button check "+session.getAttribute("typeofuser"));
System.out.println("this is for admin button check "+session.getAttribute("name"));

String typeofuser=(String)session.getAttribute("typeofuser");

%>




<!-- <nav class="navbar navbar-toggleable-md navbar-inverse bg-inverse"  style="box-shadow: 0px 3px 5px gray; " >
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <a class="navbar-brand col-lg-2 col-4" href="#!"  ng-click="newtabclicked();" ><img alt="" src="http://alignwisesmile.com/images/logo.png" width="90%"> </a>
  
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav ml-auto" >
    
    <li class="nav-item">
 	 <a class="nav-link" style="color:white;"  data-toggle="collapse" data-target="#navbarNav" ng-click="showlatestcase()" href="" role="tab"  >Latest Case</a>
  	</li>
    	<li class="nav-item">
 	 <a class="nav-link" style="color:white;"  data-toggle="collapse" data-target="#navbarNav" href="#!contactus" role="tab"  >Contact Us</a>
  	</li>
  	<li class="nav-item">
 	 <a class="nav-link" style="color:white;"  data-toggle="collapse"  data-target="#navbarNav" href="#!viewprevious" role="tab"  >View previous</a>
  	</li>
  	<li class="nav-item">
 	 <a class="nav-link" style="color:white;"  data-toggle="collapse" data-target="#navbarNav" href="#!samples" role="tab"  >View Samples</a>
  	</li>
  	<li class="nav-item">
 	 <a class="nav-link" style="color:white;"  data-toggle="collapse"  data-target="#navbarNav" href="#!viewtreatment" role="tab"  >View Treatment</a>
  	</li>
    	<li class="nav-item">
 	 <a class="nav-link" style="color:white;"  data-toggle="collapse"  data-target="#navbarNav" href="#!viewdrafts" role="tab"  >View drafts</a>
  	</li>
 	 <li class="nav-item">
   	 <a class="nav-link" style="color:white;" data-toggle="collapse" data-target="#navbarNav" href="#!"  ng-click="newtabclicked();" >Generate new form</a>
  	</li>
    	<li class="nav-item">
		   <div class="dropdown nav-link">
		  <a class=" dropdown-toggle" id="dropdownMenuButton" data-toggle="dropdown" style="color:white;" >
		    Welcome {{user}}
		  </a>
		  <div class="dropdown-menu dropdown-menu-right" >
		    <a class="dropdown-item" href="index.jsp">Logout</a>
		    <a class="dropdown-item " data-toggle="collapse" data-target="#navbarNav" href="#!changepassword">Change Password</a>
			<a class="dropdown-item"   href="#!contactus"  >Contact Us</a>
		  </div>
		</div>
    	
       
      </li>
    </ul>
     
   
  </div>
</nav>
 -->

<nav class="navbar navbar-inverse digiplan-header">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbarNav">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button>
      <a class="navbar-brand" href="#!"  ng-click="newtabclicked();" ><img alt="" src="http://alignwisesmile.com/images/logo.png" style="width:70%"> </a>
    </div>
    <div class="collapse navbar-collapse" id="navbarNav" style="    padding-top: 5px;">
      
      <ul class="nav navbar-nav navbar-right">
      <%if(typeofuser.equals("crm")){ %>
       <li class="">
 	 <a class=""   data-toggle="collapse" data-target="#navbarNav" href="#!addmrlog" role="tab"  >Add MRLOG</a>
 	</li>
 	 <li class="">
 	 <a class=""   data-toggle="collapse" data-target="#navbarNav" href="#!addenquiry" role="tab"  >Add ENQUIRY</a>
 	</li>
 	<%} %>
      
    
        <%if(typeofuser.equals("admin")){ %>
       <li class="">
 	 <a class=""   data-toggle="collapse" data-target="#navbarNav" href="#!checklogs" role="tab"  >Check Enquiries</a>
 	</li>
 	 <li class="">
 	 <a class=""   data-toggle="collapse" data-target="#navbarNav" href="#!checkmrlogs" role="tab"  >Check Mr Logs</a>
 	</li>
 	<%} %>
 	  <%if(typeofuser.equals("crm")){ %>
        <li class="">
 	 <a class="" data-toggle="collapse" data-target="#navbarNav" ng-click="showlatestcase()" href="#!adddoctor" role="tab"  >Add Doctor</a>
 	</li>
 		<%} %>
 	 <%if(typeofuser.equals("crm")){ %>
    	<li class="nav-item">
 	 <a class="nav-link" data-toggle="collapse" data-target="#navbarNav" href="#!viewprevious" role="tab"  >View Previous</a>
  	</li>
	<li class="nav-item">
 	 <a class="nav-link" data-toggle="collapse" data-target="#navbarNav" href="#!caseconverted" role="tab"  >Case Converted</a>
  	</li>
		<%} %>
  	<li class="">
 	 <a class="" data-toggle="collapse"  data-target="#navbarNav" href="#!viewprevious" role="tab"  >Query</a>
  	</li>
  	 <%if(typeofuser.equals("crm")){ %>
  	<li class="">
 	 <a class="" data-toggle="collapse" data-target="#navbarNav" href="#!samples" role="tab"  >View Notification</a>
  	</li>
  	<%} %>
    	
    	
        <%if(typeofuser.equals("admin")){ %>
    	<li class="">
 	 <a class="" data-toggle="collapse" data-target="#navbarNav" href="#!createnoti" role="tab"  >Create Notification</a>
  	</li>
  	<%} %>
    	
       <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href=""> Welcome {{user}} <i class="fa fa-angle-down" aria-hidden="true"></i></a>
         	
         	
         	<ul class="dropdown-menu">
         	<li> <a class="" href="login.jsp">Logout</a> </li>
		    <li><a class=" " data-toggle="collapse" data-target="#navbarNav" href="#!changepassword">Change Password</a> </li>
			<li><a class=""   href="#!contactus"  >Contact Us</a> </li>
		
			
          </ul>
         	
         	
         	
         
        </li>
      
      
      </ul>
    </div>
  </div>
</nav>

<!--
<header id="header">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-2">
                    <div class="header-logo">
                        <a href="index.html">
                            <img src="images/logo.png" alt="32 Watts Clear Aligners" />
                        </a>
                    </div>
                </div>
                <div class="col-sm-10">
                    <nav id="header-nav-wrap">
                        <ul class="header-main-nav">
                            <li>
                                <a class="" data-toggle="collapse" data-target="#navbarNav" ng-click="showlatestcase()" href="" role="tab">Latest Case</a>
                            </li>
                            <li>
                                <a class="nav-link" data-toggle="collapse" data-target="#navbarNav" href="#!contactus" role="tab">Contact Us</a>
                            </li>
                            <li>
                                <a class="" data-toggle="collapse"  data-target="#navbarNav" href="#!viewprevious" role="tab"  >View previous</a>
                            </li>
                            <li>
                                <a class="" data-toggle="collapse" data-target="#navbarNav" href="#!samples" role="tab"  >View Samples</a>
                            </li>
							<li>
								<a class="" data-toggle="collapse"  data-target="#navbarNav" href="#!viewtreatment" role="tab"  >View Treatment</a>
							</li>
							<li>
								<a class="" data-toggle="collapse"  data-target="#navbarNav" href="#!viewdrafts" role="tab"  >View drafts</a>
							</li>
							<li>
								<a class="" data-toggle="collapse" data-target="#navbarNav" href="#!"  ng-click="newtabclicked();" >Generate new form</a>
							</li>
							
                        </ul>
                    </nav>
                    <a class="header-menu-toggle" href="#">
                        <span>Menu</span>
                    </a>
                </div>
            </div>
        </div>
    </header>
-->


<div class="container">
<ng-view class="slide"></ng-view>
</div>



<!-- Modal -->
 
 <div class="modal fade" id="fileuploadedmodal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
        <h4 class="modal-title">Order Details</h4>
       
       			<div><h2>Order value    : {{orderdata.ordervalue}}</h2></h4></div>
       			<div><h2>Order quantity : {{orderdata.orderquantity}}</h2></h4></div>
       			<div><h2>Order booked   : {{orderdata.orderbooked}}</h2></h4></div>
       
       
      </div>
      
    </div>
  </div>

  
   <div class="modal fade" id="nofilemodal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        
        <div class="modal-body"   >
      
			
		
			<label><b><h4 style="">NO FILE EXISTS.</h4> </b> </label>
			</div>
           
        
        </div>
      </div>
    </div>
  </div>
  
  
  
 <div class="modal fade" id="fileuploadedmodal1" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
        <h4 class="modal-title">Order Details</h4>
       
       			<div><h2>remarks  : <textarea type="text">{{rem.remarks}}</textarea></h2></h4></div>
       			
       
      </div>
      
    </div>
  </div>

  
   <div class="modal fade" id="nofilemodal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        
        <div class="modal-body"   >
      
			
		
			<label><b><h4 style="">NO FILE EXISTS.</h4> </b> </label>
			</div>
           
        
        </div>
      </div>
    </div>
  </div>
  
  
  
  
  
  
  
   <div class="modal fade" id="fileuploadedmodal2" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
        <h4 class="modal-title">DISCUSSION</h4>
       
       			<div><h2>Discussion  : <textarea type="text">{{dis.discussion}}</textarea></h2></h4></div>
       			
       
      </div>
      
    </div>
  </div>

  
   <div class="modal fade" id="nofilemodal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        
        <div class="modal-body"   >
      
			
		
			<label><b><h4 style="">NO FILE EXISTS.</h4> </b> </label>
			</div>
           
        
        </div>
      </div>
    </div>
  </div>
  
  
  
  
  
  
  

<footer>
        <div class="container">
            <div class="f-logo">
                <img src="images/logo-footer.jpg" alt="32 watts clear aligner" />
            </div>
            <div class="footer-links">
                <ul>
                    <li>
                        <a href="http://www.32watts.com/">Patient</a>
                    </li>
                    <li>
                        <a href="http://www.32watts.com/orthodontist.html">Orthodontists</a>
                    </li>
                    <li>
                        <a href="http://www.32watts.com/about.html">About</a>
                    </li>
                    <li>
                        <a href="https://blog.32watts.com/ target="blank" title="Blog">Blog</a>
                    </li>
                </ul>
            </div>
            <div class="copyright">
                <span>32 Watts</span>&copy; 2019 Alignwise Smile Technologies. All Rights Reserved
            </div>
            <div class="footer-social">
                <ul>
                    <li>
                        <a href="https://www.facebook.com/32watts">
                            <i class="fa fa-facebook" aria-hidden="true"></i>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.linkedin.com/company/alignwise-smile-technologies/">
                            <i class="fa fa-linkedin" aria-hidden="true"></i>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.instagram.com/alignwise_smile/">
                            <i class="fa fa-instagram" aria-hidden="true"></i>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.youtube.com/channel/UC2Xgt_zC4ZWvKISMzzwmMbg">
                            <i class="fa fa-youtube-play" aria-hidden="true"></i>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </footer>

</body>
</html>