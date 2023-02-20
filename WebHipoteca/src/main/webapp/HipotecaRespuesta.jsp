<!DOCTYPE html>
<%@page import="java.util.List"%>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <link rel="stylesheet" href="css/estiloRespuesta.css" />
    <title>Hipoteca</title>
  </head>
  <body>
    <div class="container">
      <h1>Hipoteca</h1>
      <p>La cuota mensual es: ${cuota}</p>
      <p>
        El cálculo realizado es el siguiente: <br />
        Capital : ${capital} <br />
        Interes : ${interes} <br />
        Plazo : ${plazo} <br />
      </p>
      <button onclick="verCuadro()">Calcular Cuadro amortizacion</button>
    </div>
    <div id="amortizacion" style="display: none">
      <h1>Tabla amortizacion</h1>
      <table id="tabla-amortizacion">
        <thead>
          <tr>
            <th>Mes</th>
            <th>Cuota Mensual</th>
            <th>Amortizacion</th>
            <th>Intereses</th>
            <th>Capital Pendiente</th>
          </tr>
        </thead>
        <tbody>
          <% 
          List<List<Double>> cuadroAmortizacion = (List<List<Double>>) request.getAttribute("amortizacion");
          for (int i = 0; i < cuadroAmortizacion.size(); i++) {
              out.println("<tr>");
              out.println("<td>" + cuadroAmortizacion.get(i).get(0) + "</td>");
              out.println("<td>" + cuadroAmortizacion.get(i).get(1) + "</td>");
              out.println("<td>" + cuadroAmortizacion.get(i).get(2) + "</td>");
              out.println("<td>" + cuadroAmortizacion.get(i).get(3) + "</td>");
              out.println("<td>" + cuadroAmortizacion.get(i).get(4) + "</td>");
              out.println("</tr>");
            }
          %>
        </tbody>
      </table>
    </div>

    <p>
      acorde al prestamo que te puedes permitir , te recomendamos la siguiente
      casa:
    </p>
    <img
      src="https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fopwiki.org%2Fimages%2FFranky_Haus2.jpg&f=1&nofb=1&ipt=eb4848bb2b4aeb82f160664747ee94438ff75081d18864a8c5a0db63494b93ba&ipo=images"
    />
    <script type="text/javascript">
      function verCuadro() {
        document
          .getElementById("amortizacion")
          .setAttribute("style", "display: block");
      }
      this.addEventListener("load",function(){
          [].forEach.call(document.getElementsByTagName("td"),function(element){
            
            element.textContent = parseFloat(element.textContent).toFixed(2).replace(".",",")
             })
          })
    </script>
  </body>
</html>
