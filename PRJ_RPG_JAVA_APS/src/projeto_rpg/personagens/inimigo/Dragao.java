package projeto_rpg.personagens.inimigo;

public class Dragao extends Inimigo{
	
	public Dragao()
	{
		super("Dragao", "Monstro");
		
		setAtaque(30);
		setDefesa(35);
		setVidaMaxima(500);
		setPontosVida(500);
	}

}
