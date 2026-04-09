package projeto_rpg.sistema;
import projeto_rpg.personagens.jogador.*;
import java.util.Scanner;

public class Menu {		
	
	private Scanner sc = new Scanner(System.in);
		
	public void iniciarJogo()
	{
		
		Jogador jogador = null;
		int escolha = 0;
		
		System.out.println("--------- BEM - VINDO ---------");
		System.out.println("---- ao Jogo de RPG em Java ----");
		while(true) 
		{
			System.out.println("\nDigite para começar: ");
			System.out.println("1 - Jogar");
			System.out.println("2 - Criar Personagem");
			System.out.println("3 - Exibir Ficha");
			System.out.println("4 - Sair");
			
			escolha = sc.nextInt();
			sc.nextLine();
			
			switch(escolha) {
				case 1:
					if(jogador == null) {
						System.out.println("Crie um personagem primeiro!!!");						
					}
					else {
						limparTela();
	                    System.out.println("Iniciando batalha...");
	                    // metodo batalha	                    
					}
					break;
				case 2:
					jogador = criarPersonagem();
					break;
				case 3:
					if(jogador == null) {
						System.out.println("Crie um personagem primeiro!!!");
					}
					else {
						jogador.exibirFicha();
					}
					break;
				case 4:
	                System.out.println("Saindo do jogo...");
					sc.close();
					System.exit(0);
				default:
					System.out.println("Digite uma das opções válidas!!!");
			}
		}
		
	}
	
	
	// Obtendo os valores do usuario para a classe Jogador
	private Jogador criarPersonagem() 
	{
		String nome = lernomeValido();
		int idade = leridadeValido();
		char sexo = lersexoValido();
		
		int escolha = 0;
		
		while(escolha != 1 && escolha != 2 && escolha != 3) {
			System.out.println("Escolha sua classe:");
			System.out.println("1 - Guerreiro");
			System.out.println("2 - Mago");
			System.out.println("3 - Arqueiro");
        
        escolha = sc.nextInt();
        sc.nextLine();
        
        switch(escolha){
        	case 1:
        		return new Guerreiro(nome, idade, sexo);
        	case 2:
        		return new Mago(nome, idade, sexo);
        	case 3:
        		return new Arqueiro(nome, idade, sexo);
        	default:
                System.out.println("Opção inválida, tente novamente.");
        	}
        
		}
		
		return null;
	}
	    
	 // Validar nome
	private String lernomeValido() {
	    String nome;
	    
		while(true)
		{
			System.out.println("Digite seu nome: ");
			nome = sc.nextLine();
			
			if(!nome.trim().isEmpty()) {
				break;
			}
			System.out.println("Nome não pode ser vazio!!!");
		}	
		return nome;
	}
	    
	// Validar idade
	private int leridadeValido() {
		int idade;
		
		while(true) {
			System.out.println("Digite sua idade: ");
			
			if(sc.hasNextInt()) {
				idade = sc.nextInt();
				sc.nextLine();
				
				if(idade > 5) {
					return idade;
				}
				else {
				System.out.println("Digite uma idade valida!!!");
				}
			}
			else {
				System.out.println("Digite apenas números!!!");
				sc.nextLine();
			}
		}
	}
	
	// Validar sexo
	private char lersexoValido() {
		char sexo;
		
		sc.nextLine();
		
		while(true) {
			System.out.println("Digite seu sexo: ");
			String s = sc.nextLine();
			
			if(s.isEmpty()) {
				System.out.println("Entrada vazia! Digite um sexo(M/F)!!!");
				continue;
			}
			
			sexo = s.charAt(0);
			
			if(sexo == 'M' || sexo == 'm' || sexo == 'F' || sexo == 'f') {
				break;
			}
		}
		return sexo;
	}
	
	// Metodo pausar
	private void pausar() {
	    System.out.println("\nPressione ENTER para continuar...");
	    sc.nextLine();
	}
	
	// Limpar o console
	public void limparTela() {
	    for(int i = 0; i < 30; i++) {
	        System.out.println();
	    }
	}
}
