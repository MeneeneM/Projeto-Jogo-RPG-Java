package projeto_rpg.personagens.jogador;

public class Guerreiro extends Jogador{
		
	public Guerreiro(String nome, int idade, char sexo)
	{
		super(nome, "Guerreiro", idade, sexo);
		
		setAtaque(20);
		setDefesa(15);
		setVidaMaxima(120);
		setPontosVida(120);
	}
}
