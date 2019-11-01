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
			//Constructor 1. Inicialitza als atributys yes i no a null
		}
		NodeA(String pregunta, ArbreB a1, ArbreB a2) {
			//Constructor 2. Crea el node i l'inicialitza amb els paràmetres
		}
	}
	// Atributs: Taula de 2 posicions
	private NodeA[] root;

	/* CONSTRUCTORS */
	public ArbreB(ArbreB a1, ArbreB a2, String pregunta) {
		//Constructor 1. Crea un arbre amb una pregunta i dos respostes
	}
	public ArbreB() {
		//Constructor 2. Crea un arbre buit
	}	
	public ArbreB(String filename) throws Exception{
		//Constructor 3. Crea l'arbre amb el contingut donat en un fitxer
		//El paràmetre indica el nom del fitxer
	}

	/* PUBLIC METHODS */
	public boolean isEmpty() {
		//COMPLETE
	}
	public void rewind() {
		//COMPLETE
	}
	/* True if the current node is an answer (a leaf) */
	public boolean atAnswer() {
		//COMPLETE
	}
	/* move current to yes-descendant of itself */
	public void moveToYes() {
		//COMPLETE
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
