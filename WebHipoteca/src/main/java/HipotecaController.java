

import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HipotecaController
 */
@WebServlet("/HipotecaController")
public class HipotecaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HipotecaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("Hipoteca.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Double capital = Double.parseDouble(request.getParameter("capital"));
		Double interes = Double.parseDouble(request.getParameter("interes"));
		Double plazo = Double.parseDouble(request.getParameter("plazo"));
		
		Double cuota = calcularcuota(capital, interes, plazo);
		
		
		// hacer dos servlet uno para que guarde los datos a la base de datos y luego el segundo servlet lo que hace
		// es comprobar que se puede hacer el calculo , devuelve si puede hacer el calculo o no.
		request.setAttribute("cuota", cuota);
        request.setAttribute("capital", capital);
        request.setAttribute("interes", interes);
        request.setAttribute("plazo", plazo);
        request.setAttribute("amortizacion", calcularTablaAmortizacion(capital, interes, plazo, cuota));
        
		request.getRequestDispatcher("HipotecaRespuesta.jsp").forward(request, response);
	}
	
	private Double calcularcuota(Double P, Double i, Double n) {
	    double iMensual = (i*0.01) / 12.0; // Convertir el interés anual a mensual
	    double factor = 1.0 - Math.pow(1.0 + iMensual, -n);
	    double cuota = P / (factor / iMensual);
	    return cuota;
	}

	
    private ArrayList<ArrayList<Double>> calcularTablaAmortizacion(Double capital, Double interes,Double plazo,Double cuota){

		Double mesesDePago = plazo ; 
		Double pagarMes = calcularcuota(capital, interes,plazo);
	    ArrayList<ArrayList<Double>> tabla = new ArrayList<ArrayList<Double>>();
	    for (int i = 0; i <= mesesDePago; i++) {
	    	if(i == 0) {
	            ArrayList<Double> fila = new ArrayList<Double>();
	            fila.add(i/1.0);//mes
	            fila.add(0.0);//cuotamensual
	            fila.add(0.0);// amortizacion
	            fila.add(0.0);// intereses
	            fila.add(capital);//capitalPendiente
	            tabla.add(fila);
	            continue;
	    	}
            ArrayList<Double> fila = new ArrayList<Double>();
    		Double intereses = calcularIntereses(capital,interes);
            fila.add(i/1.0);//mes
            fila.add(pagarMes);//cuotamensual
            fila.add(pagarMes - intereses);// amortizacion
            fila.add(intereses);// intereses
            capital = capital - (pagarMes - intereses);
            fila.add(capital < 0 ? 0.00 : capital);//capitalPendiente
            tabla.add(fila);
            

        }
        return tabla;
	}

    private Double calcularIntereses(double capital, double interes) {
    	return capital * ((interes * 0.01) / 12);
       
    }

		
	
	

}
