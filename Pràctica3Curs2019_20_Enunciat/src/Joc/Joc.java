package Joc;

public class Joc {
	
	private boolean volsJugar;
	private String resposta;
	private Keyboard consola = new Keyboard();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		System.out.println("Welcome to wonderful GUESS THE ANIMAL");
		System.out.println("-------------------------------------");
		System.out.println();
		System.out.print("Vols carregar un fitxer?");
		resposta = consola.readString();
		System.out.println();
		while(!resposta.equals("SI")&&!resposta.equals("Si")&&!resposta.equals("sI")&&!resposta.equals("si")&&!resposta.equals("NO")&&!resposta.equals("No")&&!resposta.equals("nO")&&!resposta.equals("no")){
			System.out.print("Resposta no valida. Vols carregar un fitxer?");
			resposta= consola.readString();
			System.out.println();
		}
		volsJugar=true;
		while(volsJugar){
			if(resposta.equals("SI")||resposta.equals("sI")||resposta.equals("Si")||resposta.equals("si"))
				jocFitxer();
			else jocSenseFitxer();
		}
		
		System.out.print("ADEU!");
	}
	
	
	private static void jocFitxer(){
	
	}
	
	private static void jocSenseFitxer(){
		
		String pregunta, yes, no, resposta;
		
		System.out.println("Per començar cal introduir una primera pregunta amb dues respostes");
		System.out.println("------------------------------------------------------------------");
		System.out.printl();
		System.out.print("  Indica la pregunta de l'arrel de l'arbre: ");
		pregunta= consola.readString();
		
		//falta el metodo para añadir el ? al final por si acaso no lo pone el usuario
		
		System.out.println();
		System.out.print("  Indica el nom de l'animal de la resposta afirmativa: ");
		yes= consola.readString();
		System.out.println();
		System.out.print("  Indica el nom de l'animal de la resposta negativa: ");
		no= consola.readString();
		
		System.out.println();
		System.out.println();
		
		System.out.println("JUEGUEM!!!!");
		System.out.println();
		
		System.out.print(//la pregunta del node);
		resposta= consola.readString();
			while(!resposta.equals("SI")&&!resposta.equals("Si")&&!resposta.equals("sI")&&!resposta.equals("si")&&!resposta.equals("NO")&&!resposta.equals("No")&&!resposta.equals("nO")&&!resposta.equals("no")){
				System.out.print("Resposta no valida"//+ la pregunta del node);
				resposta= consola.readString();
			}
		
		if(resposta.equals("SI")||resposta.equals("sI")||resposta.equals("Si")||resposta.equals("si")){
		}
			
	}

}
