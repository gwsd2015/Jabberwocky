<?php 
    session_start();
    //$test = $_SESSION['student'];
    $student = $_POST['student_id'];
    $courseNum = $_SESSION['course_id'];


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
<center><ul class="pagination">
<li><a href="class_roster.php">Students</a></li>
<li><a href="features.php">Features</a></li> 
</ul></center>
<div class="jumbotron">

<table class="table table-striped">
<thead>
    <tr>
        <th>Select</th>
        <th>Feature Name</th>
        <th>Enabled</th>
        <!--<th>Level</th> -->
    </tr>
    </thead>
    <tbody>
    
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