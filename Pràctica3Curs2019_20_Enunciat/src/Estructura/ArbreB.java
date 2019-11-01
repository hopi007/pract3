package Estructura;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

//Hola Cristian
public class ArbreB {
	private class NodeA {
		String contents;
		ArbreB yes, no;	
		NodeA(String contents) {
            		// Constructor 1. Inicialitza als atributys yes i no a null
            		this.contents = contents;
            		yes = null;
            		no = null;
        	}

        	// PROVISIONAL
        	NodeA(String pregunta, ArbreB a1, ArbreB a2) {
            		// Constructor 2. Crea el node i l'inicialitza amb els paràmetres
            		this.contents = pregunta;
            		yes = a1;
            		no = a2;
		}
	}
	// Atributs: Taula de 2 posicions
	private NodeA[] root;

	/* CONSTRUCTORS */
	public ArbreB(ArbreB a1, ArbreB a2, String pregunta) {
		//Constructor 1. Crea un arbre amb una pregunta i dos respostes
		new NodeA(pregunta, a1, a2);
	}
	public ArbreB() {
		//Constructor 2. Crea un arbre buit
		new NodeA(null);
	}	
	public ArbreB(String filename) throws Exception{
		//Constructor 3. Crea l'arbre amb el contingut donat en un fitxer
		//El paràmetre indica el nom del fitxer
		String linea;
		
        	File fileIn = new File(filename);
        	BufferedReader entrada = new BufferedReader(new FileReader(fileIn));
		
		linea= entrada.readLine();
		
		while(linea!=null){
		
			if(linea.charAt(linea.length()-1)=='?')
				new NodeA(linea); //no estoy seguro si sería asñi para decirle que almacene la pregunta, aunque no tenga las respuestas
			else
				//añade la respuesta al lado izquierdo de la pregunta (lado positivo)
				//si es la segunda respuesta que lee, la tiene que añadir al lado derecho de la pregunta(lado negativo)
				//entonces hemos llegado a un extremo del árbol
		}
	}

	/* PUBLIC METHODS */
	public boolean isEmpty() {
		//COMPLETE
		return root[0] == null;
	}
	public void rewind() {
		//COMPLETE
		root[1] = root[0];
		// FALTA CHECKAR ESTO
	}
	/* True if the current node is an answer (a leaf) */
	public boolean atAnswer() {
		//COMPLETE
		String node = root[1].contents;
		return node.substring(node.length() - 1) != "?";
	}
	/* move current to yes-descendant of itself */
	public void moveToYes() {
		//COMPLETE
		root[1] = root[1].yes.root[0];
	}
	/* move current to yes-descendant of itself */
	public void moveToNo() {
		//COMPLETE
	}
	/* get the contents of the current node */
	public String getContents() {
		//COMPLETE
	}
	 /* Substituir la informació del node actual
	 * per la pregunta donada pel jugador. Previament crear el node que serà el
	 * seu fill dret, resposta no encertada, amb la informació del node actual.
	 */
	public void improve(String question, String answer) {
		//COMPLETE
	}
	private void preorderWrite(BufferedWriter buw) throws Exception {
		//Imprescindible que la implementació sigui recursiva
	}
	/* Saves contents of tree in a text file */
	public void save(String filename) throws Exception {
		BufferedWriter buw = null;
		try {
			buw = new BufferedWriter(new FileWriter(filename));
			this.preorderWrite(buw);
			buw.close();

		} catch (IOException e) {
			System.err.println("saveToTextFile failed: " + e);
			System.exit(0);
		}
	}
	private NodeA loadFromFile(String filename){
		//Imprescindible implementació recursiva
	}
	public void visualitzarAnimals() {
		/* Following the guidelines indicated in the statement of practice */
		/* COMPLETE */
	}
	public int quantsAnimals() {
		/* Following the guidelines indicated in the statement of practice */
		/* COMPLETE */
	}
	public int alsada() {
		/* COMPLETE */
		// Imprescindible invocar a un mètode la classe NodeA
	}
	public void mostraPreguntes() {
		/* COMPLETE */
		// Visualitza a pantalla les preguntes
		// Imprescindible invocar a un màtode la classe NodeA
	}
}
