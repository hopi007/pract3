package Estructura;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArbreB {
	private class NodeA {
		String contents;
		ArbreB yes, no;

		// PROVISIONAL
		NodeA(String contents) {
			// Constructor 1. Inicialitza als atributys yes i no a null
			this.contents = contents;
			yes = null;
			no = null;
		}

		// PROVISIONAL
		NodeA(String pregunta, ArbreB a1, ArbreB a2) {
			// Constructor 2. Crea el node i l'inicialitza amb els paràmetres
			contents = pregunta;
			yes = a1;
			no = a2;
		}

	}

	// Atributs: Taula de 2 posicions
	private NodeA[] root;

	/* CONSTRUCTORS */
	//PROVISIONAL
	public ArbreB(ArbreB a1, ArbreB a2, String pregunta) {
		// Constructor 1. Crea un arbre amb una pregunta i dos respostes
		initialise();
		root[0] = new NodeA(pregunta, a1, a2);
		rewind();
	}

	// PROVISIONAL
		public ArbreB() {
			// Constructor 2. Crea un arbre buit
			initialise();
			root[0] = new NodeA(null);
			rewind();
		}

		public ArbreB(String filename) throws Exception {
			// Constructor 3. Crea l'arbre amb el contingut donat en un fitxer
			// El paràmetre indica el nom del fitxer
			initialise();
			root[0] = loadFromFile(filename);
			rewind();
		}
		
		private void initialise() {
			root = new NodeA[2];
			for (int i = 0; i < 2; i++) {
				root[i] = null;
			}
		}

	/* PUBLIC METHODS */
	public boolean isEmpty() {
		// COMPLETE
		return root[1] == null||root[1].contents==null;
	}

	public void rewind() {
		// COMPLETE
		root[1] = root[0];
	}

	/* True if the current node is an answer (a leaf) */
	public boolean atAnswer() {
		// COMPLETE
		return root[1].contents.charAt(root[1].contents.length() - 1) != '?'||root[1].contents==null;
	}

	/* move current to yes-descendant of itself */
	public void moveToYes() {
		// COMPLETE
		root[1] = root[1].yes.root[0];
	}

	/* move current to yes-descendant of itself */
	public void moveToNo() {
		// COMPLETE
		root[1] = root[1].no.root[1];
	}

	/* get the contents of the current node */
	public String getContents() {
		// COMPLETE
		return root[1].contents;
	}

	/*
	 * Substituir la informació del node actual per la pregunta donada pel jugador.
	 * Previament crear el node que serà el seu fill dret, resposta no encertada,
	 * amb la informació del node actual.
	 */
	public void improve(String question, String answer) {
		// COMPLETE
		ArbreB right = new ArbreB(new ArbreB(), new ArbreB(), root[1].contents );
		root[1].contents = question;
		root[1].yes = new ArbreB(new ArbreB(), new ArbreB(), answer);
		root[1].no = right;
	}

	private void preorderWrite(BufferedWriter buw) throws Exception {
		// Imprescindible que la implementació sigui recursiva
		rewind();
		buw.write(arbreSencer());
	}
	
	private String arbreSencer() {
		if (atAnswer())
			return getContents() + "\n";
		String answer = getContents() + "\n";
		moveToYes();
		answer += arbreSencer();
		rewind();
		moveToNo();
		answer += arbreSencer();
		return answer;
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
		ArbreB yes, no;
		try {
			entrada = new BufferedReader(new FileReader(fileIn));
			linea = entrada.readLine();
			while (linea != null) {
				if (root[0] == null) {
					root[0] = new NodeA(linea, new ArbreB(), new ArbreB());
					rewind();
				} else {
					yes= acabat(entrada);
					no=acabat(entrada);
					
					
				}
				
			}
			entrada.close();
		} catch (IOException e) {
			System.err.println("Error:c");
			System.exit(0);
		}
		System.out.println();
		System.out.println("HAS CARREGAT UN FITXER.");
		return root[0];
	}

	private ArbreB acabat(BufferedReader entrada) { //repensarlo
		String linea;
		ArbreB aux = null;
		try {
			linea = entrada.readLine();
			if (isEmpty()) 
	             return aux= new ArbreB( new ArbreB(), new ArbreB(),linea);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        rewind();
        ArbreB left, right;
        moveToYes();
        acabat(entrada);
        rewind();
        moveToNo();
        acabat(entrada);
        rewind();
        return aux;
    }

	public void visualitzarAnimals() {
		/* Following the guidelines indicated in the statement of practice */
		/* COMPLETE */
		if (atAnswer())
			System.out.println("\t"+getContents().toUpperCase());
		moveToYes();
		visualitzarAnimals();
		rewind();
		moveToNo();
		visualitzarAnimals();
	}

	public int quantsAnimals() {
		/* Following the guidelines indicated in the statement of practice */
		/* COMPLETE */
		int numAni = 0;

		return quantsAnimals(numAni);
	}

	private int quantsAnimals(int numAnim) {
		if (atAnswer())
			return numAnim++;
		moveToYes();
		numAnim = quantsAnimals(numAnim);
		rewind();
		moveToNo();
		numAnim = quantsAnimals(numAnim);
		return numAnim;
	}

	public int alsada() {
		/* COMPLETE */
		// Imprescindible invocar a un mètode la classe NodeA
		if (atAnswer())
			return 1;
		int left, right;
		moveToYes();
		left = alsada() + 1;
		rewind();
		moveToNo();
		right = alsada() + 1;
		if (left >= right)
			return left;
		return right;
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
