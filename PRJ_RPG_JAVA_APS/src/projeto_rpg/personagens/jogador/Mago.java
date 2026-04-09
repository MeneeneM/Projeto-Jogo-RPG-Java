package projeto_rpg.personagens.jogador;

public class Mago extends Jogador{
	
	public Mago(String nome, int idade, char sexo)
	{
		super(nome, "Mago", idade, sexo);
		
		setAtaque(30);
		setDefesa(8);
		setVidaMaxima(70);
		setPontosVida(70);
	}
}
