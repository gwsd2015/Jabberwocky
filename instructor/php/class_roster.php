<?php
    session_start();
    $_SESSION['course_id'] = 123;
?>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"  href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<title>Class Roster</title>

</head>
<body>
<div class="page-header" align="center">
    <h1>Project Jabberwocky</h1>
    </div>
<div class="container">
<center><ul class="pagination">
<li class="active"><a href="#">Students</a></li>
<li><a href="features.php">Features</a></li>
</ul></center>
<div class="jumbotron">
<table class="table table-striped">
<thead>
    <tr>
        <th>Select</th>
        <th>First Name</th>
        <th>Last Name</th>
        <!--<th>Level</th> -->
        <!--<th>Learning Value</th> -->
        <th>Workspace</th>
        <th>View</th>
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

$query = "Select * FROM class_students where CID=123";
$res = mysql_query($query) or
    die('Error querying database. Query is '.$query);

while($column = mysql_fetch_array($res)){
    $query = "Select LV,level from learning_data where sid=".$column['1']." and CID=123";
    $studentRes = mysql_query($query)or
        die('Error querying database. Query is '.$query);
    $studentData = mysql_fetch_array($studentRes);

    $query = "Select dir from profile where CID=123 and SID=".$column['SID'];
    $wkspRes = mysql_query($query)or
        die('Error querying databases. Query is '.$query);
    $studentWksp= mysql_fetch_array($wkspRes);


    echo "<tr>";
    echo "<td><div class='checkbox'>
      <label><input type='checkbox' value=''></label>
    </div></td>";
    echo "<td>".$column['FNAME']."</td>";
    echo "<td>".$column['LNAME']."</td>";
    //echo "<td>Level ".$studentData['level']."</td>";
    //echo "<td>".$studentData['LV']."</td>";
    echo "<td>".$studentWksp['dir']."</td>";
    echo "<td> <form action='student_view.php' method='post'><input type='hidden' value='".$column['SID']."' name='student_id'><input button type='submit' class='btn btn-info' name='username' value='View'></button></form></td>";
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
