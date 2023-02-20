<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/estilos.css" />
    <title>Hipoteca</title>
  </head>
<body>
  <div class="container">
    <h1>Hipoteca</h1>
	  <form action="HipotecaController" method="post">
      <label for="capital">Capital</label>
      <input type="text" name="capital" id="capital" />
      <br />
      <label for="interes">Interes</label>
      <input type="text" name="interes" id="interes" />
      <br />
      <label for="plazo">Plazo mensual</label>
      <input type="text" name="plazo" id="plazo" />
      <br />
      <input type="submit" value="Calcular" />
    </form>
  </div>
</body>
</html>
