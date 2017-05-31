package view;

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.WebALC;

/**
 * Servlet implementation class VisualizarPontos
 */
@WebServlet("/VisualizarPontos")
public class VisualizarPontos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizarPontos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String amenity = request.getParameter("amenity");
		salvar(nome, email);
		PrintWriter out = response.getWriter();
		out.println("<html>");
	    out.println("<head>");
	    out.println("<meta charset=\"UTF-8\">");
	    out.println("<title>Localização Ideal no Espaço Euclidiano</title>");
	    out.println("</head>");
	    out.println("<h1>Localização Ideal no Espaço Euclidiano</h1>");
	    out.println("<body align=\"justify\">");
	    out.println("Entre esses três locais candidatos qual você acha melhor para instalar um(a) novo(a)");
	    out.println(amenity+":");
	    out.print("<form action=\"VisualizarPontos\" method=\"POST\">");
	    out.println("<input type=\"radio\" name=\"algoritmo\" value=\"0\" checked> ponto 1<br>");
	    out.println("<input type=\"radio\" name=\"algoritmo\" value=\"1\"> ponto 2<br>");
	    out.println("<input type=\"radio\" name=\"algoritmo\" value=\"2\"> ponto 3");
	    out.println("<input type=\"submit\" value=\"gravar\">");
	    out.println("</form>");
	    out.println("</body>");
	    out.println("</html>");
	    
	    out.println(request.getParameter("amenity"));
	}
	
	private void salvar(String nome, String email){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:sqlite:"+WebALC.BANCO_DE_DADOS);
			java.sql.Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			statement.executeUpdate("insert into pessoa values(" + nome + ",'" + email + "')");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
