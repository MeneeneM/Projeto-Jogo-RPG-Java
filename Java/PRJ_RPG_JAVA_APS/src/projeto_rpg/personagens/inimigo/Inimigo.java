package projeto_rpg.personagens.inimigo;
import projeto_rpg.Personagem;

public class Inimigo extends Personagem{
	private String tipo;
	protected int xp;

	public Inimigo(String nome, String tipo) {
	        super(nome, tipo, 0, 'M');
	        this.tipo = tipo;
	    }

	    public String getTipo() {
	        return tipo;
	    }
	    

	    public int getXp() {
	        return xp;
	    }
	}
