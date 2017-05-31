package view;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Inicio
 */
@WebServlet("/Inicio")
public class Inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String texto = "Você está participando de um estudo exeprimental onde terá que escolher"
			+ "o tipo de uma facility, dentre os tipos mais populares. Sejam eles: farmácias, academias, "
			+ "supermercados, restaurantes, clínicas."
			+ "No mapa será indicado três possíveis locais candidatos. Você deverá escolher dentre esses locais"
			+ "o que julga ser o local mais indicado para ser instalado uma nova facility, levando em consideração as facilities já existentes";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inicio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Localização Ideal no Espaço Euclidiano</title>");
	    out.println("</head>");
	    out.println("<h1>Localização Ideal no Espaço Euclidiano</h1>");
	    out.println("<body align=\"justify\">");
	    out.println(texto);
	    out.println("</body>");
	    out.println("</html>");
		//response.getWriter().append(texto).append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
