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
	// PROVISIONAL
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
		return root[1] == null || root[1].contents == null;
	}

	public void rewind() {
		// COMPLETE
		root[1] = root[0];
	}

	/* True if the current node is an answer (a leaf) */
	public boolean atAnswer() {
		// COMPLETE
		return isAnswer(getContents()) || root[1].contents == null;
	}

	private boolean isAnswer(String text) {
		return text.charAt(text.length() - 1) != '?';
	}

	/* move current to yes-descendant of itself */
	public void moveToYes() {
		// COMPLETE
		root[1] = root[1].yes.root[0];
	}

	/* move current to yes-descendant of itself */
	public void moveToNo() {
		// COMPLETE
		root[1] = root[1].no.root[0];
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
		ArbreB right = new ArbreB(new ArbreB(), new ArbreB(), root[1].contents);
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
		// moveToYes();
		answer += root[0].yes.arbreSencer();
		answer += root[0].no.arbreSencer();
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

		File fileIn = new File(filename);
		BufferedReader entrada;
		NodeA aux = null;
		try {
			entrada = new BufferedReader(new FileReader(fileIn));
			aux = crear(aux, entrada, entrada.readLine());
			entrada.close();

		} catch (IOException e) {
			System.err.println("Error:c");
			System.exit(0);
		}
		System.out.println();
		System.out.println("HAS CARREGAT UN FITXER.");
		return root[0];
	}

	private NodeA crear(NodeA arrel, BufferedReader entrada, String text) { // repensarlo
		if (arrel == null)
			arrel = new NodeA(text, new ArbreB(), new ArbreB());
		if (!isAnswer(text)) {
			try {
				String linea = entrada.readLine();
				if (linea != null) {
					arrel.yes.root[0] = crear(arrel.yes.root[0], entrada, linea);
					linea = entrada.readLine();
					arrel.no.root[0] = crear(arrel.no.root[0], entrada, linea);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return arrel;
	}

	public void visualitzarAnimals() {
		/* Following the guidelines indicated in the statement of practice */
		/* COMPLETE */
		if (atAnswer())
			System.out.println("\t" + getContents().toUpperCase());
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
