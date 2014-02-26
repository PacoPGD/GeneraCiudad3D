import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.JOptionPane;


public class main {

	static int tamanioCiudad;
	static int callesHorizontal;
	static int callesVertical;
	static int matriz[][];


	/***************************************************
	Funcion principal del programa
	*************************************************/
	public static void main(String[] args)
	{

		pedirDatos();
		construirMatriz();

		//guardaFichero();


	}

	/***************************************************
	La funcion guarda pedirDatos es la encargada de pedir al usuario la 
	informaci�n para generar la ciudad
	
	*************************************************/
	public static void pedirDatos()
	{
		tamanioCiudad = Integer.parseInt( JOptionPane.showInputDialog(
			null,"Introduzca tamanio ciudad en metros cuadrados","GeneraCiudad3D",
	    	JOptionPane.QUESTION_MESSAGE) );
		callesHorizontal = Integer.parseInt( JOptionPane.showInputDialog(
				null,"Introduzca calles en horizontal","GeneraCiudad3D",
		    	JOptionPane.QUESTION_MESSAGE) );
		callesVertical = Integer.parseInt( JOptionPane.showInputDialog(
				null,"Introduzca calles en vertical","GeneraCiudad3D",
		    	JOptionPane.QUESTION_MESSAGE) );
	}

	/***************************************************
	Esta funcion construye la matriz a partir de la que se reproducir� la ciudad
	
	*************************************************/
	public static void construirMatriz()
	{
		Random r=new Random();
		int i,j;
		int auxiliar;
		int bandera[]= new int [tamanioCiudad];
		
		matriz= new int[tamanioCiudad][tamanioCiudad];
		
		//INICIALIZACION DE VARIABLES A 0
		for(i=0;i<tamanioCiudad;i++)
		{
			for(j=0;j<tamanioCiudad;j++)
			{
				matriz[i][j]=0;
			}
			bandera[i]=0;
		}

		//INTRODUCIMOS LAS CALLES EN HORIZONTAL
		for(i=0;i<callesHorizontal;i++)
		{
			do
			{
			auxiliar=r.nextInt(tamanioCiudad);
			}while(bandera[auxiliar]==1);
				
			for(j=0;j<tamanioCiudad;j++)
			{
				matriz[auxiliar][j]++;
				bandera[auxiliar]=1;
			}
		}
		
		//REINICIALIZACION DE BANDERA A 0
		for(i=0;i<tamanioCiudad;i++)
		{
			bandera[i]=0;
		}
		
		//INTRODUCIMOS LAS CALLES EN VERTICAL
		for(i=0;i<callesVertical;i++)
		{
			do
			{
			auxiliar=r.nextInt(tamanioCiudad);
			}while(bandera[auxiliar]==1);
				
			for(j=0;j<tamanioCiudad;j++)
			{
				matriz[j][auxiliar]++;
				bandera[auxiliar]=1;
			}
		}
		
		


		for(i=0;i<tamanioCiudad;i++)
		{
			for(j=0;j<tamanioCiudad;j++)
			{
				System.out.print(matriz[i][j]);
			}
			System.out.println("");
		}
		
	}
	
	
	
	
	/***************************************************
	La funcion guarda fichero recoge toda la informaci�n del programa
	y la transforma en un fichero wrl/x3d que despu�s podr� ser visualizado
	por el visor 3D
	
	*************************************************/
	public static void guardaFichero()
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
            e.printStackTrace(); //Devolvemos excepci�n
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
				e2.printStackTrace(); //Excepci�n, no se pudo cerrar el fichero
           	}
        }
	
	}
	
	/***************************************************
	La funcion escribeFichero escribe el fichero con los datos recogidos anteriormente
	
	*************************************************/
	public static void escribeFichero(PrintWriter pw)
	{
		int i;
		
		pw.println("#VRML V2.0 utf8");
		
		for(i=0;i<callesHorizontal;i++)
		{

			pw.println("DEF Calle Group {"
					+ "	children ["
					+ "		Transform{"
					+ "			translation 0 0 0"
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
					+ "						size 10 0.1 10"
					+ "					}"
					+ "				}"
					+ "			]"
					+ "		}"
					+ "	]"
					+ "}");
		}
	


		
	}
	
}
