package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

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
		String db = getServletContext().getRealPath("WEB-INF/arquivos/database.db");
		int alg = Integer.parseInt(algoritmo);
		String minMaxEqualsMinSum = request.getParameter("minMaxEqualsMinSum");
		boolean equals = Boolean.getBoolean(minMaxEqualsMinSum);
		
		if(equals == true){
			if(alg == 2 || alg == 1){
				salvar("2", db);
				salvar("1", db);
			}
		} else {
			salvar(algoritmo, db);
		}
		
		
		//saber a de todas 
		salvar("3", db);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(minMaxEqualsMinSum);
try {	
			Class.forName("org.sqlite.JDBC");
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:sqlite:"+db);
			java.sql.Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			ResultSet rs = statement.executeQuery("select * from algoritmo");
			while (rs.next()) {
				out.print(rs.getString("algoritmo"));
				out.print(":");
				out.print(rs.getInt("pontos"));
				out.println();
			}
			
			connection.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		out.close();
		
	}

	
	private void salvar(String algoritmo, String db){
		try {
			
			Class.forName("org.sqlite.JDBC");
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:sqlite:"+db);
			java.sql.Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			ResultSet rs = statement.executeQuery("select * from algoritmo where id = " +algoritmo);
			int pontos = rs.getInt("pontos");
			pontos++;
			statement.execute("UPDATE algoritmo SET pontos ="+pontos+" WHERE ID ="+algoritmo);
			connection.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
