package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sqlite.JDBC;
import org.sqlite.SQLiteJDBCLoader;

import model.WebALC;



/**
 * Servlet implementation class VisualizarPontos
 */
@WebServlet("/VisualizarPontos")
public class VisualizarPontos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String comeco;
	String fim; 
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
		
		
		//pega diretório dos arquivos
		//não funciona em windows por causa do separador 
		//lembrar de consertar depois 
		String path = getServletContext().getRealPath("WEB-INF/arquivos/");
		String db = getServletContext().getRealPath("WEB-INF/arquivos/database.db");
		String osm = getServletContext().getRealPath("WEB-INF/arquivos/osm.txt");
		String clientes = getServletContext().getRealPath("WEB-INF/arquivos/clientes.txt");
		salvar(nome, email, db);
		carregarHtml(path);
		WebALC alc = new WebALC(clientes, osm);
		alc.init(amenity);
		
		String marker_alc = "var marker = new google.maps.Marker({position: "
				+ "{lat:"+alc.getAlc().getX()+", lng:"+alc.getAlc().getY()+"},map: map, label: \"1\"});";
		String marker_minsum = "var marker = new google.maps.Marker({position: "
				+ "{lat:"+alc.getMinsum().getX()+", lng:"+alc.getMinsum().getY()+"},map: map, label: \"2\"});";
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(comeco);
		out.print(marker_alc);
		out.println(marker_minsum);
		//aqui vai ficar o código dos marcadores hehe
		out.print(fim);
		out.close();
	}
	
	
	private void carregarHtml(String path){
		 try {
			 BufferedReader comeco = new BufferedReader(new FileReader(path+"/visualizar_pontos.html"));
			 String str;
			 StringBuffer buf = new StringBuffer();
			 while (comeco.ready()) {
			  str = comeco.readLine();
			  buf.append(str);
			 }
			 comeco.close();
			 this.comeco = buf.toString();
			 
			 buf = new StringBuffer();
			 BufferedReader fim = new BufferedReader(new FileReader(path+"/visualizar_pontos_final.html"));
			 
			 buf = new StringBuffer();
			 while (fim.ready()) {
			  str = fim.readLine();
			  buf.append(str);
			 }
			 comeco.close();
			 this.fim = buf.toString();
			  } catch (IOException e) {
			 e.printStackTrace();
			 
			  }
	}
	private void salvar(String nome, String email, String db){
		try {
			
			Class.forName("org.sqlite.JDBC");
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:sqlite:"+db);
			java.sql.Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			statement.executeUpdate("insert into usuario values('" + nome + "','" + email + "')");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
