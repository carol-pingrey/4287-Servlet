<?php
error_reporting(E_ALL);
ini_set("display_errors", 1);

$host = "localhost";
$user = "root";
$pass = "";
$db   = "parc_info";

$idOrdi = $_POST["idOrdi"];
$idEtat = $_POST["idEtat"];
$idPanne = $_POST["idPanne"];
$date = $_POST["date"];
$observation = $_POST["observation"];
$connecte = $_POST["connecte"];

if (empty($connecte)) {
    echo "erreur : acces refuse (utilisateur non connecte)";
    exit;
}

$conn = new mysqli($host, $user, $pass, $db);

if ($conn->connect_error) {
    echo "erreur connexion : " . $conn->connect_error;
    exit;
}

if ($idPanne == "") {
    $idPanneSql = "NULL";
} else {
    $idPanneSql = "'" . $idPanne . "'";
}

$sql = "INSERT INTO ordinateur_etat (id_ordi, id_etat, id_panne, date, observation) VALUES ('$idOrdi', '$idEtat', $idPanneSql, '$date', '$observation')";

if ($conn->query($sql) === TRUE) {
    echo "ok";
} else {
    echo "erreur insertion : " . $conn->error;
}

$conn->close();
?>
