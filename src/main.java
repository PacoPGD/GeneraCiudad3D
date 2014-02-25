import java.io.FileWriter;
import java.io.PrintWriter;


public class main {

	/***************************************************
	Funcion principal del programa
	*************************************************/
	public static void main(String[] args)
	{
		System.out.println("Proyecto iniciado");
	}

	
	/***************************************************
	La funcion guarda fichero recoge toda la informaci�n del programa
	y la transforma en un fichero wrl/x3d que despu�s podr� ser visualizado
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
            pw.println("Escribiendo fichero");
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
}
