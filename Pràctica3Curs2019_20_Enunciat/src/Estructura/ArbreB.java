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
	public ArbreB(String filename) throws Exception {
		// Constructor 3. Crea l'arbre amb el contingut donat en un fitxer
		// El paràmetre indica el nom del fitxer
		for (int i = 0; i < 2; i++) {
			root[i] = null;
		}
		root[0] = loadFromFile(filename);
		rewind();
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
		root[1] = root[1].no.root[0];
	}
	/* get the contents of the current node */
	public String getContents() {
		//COMPLETE
		return root[1].contents;
	}
	 /* Substituir la informació del node actual
	 * per la pregunta donada pel jugador. Previament crear el node que serà el
	 * seu fill dret, resposta no encertada, amb la informació del node actual.
	 */
	public void improve(String question, String answer) {
		//COMPLETE
		NodeA right = new NodeA(root[1].contents);
		root[1].contents = question;
		root[1].yes.root[0].contents = answer;
		root[1].no.root[0] = right;
	}
	private void preorderWrite(BufferedWriter buw) throws Exception {
		// Imprescindible que la implementació sigui recursiva
		rewind();
		buw.write(visualitzarAnimals());
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
	private NodeA loadFromFile(String filename) {
		// Imprescindible implementació recursiva
		String linea;
		boolean left = false;
		File fileIn = new File(filename);
		BufferedReader entrada;
		for (int i = 0; i < 2; i++) {
			root[i] = null;
		}
		try {
			entrada = new BufferedReader(new FileReader(fileIn));
			linea = entrada.readLine();
			while (linea != null) {
				if (root[0] == null) {
					root[0] = new NodeA(linea);
					rewind();
				} else {
					if (!left) {
						moveToYes();
						left = acabat(root[1], linea);
					}
					moveToNo();
					acabat(root[1], linea);
				}
				rewind();
				linea = entrada.readLine();
			}
			entrada.close();
		} catch (IOException e) {
			System.err.println("Error:c");
			System.exit(0);
		}
		System.out.println("Arbre creat");
		return root[0];
	}

	private boolean acabat(NodeA node, String text) {
		if (node.contents.isEmpty()) {
			node = new NodeA(text);
			return false;
		} else if (atAnswer())
			return true;
		return acabat(node.yes.root[0], text) && acabat(node.no.root[0], text);
	}
	public void visualitzarAnimals() {
		/* Following the guidelines indicated in the statement of practice */
		/* COMPLETE */
		if (atAnswer())
			System.out.println(getContents());
		moveToYes();
		visualitzarAnimals();
		rewind();
		moveToNo();
		visualitzarAnimals();
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
		if (atAnswer())
			System.out.print("");
		System.out.println(getContents());
		moveToYes();
		mostraPreguntes();
		rewind();
		moveToNo();
		mostraPreguntes();
	}
}
