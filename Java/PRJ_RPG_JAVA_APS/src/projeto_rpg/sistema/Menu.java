package projeto_rpg.sistema;
import projeto_rpg.personagens.jogador.*;
import java.util.Scanner;

public class Menu {		
	
	Scanner sc = new Scanner(System.in);
		
	public void iniciarJogo()
	{
		
		Jogador jogador = null;
		int escolha = 0;
		
		while(true) 
		{
			System.out.println("--------- BEM - VINDO ---------");
			System.out.println("---- ao Jogo de RPG em Java ----");
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
		System.out.println("Digite seu nome: ");
		String nome = sc.nextLine();
		
		System.out.println("Digite sua idade: ");
		int idade = sc.nextInt();
		
		System.out.println("Digite seu sexo(M/F): ");
		char sexo = sc.next().charAt(0);
		
		sc.nextLine();
		
		int escolha = 0;
		
		while(escolha != 1 && escolha != 2 && escolha != 3) {
			System.out.println("Escolha sua classe:");
			System.out.println("1 - Guerreiro");
			System.out.println("2 - Mago");
			System.out.println("3 - Arqueiro");
        
        escolha = sc.nextInt();
        
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
	
	// Limpar o console
	public void limparTela() {
	    for(int i = 0; i < 30; i++) {
	        System.out.println();
	    }
	}
}
