package model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class WebALC {

	public static String CLIENTES;
	public static String OSM;
	
	private Point2D alc; 
	private Point2D minsum;
	private Point2D maxsum;
	
	public WebALC(String clientes, String osm) {
		
		CLIENTES = clientes;
		OSM = osm;
	}
	
	public void init(String amenity){
		Parser parser = new Parser();
		parser.init(OSM, amenity);
        parser.leClientes(CLIENTES);
        
        
        //pega quatro pontos randomicos
        ArrayList<Node> candidatos = new ArrayList<>();
        candidatos.add(parser.getNodes().get((new Random().nextInt(parser.getNodes().size() - 1))));
        candidatos.add(parser.getNodes().get((new Random().nextInt(parser.getNodes().size() - 1))));
        candidatos.add(parser.getNodes().get((new Random().nextInt(parser.getNodes().size() - 1))));
        candidatos.add(parser.getNodes().get((new Random().nextInt(parser.getNodes().size() - 1))));
        
        
		alc = ALC.getLC(candidatos, parser.getFacilities(), parser.getClientes());
		minsum = ALC.getMinSum(candidatos, parser.getFacilities(), parser.getClientes());
	}
	
	
}
