package projeto_rpg.sistema;
import java.util.Scanner;
import projeto_rpg.Personagem;
import projeto_rpg.personagens.inimigo.Inimigo;

public class Batalha {
		private Scanner sc = new Scanner(System.in);
		
		public void iniciar(Personagem jogador, Inimigo inimigo)
		{
			System.out.println("Batalha Iniciada!!!");
			System.out.println(jogador.getNome() + " X " + inimigo.getNome());
			
			
			while(jogador.estaVivo() && inimigo.estaVivo())
			{
				System.out.println("\n" + jogador.getNome() + " HP: " + jogador.getPontosVida());
				System.out.println(inimigo.getNome() + " HP: " + inimigo.getPontosVida());
				
				turnoJogador(jogador, inimigo);
				
				if(!inimigo.estaVivo())
					break;
				
				turnoInimigo(inimigo, jogador);
				
				System.out.println("Pressione ENTER para continuar...");
				sc.nextLine();
			}
			
			if(jogador.estaVivo()) {
				System.out.println("Você ganhou!");
				
			    jogador.ganharExperiencia(inimigo.getXp());
			}
			else {
				System.out.println("Você foi derrotado!");
			}
			
			jogador.restaurarPosBatalha();
		}
		
		private void turnoJogador(Personagem jogador, Personagem inimigo)
		{
			System.out.println("\n--- Seu turno ---\n");
			System.out.println("1 - Atacar");
			System.out.println("2 - Defender");
			System.out.println("3 - Curar");
			
			int escolha;
			
			if(sc.hasNextInt()) {
			    escolha = sc.nextInt();
			    sc.nextLine();
			} else {
			    System.out.println("Digite um número válido!");
			    sc.nextLine();
			    return;
			}
			
			switch(escolha) 
			{
				case 1:
					int dano = jogador.atacar();
					inimigo.receberDano(dano);
					break;
				case 2:
					jogador.defender();
					break;
				case 3:
					if(jogador.getCurasVidaDisponiveis() > 0)
						jogador.curar(25);
					break;
				default:
					System.out.println("Ação invalída!!!");
			}
		}
		
		private void turnoInimigo(Personagem inimigo, Personagem jogador) 
		{
			System.out.println("\n--- Turno Inimigo ---\n");
			
			int acao = (int)(Math.random() * 3);
			
			switch(acao)
			{
				case 0:
					int dano = inimigo.atacar();
					jogador.receberDano(dano);
					break;
				case 1:
					inimigo.defender();
					break;
				case 2:
					if(inimigo.getCurasVidaDisponiveis() > 0)
						inimigo.curar(25);
					break;
			}
		}
}
