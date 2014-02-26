import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
		construirCalles();
		construirArboles();
		construirIglesia();
		imprimirCiudad();
		guardaFichero();
		
		System.exit(0);
	}

	
	/***************************************************
	La funcion guarda pedirDatos es la encargada de pedir al usuario la 
	información para generar la ciudad
	
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
	Esta funcion construye la matriz de calles
	
	*************************************************/
	public static void construirCalles()
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
	}
	
	public static void construirIglesia()
	{
		Random r=new Random();
		int i,j;
		int auxiliar,auxiliar2;
		int bandera=0;
		
		do
		{
			bandera = 0;
			
			do
			{
				auxiliar=r.nextInt(tamanioCiudad);
				auxiliar2=r.nextInt(tamanioCiudad);
			}while(matriz[auxiliar][auxiliar2]!=0);
			
			for(i=auxiliar;i<auxiliar+3 && i<tamanioCiudad;i++)
			{
				for(j=auxiliar2;j<auxiliar2+7 && j<tamanioCiudad;j++)
				{
					if(matriz[i][j]==0)
						bandera++;
				}
			}
		
			if(bandera==21)
			{
				for(i=auxiliar;i<auxiliar+3  && i<tamanioCiudad;i++)
				{
					for(j=auxiliar2;j<auxiliar2+7 && j<tamanioCiudad;j++)
					{
						matriz[i][j]=4;
					}
			
				}
			}

		}while(bandera!=21);
	}
	
	public static void construirArboles()
	{
		int i,j;
		
		for(i=1;i<tamanioCiudad-1;i++)
		{
			for(j=1;j<tamanioCiudad-1;j++)
			{
				if(matriz[i][j]==0 && matriz[i][j-1]>0 && matriz[i][j+1]>0)
					matriz[i][j]=3;
				
				if(matriz[i][j]==0 && matriz[i-1][j]>0 && matriz[i+1][j]>0)
					matriz[i][j]=3;
					
			}
			
		}
	}
	
	/***************************************************
	Esta funcion imprime la matriz de la ciudad
	*************************************************/
	
	public static void imprimirCiudad(){
		int i,j;
		//IMPRESION DE PRUEBA POR TERMINAL
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
	La funcion guarda fichero recoge toda la información del programa
	y la transforma en un fichero wrl/x3d que después podrá ser visualizado
	por el visor 3D
	
	*************************************************/
	public static void guardaFichero()
	{
		FileWriter fichero = null; 
		PrintWriter pw = null; 
		try
		{
			//LIMPIAR EL FICHERO POR SI TENIA CONTENIDO
			File f = new File("fichero.wrl");
			f.delete();
			try {
			  f.createNewFile();
			} catch (IOException ioe) {
			  ioe.printStackTrace();
			}
			
			//CREACION DE FICHERO
			fichero = new FileWriter("fichero.wrl"); //URL del fichero generado
			
			pw = new PrintWriter(fichero);
  
			//Escritura del fichero
            escribeFichero(pw);
            fichero.close();
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
	La funcion escribeFichero escribe el fichero con los datos recogidos anteriormente
	
	*************************************************/
	public static void escribeFichero(PrintWriter pw)
	{
		int i,j;
		int iglesia=0;
		
		pw.println("#VRML V2.0 utf8");

		
		for(i=0;i<tamanioCiudad;i++)
		{
			//VERTICAL
			if(matriz[i][0]==1)
			{
				for(j=0;j<tamanioCiudad;j++)
				{
					if(matriz[i][j]==1)
					pw.println("Transform{"
								+ "translation "+ j +" 0 "+ i +" children[ Inline{ url \"figuras/calle.wrl\"}]}");
				}	
			}
			
			//HORIZONTAL
			if(matriz[0][i]==1)
			{
				for(j=0;j<tamanioCiudad;j++)
				{	
					if(matriz[j][i]==1)
					pw.println("Transform{"
							+ "rotation 0.0 1.0 0.0 1.5708 translation "+ i +" 0 "+ j +" children[ Inline{ url \"figuras/calle.wrl\"}]}");
					
				}	
			}
			
			//CRUCE
			for(j=0;j<tamanioCiudad;j++)
			{
				if(matriz[i][j]==2)
					pw.println("Transform{"
							+ "translation "+ j +" 0 "+ i +" children[ Inline{ url \"figuras/calle2.wrl\"}]}");
					
			}
			
			//TIERRA
			for(j=0;j<tamanioCiudad;j++)
			{
				if(matriz[i][j]==0)
					pw.println("Transform{"
							+ "translation "+ j +" 0 "+ i +" children[ Inline{ url \"figuras/tierra.wrl\"}]}");
					
			}
			
			//ARBOL
			for(j=0;j<tamanioCiudad;j++)
			{
				if(matriz[i][j]==3)
					pw.println("Transform{"
							+ "translation "+ j +" 0.5 "+ i +" children[ Inline{ url \"figuras/tree.wrl\"}]}");
					
			}
			//IGLESIA
			for(j=0;j<tamanioCiudad;j++)
			{
				if(matriz[i][j]==4)
				{
					if(iglesia==0)
					{
					pw.println("Transform{"
							+ "rotation 0.0 1.0 0.0 1.5708 translation "+ (j+1.5) +" 2.5 "+ (i+1) +" children[ Inline{ url \"figuras/iglesia.wrl\"}]}");
					iglesia=1;
					}
					
					pw.println("Transform{"
							+ "translation "+ j +" 0 "+ i +" children[ Inline{ url \"figuras/tierra.wrl\"}]}");
				}
					
			}
		}
		

		pw.println("Transform{"
				+ "translation 0 10 0 children[ Inline{ url \"figuras/ejes.wrl\"}]}");
		

		pw.println("Viewpoint {position "+ (tamanioCiudad/2) +" "+(tamanioCiudad*2)+" "+ (tamanioCiudad/2)+" orientation 1 0 0 -1.57 description \"arriba\"}");

	}
	
}
