<?php 
    session_start();
    $_SESSION['course_id'] = 123;
    $_SESSION['studentName'] = "Lucas Chaufournier";
    $_SESSION['studentEmail'] = "lucasch@gwu.edu";
    $_SESSION['SID'] = 1;
    $studentName = $_SESSION['studentName'];
    $studentEmail = $_SESSION['studentEmail'];
    $SID = $_SESSION['SID'];
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
        <?php
    echo "<b>Name:</b> $studentName"; 
    echo "<br>";
    echo "<b>Email:</b> $studentEmail";
    ?>
    </div>
<div class="jumbotron">
<table class="table table-striped">
<thead>
    <tr>
        <th>Course Number</th>
        <th>Course Title</th>
        <th>Instructor</th>
        <th>Workspace</th>
        <th>Status</th>
        <th>Action</th>
        <th>Enter</th>
        
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

$query = "select * from (select profile.ID, profile.SID, profile.dir,profile.dockinst,classes.classid,classes.classname,classes.instructor from profile Inner join classes on profile.CID = classes.classid) as A where A.SID=$SID";
$res = mysql_query($query) or 
    die('Error querying database. Query is '.$query);

while($column = mysql_fetch_array($res)){
    echo "<tr>";
    echo "<td>".$column['classid']."</td>";
    echo "<td>".$column['classname']."</td>";
    echo "<td>".$column['instructor']."</td>";
    echo "<td>".$column['dir']."</td>";
    echo "<td></td>";
    echo "<td></td>";
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