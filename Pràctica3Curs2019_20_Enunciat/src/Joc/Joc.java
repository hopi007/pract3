package Joc;

import Estructura.ArbreB;
import Keyboard.Keyboard;

public class Joc {

	private static boolean volsJugar;
	private static String resposta;
	static Keyboard consola = new Keyboard();
	private static ArbreB arbre;

	// falla improve de Arbre,
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Welcome to wonderful GUESS THE ANIMAL");
		System.out.println("-------------------------------------");
		System.out.println();
		System.out.print("Vols carregar un fitxer?");
		resposta = consola.readString();
		System.out.println();
		while (!resposta.equalsIgnoreCase("si") && !resposta.equalsIgnoreCase("no")) {
			System.out.print("Resposta no valida. Vols carregar un fitxer?");
			resposta = consola.readString();
			System.out.println();
		}
		volsJugar = true;
		while (volsJugar) {
			if (resposta.equalsIgnoreCase("si"))
				jocFitxer();
			else
				jocSenseFitxer();
		}

		System.out.print("ADEU!");
	}

	private static void jocFitxer() {

		carregaFitxer();
		mostrarFitxerRespostes();
		mostrarFitxerPreguntes();

		System.out.println("JUEGUEM!!!!");
		System.out.println();

		while (volsJugar) {

			System.out.print("\t" + arbre.getContents());
			resposta = consola.readString();
			System.out.println();

			while (!resposta.equalsIgnoreCase("si") && !resposta.equalsIgnoreCase("no")) {
				System.out.print("\tResposta no valida. " + arbre.getContents());
				resposta = consola.readString();
				System.out.println();
			}

			estarJugant(arbre.getContents(), resposta);
		}

	}

	private static void jocSenseFitxer() {

		String pregunta, yes, no, resposta;

		System.out.println("Per començar cal introduir una primera pregunta amb dues respostes");
		System.out.println("------------------------------------------------------------------");
		System.out.println();
		System.out.print("  Indica la pregunta de l'arrel de l'arbre: ");
		pregunta = consola.readString();
		pregunta = checkQues(pregunta);

		System.out.println();
		System.out.print("  Indica el nom de l'animal de la resposta afirmativa: ");
		yes = consola.readString();
		yes = checkAns(yes);

		System.out.println();
		System.out.print("  Indica el nom de l'animal de la resposta negativa: ");
		no = consola.readString();
		no = checkAns(no);

		System.out.println();
		System.out.println();

		System.out.println("JUEGUEM!!!!");
		System.out.println();

		arbre = crearArbre(pregunta, yes, no);

		while (volsJugar) {

			System.out.print("\t" + arbre.getContents());
			resposta = consola.readString();

			while (!resposta.equalsIgnoreCase("si") && !resposta.equalsIgnoreCase("no")) {
				System.out.print("\tResposta no valida. " + arbre.getContents());
				resposta = consola.readString();
			}

			estarJugant(arbre.getContents(), resposta);
		}
	}

	private static void estarJugant(String pregunta, String resposta) {

		if (resposta.equalsIgnoreCase("si")) {
			arbre.moveToYes();
			if (arbre.atAnswer()) {
				jaHoSe();
				continuarJugar();
			}
			
		} else {
			arbre.moveToNo();
			if (arbre.atAnswer()) {
				jaHoSe();
				continuarJugar();
			}
			
		}
	}

	private static void respostaFinalCorrecta() {
		System.out.println("Gooooood. ENCERTAT!!!");
	}

	private static void respostaFinalIncorrecta() {
		System.out.println("\tUuuups HE FALLAT!!!");
		System.out.print("\t\tplis, diguem el nom de l'animal que has pensat: ");
		String right = consola.readString();
		right = checkAns(right);
		System.out.println();
		System.out.print("\t\tplis, diguem una pregunta que correspon a aquest animal: ");
		String pregunta = consola.readString();
		pregunta = checkQues(pregunta);
		System.out.println();

		arbre.improve(pregunta, right);
	}
	
	private static void jaHoSe() {
		System.out.print("\tEm sembla que ja ho se!!!. Podria ser un/a "+ arbre.getContents()+"?");
		String resposta= consola.readString();
		
		while (!resposta.equalsIgnoreCase("si") && !resposta.equalsIgnoreCase("no")) {
			System.out.print("\tResposta no valida. Podria ser un/a " + arbre.getContents()+"?");
			resposta = consola.readString();
		}
		
		if (resposta.equalsIgnoreCase("si"))
			respostaFinalCorrecta();
		else {
			respostaFinalIncorrecta();
		}
	}

	private static void continuarJugar() {
		System.out.print("\tVols jugar altre cop? ");
		resposta = consola.readString();
		System.out.println();
		while (!resposta.equalsIgnoreCase("si") && !resposta.equalsIgnoreCase("no")) {
			System.out.print("\tResposta no valida. Vols jgar altre cop? ");
			resposta = consola.readString();
			System.out.println();
		}

		if (resposta.equalsIgnoreCase("si")) {
			volsJugar = true;
			arbre.rewind();
		} else {
			volsJugar = false;

			System.out.print("Vols emmagatzemar 'el coneixement' en un fitxer? ");
			String guardar = consola.readString();
			if (guardar.equalsIgnoreCase("si"))
				guardarFitxer(arbre);
		}
	}

	private static String checkQues(String preg) {
		if (preg.charAt(preg.length() - 1) != '?')
			preg = preg + "?";
		return preg.toUpperCase();
	}

	private static String checkAns(String ans) {
		if (ans.charAt(ans.length() - 1) == '?')
			ans = ans.substring(0, ans.length() - 1);
		return ans.toUpperCase();
	}

	private static ArbreB crearArbre(String pregunta, String yes, String no) {
		return arbre = new ArbreB(new ArbreB(null, null, yes), new ArbreB(null, null, no), pregunta);
	}

	private static void guardarFitxer(ArbreB arbre) {
		System.out.println();
		System.out.print("Nom del fitxer? ");
		String nomFitxer = consola.readString();
		System.out.println();

		if (!nomFitxer.substring(nomFitxer.length() - 4, nomFitxer.length()).equalsIgnoreCase(".txt"))
			nomFitxer = nomFitxer.toUpperCase() + ".TXT";
		nomFitxer = nomFitxer.toUpperCase();

		try {
			arbre.save(nomFitxer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void carregaFitxer() {
		System.out.print("Nom del fitxer: ");
		String nomFitxer = consola.readString();
		System.out.println();

		if (!nomFitxer.substring(nomFitxer.length() - 4, nomFitxer.length()).equalsIgnoreCase(".txt"))
			nomFitxer = nomFitxer.toUpperCase() + ".TXT";
		nomFitxer = nomFitxer.toUpperCase();

		try {
			arbre = new ArbreB(nomFitxer);

			System.out.println("------------------------");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void mostrarFitxerRespostes() {
		System.out.println("L'arbre de coneixement te els seguents animals:");
		arbre.visualitzarAnimals();
		System.out.println();
		System.out.println("en total en te: " + arbre.quantsAnimals());
		System.out.println("i l'alçada de l'arbre es " + arbre.alsada());
		System.out.println();
	}

	private static void mostrarFitxerPreguntes() {
		System.out.print("Vols visualitzar les preguntes de l'arbre de coneixement?");
		String resposta = consola.readString();
		System.out.println();

		while (!resposta.equalsIgnoreCase("si") && !resposta.equalsIgnoreCase("no")) {
			System.out.print("Vols visualitzar les preguntes de l'arbre de coneixement?");
			resposta = consola.readString();
			System.out.println();
		}

		if (resposta.equalsIgnoreCase("si"))
			arbre.mostraPreguntes();
		System.out.println();

	}

}
