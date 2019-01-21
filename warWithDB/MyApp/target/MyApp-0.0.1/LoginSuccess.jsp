<html>
<head>
<meta charset="utf-8"> 
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<title>Login Success</title> 
<script>
	function init() {
		document.getElementById("block1").style.display = "none";
		return false;
	}

	function disp1() {
		document.getElementById("block1").style.display = "";
	}

	
</script>
<link rel="stylesheet" href="css/style.css">
</head>
<body onLoad=" return init();">
  <section class="container">  
<div class="login"> 
<h1> Employees Database </h1>
<table style="width:300px">
<tbody>
<tr> 
<th>Firstname</th> 
<th>Lastname</th>
<th>Points</th>
</tr>
<tr>
<td>Jill</td> 
<td>Smith</td>
<td>50</td>
</tr>
<tr>
<td>Eve</td> 
<td>Jackson</td>
<td>94</td>
</tr>
<tr>
<td>John</td> 
<td>Doe</td>
<td>80</td>
</tr>
</tbody>
</table>
<div> 
<form name="actionForm" action="LoginServlet" method ="Post">
<table style="width: 300px" border="1">
			 <tbody>
				<tr>
					<td ><p class="submit">
						<input type="submit" name="commit" value="Get Inventory"></p></td>
						<td><p class="submit">
						<input type="button" id="idlist1" value="Add Car Details" onclick="disp1()" /></p></td>
					<td></td>
				</tr>
			 </tbody>
</table>
</form>
</div>  
</div> 
</section>
<form name="addForm" action="LoginServlet" method ="Post">
<section class="container">  
<div class="login" id="block1"> 
<h1> Add Car Details </h1>
<p>
<input type="text" name="manufacturer" value="" placeholder="Manufacturer :-"/>
</p>
<p>
<input type="text" name="carname" value="" placeholder="Car Name :-"/>
</p>
<p>
<input type="text" name="color" value="" placeholder="Colors available :-"/>
</p>
<p>
<input type="text" name="price" value="" placeholder="Price(INR) :-"/>
</p>
<p class="submit">
<input type="submit" name="add" value="Add"></p>
<p class="submit">
<input type="button" name="hide" value="hide" onclick="init()"></p>  
</div> 
</section>
</form>
</body>
</html>