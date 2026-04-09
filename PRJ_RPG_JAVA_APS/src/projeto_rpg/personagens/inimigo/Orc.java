package projeto_rpg.personagens.inimigo;

public class Orc extends Inimigo{
	
	public Orc()
	{
		super("Orc", "Monstro");
		
		setAtaque(16);
		setDefesa(14);
		setVidaMaxima(200);
		setPontosVida(200);
	}

}
