package projeto_rpg.personagens.jogador;

public class Arqueiro extends Jogador{
	
	public Arqueiro(String nome, int idade, char sexo)
	{
		super(nome, "Arqueiro", idade, sexo);
		
		setAtaque(30);
		setDefesa(8);
		setVidaMaxima(70);
		setPontosVida(70);
	}
}
