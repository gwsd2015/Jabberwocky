<?php 
    session_start();
    //$test = $_SESSION['student'];
    $student = $_POST['student_id'];
    $courseNum = $_SESSION['course_id'];
	
	function createWorkspace(){
		$wksp = $_POST['lname'];
		$wksp = $wksp.$_POST['fname'];
		$wksp = $wksp.$_POST['cid'];
		#$output2 = shell_exec("mkdir /users/$wksp");
		exec("mkdir /users/".escapeshellarg($wksp),$output2);
		echo "<pre>$output2[0]</pre>";
		$output = shell_exec('ls -l /users');
		echo "<pre>$output</pre>";
		echo "Workspace $wksp";
	}	

	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		createWorkspace();
	}

    ?>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"  href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>   
<title>Student View</title>

</head>
<body>

    
<div class="page-header" align="center">
    <h1>Project Jabberwocky</h1>
    </div>
<div class="container">
<div class="jumbotron">
<form method="post" action="add_wrksp.php">
<table class="table table-striped">
    <tbody>
    <tr>
    <td>Student First Name:<input type="text" name="fname"></td> 
    </tr>
	<tr>
	<td>Student Last Name:<input type="text" name="lname"> </td>
	</tr>
	<tr><td>
	Student Id: <input type="text" name="sid">
	</td></tr>
	<tr><td>
	Class Id: <input type="text" name="cid"> 
	</td></tr>
	<tr><td>
	Workspace: <input type="text" name="wksp">
	</td></tr>
	<tr><td>
	<input type="submit" name="submit" value="Submit"></form>
	</td></tr>
    
<?php
include("database.php");

//Open Database connection
$dbc = mysql_connect($dbServer,$dbUser,$dbPass)
    or die("Error connecting to Mysql Server");

//$cid = 12
//Select the actual database
mysql_select_db($dbName,$dbc);

$query = "Select * FROM features inner join student_features on features.fid=student_features.fid where student_features.sid=$student and student_features.cid=$courseNum";
$res = mysql_query($query) or 
    die('Error querying database. Query is '.$query);

while($column = mysql_fetch_array($res)){
    
    
    
    echo "<tr>";
    echo "<td><div class='checkbox'>
      <label><input type='checkbox' value=''></label>
    </div></td>";
    echo "<td>".$column['featureName']."</td>";
    echo "<td>Enabled</td>";
    //echo "<td>Level ".$column['Level']."</td>";
    echo "</tr>";
}

$query = "Select featureName FROM features where fid not in (select FID from student_features where SID=$student)";
$res = mysql_query($query) or 
        die('Error querying database. Query is '.$query);
while($column = mysql_fetch_array($res)){
            
    echo "<tr>";
    echo "<td><div class='checkbox'><label>
    <input type='checkbox' value=''></label></div></td>";
    echo "<td>".$column['featureName']."</td>";
    echo "<td>Disabled</td>";
     //echo "<td>Level ".$column['Level']."</td>";
    echo "</tr>";
        }


?>
        
    </tbody>
    </table>
    </div>
    </div>
    </div>
</body>
</html>
