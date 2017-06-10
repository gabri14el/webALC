package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Concluido
 */
@WebServlet("/Concluido")
public class Concluido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Concluido() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = getServletContext().getRealPath("WEB-INF/arquivos/concluido.html");
		String algoritmo = request.getParameter("algoritmo");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(algoritmo);
	}

}
