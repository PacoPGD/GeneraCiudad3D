import java.io.FileWriter;
import java.io.PrintWriter;


public class main {

	//Variables declaradas como globales para no tener que pasar todas ellas mediante 
	//las funciones
	public static int callesHorizontal;
	public static int callesVertical;
	public static double calleAnchoMin;
	public static double calleAnchoMax;
	public static double calleSeparacionMin;
	public static double calleSeparacionMax;
	


	/***************************************************
	Funcion principal del programa
	*************************************************/
	public static void main(String[] args)
	{
<<<<<<< HEAD
		pedirDatos();

	    guardaFichero();
=======
		guardaFichero();
>>>>>>> haciendoCalles
=======
		System.out.println("Proyecto iniciado");
>>>>>>> parent of 47f3802... FunciÃ³n bÃ¡sica para pedir datos
	}

<<<<<<< HEAD
	/***************************************************
	La funcion guarda pedirDatos es la encargada de pedir al usuario la 
	información para generar la ciudad
	
	*************************************************/
	public static void pedirDatos()
	{
		int callesHorizontal;
		int callesVertical;
		
		callesHorizontal = Integer.parseInt( JOptionPane.showInputDialog(
			null,"Introduzca calles en horizontal","GeneraCiudad3D",
	    	JOptionPane.QUESTION_MESSAGE) );
	    	 
		callesVertical = Integer.parseInt( JOptionPane.showInputDialog(
			null,"Introduzca calles en vertical","GeneraCiudad3D",
		    JOptionPane.QUESTION_MESSAGE) );
		
<<<<<<< HEAD
=======
		calleAnchoMin = Double.parseDouble( JOptionPane.showInputDialog(
			null,"Introduzca minimo de ancho de la calle en metros","GeneraCiudad3D",
		    JOptionPane.QUESTION_MESSAGE) );
		    	 
		calleAnchoMax = Double.parseDouble( JOptionPane.showInputDialog(
			null,"Introduzca maximo de ancho de la calle en metros","GeneraCiudad3D",
			JOptionPane.QUESTION_MESSAGE) );
		
		calleSeparacionMin = Double.parseDouble( JOptionPane.showInputDialog(
			null,"Introduzca minimo de separacion entre calles en metros","GeneraCiudad3D",
			JOptionPane.QUESTION_MESSAGE) );
			    	 
		calleSeparacionMax = Double.parseDouble( JOptionPane.showInputDialog(
			null,"Introduzca maximo de separacion entre calles en metros","GeneraCiudad3D",
			JOptionPane.QUESTION_MESSAGE) );	
>>>>>>> haciendoCalles
	}
	
=======
>>>>>>> parent of 47f3802... FunciÃ³n bÃ¡sica para pedir datos
	
	/***************************************************
	La funcion guarda fichero recoge toda la información del programa
	y la transforma en un fichero wrl/x3d que después podrá ser visualizado
	por el visor 3D
	
	*************************************************/
	public void guardaFichero()
	{
		FileWriter fichero = null; 
		PrintWriter pw = null; 
		try
		{
			fichero = new FileWriter("fichero.wrl"); //URL del fichero generado
			pw = new PrintWriter(fichero);
  
			//Escritura del fichero
            escribeFichero(pw);
        } 
		catch (Exception e) //No se ha podido escribir el fichero
        {
            e.printStackTrace(); //Devolvemos excepción
        } 
		finally //Se cierra el fichero
		{
			try
			{
				if (null != fichero)
					fichero.close(); //Fichero cerrado
			} 
			catch (Exception e2)
			{
				e2.printStackTrace(); //Excepción, no se pudo cerrar el fichero
           	}
        }
	
	}
	
	/***************************************************
	La funcion preparaDistancias guarda todas las distancias entre calles y sus anchos para despues
	pasarlas a la función que escribe el fichero
	
	*************************************************/
	public static void preparaDistancias(double [] anchoCalleH, double [] separacionCalleH,double [] anchoCalleV, double [] separacionCalleV)
	{
		int i,j;

		for(i=0;i<callesHorizontal;i++)
		{
			anchoCalleH[i]=0.;
			separacionCalleH[i]=0;
		}
		
		for(i=0;i<callesVertical;i++)
		{
			anchoCalleV[i]=0.;
			separacionCalleV[i]=0;
		}
		
		for(i=0;i<callesHorizontal;i++)
		{
			anchoCalleH[i]= Math.random()*(calleAnchoMax-calleAnchoMin) + calleAnchoMin;
			
			separacionCalleH[i]= Math.random()*(calleSeparacionMax-calleSeparacionMin) 
								+ calleSeparacionMin+anchoCalleH[i];
			if(i>0)
			{
				separacionCalleH[i]=separacionCalleH[i]+separacionCalleH[i-1];
			}

			 //System.out.println ("ancho de "+i+"= "+anchoCalle[i]);
			// System.out.println ("separacion de "+i+"= "+separacionCalle[i]);
		}	
		
		for(i=0;i<callesVertical;i++)
		{
			anchoCalleV[i]= Math.random()*(calleAnchoMax-calleAnchoMin) + calleAnchoMin;
			
			separacionCalleV[i]= Math.random()*(calleSeparacionMax-calleSeparacionMin) 
								+ calleSeparacionMin;
			if(i>0)
			{
				separacionCalleV[i]=separacionCalleV[i]+separacionCalleV[i-1];
			}

		}	
	}
	
	
	/***************************************************
	La funcion escribeFichero escribe el fichero con los datos recogidos anteriormente
	
	*************************************************/
	public static void escribeFichero(PrintWriter pw)
	{
		double[] anchoCalleH = new double[callesHorizontal];
		double[] separacionCalleH = new double[callesHorizontal];

		double[] anchoCalleV = new double[callesVertical];
		double[] separacionCalleV = new double[callesVertical];
		
		int i;
		
		preparaDistancias(anchoCalleH,separacionCalleH,anchoCalleV,separacionCalleV);
		
		pw.println("#VRML V2.0 utf8");
		
		for(i=0;i<callesHorizontal;i++)
		{

			pw.println("DEF Calle Group {"
					+ "	children ["
					+ "		Transform{"
					+ "			translation " + separacionCalleH[i] +" 0 0"
					+ "			children["
					+ "				Shape"
					+ "				{"
					+ "					appearance Appearance"
					+ "					{"
					+ "						material Material"
					+ "						{"
					+ "							emissiveColor 50.0 0.0 0.0"
					+ "						}"
					+ "					}"
					+ "					geometry Box"
					+ "					{"
					+ "						size " + anchoCalleH[i]+" 0.1 "+ (separacionCalleV[callesVertical-1]-anchoCalleV[callesVertical-1])
					+ "					}"
					+ "				}"
					+ "			]"
					+ "		}"
					+ "	]"
					+ "}");
		}
	


		
	}
	
}
