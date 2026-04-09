package projeto_rpg.personagens.inimigo;

public class Goblin extends Inimigo{
	
	public Goblin()
	{
		super("Goblin", "Monstro");
		
		setAtaque(8);
		setDefesa(5);
		setVidaMaxima(60);
		setPontosVida(60);
	}

}
