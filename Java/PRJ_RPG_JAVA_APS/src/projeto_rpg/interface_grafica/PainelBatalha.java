package projeto_rpg.interface_grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.Window;
import java.awt.event.KeyEvent;

import projeto_rpg.personagens.jogador.Jogador;

import projeto_rpg.personagens.inimigo.Inimigo;
import projeto_rpg.personagens.inimigo.Goblin;
import projeto_rpg.personagens.inimigo.Orc;
import projeto_rpg.personagens.inimigo.Dragao;

public class PainelBatalha extends JPanel implements KeyListener{
		
	private Jogador jogador;
	private Inimigo inimigo;
	
	private Image fundoBatalha;
	private Image spriteJogador;
	private Image spriteInimigo;
	
	private String[] opcoes = {
		    "ATACAR",
		    "DEFENDER",
		    "CURAR",
		    "FUGIR"
		};
	
	private String mensagemBatalha = "Um inimigo apareceu!";
	private int opcaoSelecionada = 0;
	private boolean jogadorMorreu = false;
	private Image telaMorte;
	
	public PainelBatalha(Jogador jogador, Inimigo inimigo) {
		this.jogador = jogador;
		this.inimigo = inimigo;
		
		// fundo
	    if(inimigo instanceof Goblin) {
	        fundoBatalha = new ImageIcon(getClass().getResource("/resources/image/battle/floresta_batalha.png")).getImage();
	    }
	    else if(inimigo instanceof Orc) {
	        fundoBatalha = new ImageIcon(getClass().getResource("/resources/image/battle/floresta_batalha.png")).getImage();
	    }

	    else if(inimigo instanceof Dragao) {
	        fundoBatalha = new ImageIcon(getClass().getResource("/resources/image/battle/floresta_batalha.png")).getImage();
	    }
	    
	    // fundo jogador
		
		if(jogador.getClasse().equals("Guerreiro")) {
		    spriteJogador = new ImageIcon(getClass().getResource("/resources/image/battle/guerreiro_batalha.png")).getImage();
		}
		else if(jogador.getClasse().equals("Mago")) {
		    spriteJogador = new ImageIcon(getClass().getResource("/resources/image/battle/mago_batalha.png")).getImage();
		}
		else if(jogador.getClasse().equals("Arqueiro")) {
			spriteJogador = new ImageIcon(getClass().getResource("/resources/image/battle/arqueiro_batalha.png")).getImage();
		}
	
		// fundo inimigo
		if(inimigo.getClass().getSimpleName().equals("Goblin")) {
			spriteInimigo = new ImageIcon(getClass().getResource("/resources/image/battle/goblin_batalha.png")).getImage();
		}
		else if(inimigo.getClass().getSimpleName().equals("Orc")) {
		    spriteInimigo = new ImageIcon(getClass().getResource("/resources/image/battle/orc_batalha.png")).getImage();
		}
		else if(inimigo.getClass().getSimpleName().equals("Dragao")) {
		    spriteInimigo = new ImageIcon(getClass().getResource("/resources/image/battle/dragao_batalha.png")).getImage();
		}
		
		telaMorte = new ImageIcon(getClass().getResource("/resources/image/voce_morreu.png")).getImage();
		
		addKeyListener(this);
		setFocusable(true);
	}
	
	
	@Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // anti serrilhado
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // fundo batalha
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // caixa inferior
        g2.setColor(new Color(20, 20, 20));

        g2.fillRect(0, 420, 900, 180);

        // borda
        g2.setColor(Color.WHITE);

        g2.drawRect(10, 430, 870, 150);
        
        // fundo
        g.drawImage(fundoBatalha, 0, 0, getWidth(), getHeight(), this);
        
        g.setColor(new Color(15, 15, 15));
        g.fillRect(0, 420, 900, 180);
        g.setColor(new Color(210, 190, 140));
        g.drawRect(10, 430, 870, 150);
        
        // inimigo
        g.drawImage(spriteInimigo, 560, 120, 220, 220, this);
        
	     // hud inimigo
	
	     // Fundo HUD
	     g.setColor(new Color(0, 0, 0, 180));
	     g.fillRoundRect(40, 40, 260, 90, 20, 20);
	
	     // Nome inimigo
	     g.setFont(new Font("Arial", Font.BOLD, 22));
	     g.setColor(Color.WHITE);
	     g.drawString(inimigo.getNome(), 55, 70);
	
	     // Level
	     g.setFont(new Font("Arial", Font.PLAIN, 18));
	     g.drawString("Lv " + inimigo.getLevel(), 210, 70);
	
	     // Barra HP fundo
	     g.setColor(Color.DARK_GRAY);
	     g.fillRoundRect(55, 85, 200, 20, 10, 10);
	
	     // porcentagem HP
	     double porcentagemHP = (double) inimigo.getPontosVida() / inimigo.getVidaMaxima();
	
	     int larguraHP = (int)(200 * porcentagemHP);
	
	     // cor HP
	     if(porcentagemHP > 0.6) {
	         g.setColor(new Color(50, 220, 50));
	     }
	     else if(porcentagemHP > 0.3) {
	         g.setColor(new Color(255, 200, 50));
	     }
	     else {
	         g.setColor(new Color(220, 50, 50));
	     }
	
	     // Barra HP
	     g.fillRoundRect(55, 85, larguraHP, 20, 10, 10);
	
	     // Texto HP
	     g.setColor(Color.WHITE);
	     g.setFont(new Font("Arial", Font.PLAIN, 15));
	     g.drawString(inimigo.getPontosVida() + " / " + inimigo.getVidaMaxima(), 120, 100);
        
        // jogador
        g.drawImage(spriteJogador, 50, 200, 220, 220, this);
        
	     // hud jogador
	
	     // Fundo HUD
	     g.setColor(new Color(0, 0, 0, 180));
	     g.fillRoundRect(560, 330, 300, 120, 20, 20);
	
	     // Nome
	     g.setColor(Color.WHITE);
	     g.setFont(new Font("Arial", Font.BOLD, 24));
	     g.drawString(jogador.getNome(), 580, 360);
	
	     // Level
	     g.setFont(new Font("Arial", Font.PLAIN, 18));
	     g.drawString("Lv " + jogador.getLevel(), 760, 360);
	
	     // HP
	
	     // Fundo barra
	     g.setColor(Color.DARK_GRAY);
	     g.fillRoundRect(580, 375, 220, 22, 10, 10);
	
	     // porcentagem
	     double hpPorcentagem = (double) jogador.getPontosVida() / jogador.getVidaMaxima();
	
	     int hpLargura = (int)(220 * hpPorcentagem);
	
	     // Cor HP
	     if(hpPorcentagem > 0.6) {
	         g.setColor(new Color(50, 220, 50));
	     }
	     else if(hpPorcentagem > 0.3) {
	         g.setColor(new Color(255, 200, 50));
	     }
	     else {
	         g.setColor(new Color(220, 50, 50));
	     }
	
	     // Barra HP
	     g.fillRoundRect(580, 375, hpLargura, 22, 10, 10);
	
	     // Texto HP
	     g.setColor(Color.WHITE);
	     g.setFont(new Font("Arial", Font.PLAIN, 15));
	     g.drawString(jogador.getPontosVida() + " / " + jogador.getVidaMaxima(),650, 392);
	
	     // XP
	
	     // Fundo barra XP
	     g.setColor(Color.DARK_GRAY);
	     g.fillRoundRect(580, 410, 220, 14, 10, 10);
	
	     // cálculo XP
	     int xpAtual = jogador.getExperiencia();
	
	     int xpMaximo = jogador.getLevel() * 100;
	
	     double xpPorcentagem = (double) xpAtual / xpMaximo;
	
	     int xpLargura = (int)(220 * xpPorcentagem);
	
	     // Barra XP
	     g.setColor(new Color(70, 150, 255));
	     g.fillRoundRect(580, 410, xpLargura, 14, 10, 10);
	
	     // Texto XP
	     g.setColor(Color.WHITE);
	     g.setFont(new Font("Arial", Font.PLAIN, 13));
	     g.drawString("XP", 805, 422);
	        
	     // Caixa da HUD
	        g.setColor(new Color(0, 0, 0, 180));
	        g.fillRoundRect(20, 420, 300, 130, 20, 20);
	
	        // Fonte
	        g.setFont(new Font("Serif", Font.BOLD, 28));
	
	        for(int i = 0; i < opcoes.length; i++) {
	            // Opção selecionada
	            if(i == opcaoSelecionada) {
	                g.setColor(new Color(255, 220, 120));
	                g.drawString("► " + opcoes[i], 40, 460 + (i * 30));
	            }
	            // Opções normais
	            else {
	                g.setColor(Color.WHITE);
	                g.drawString(opcoes[i], 70, 460 + (i * 30));
	            }
	        }
	        
	     // Mensagem batalha
	        g.setFont(new Font("Arial", Font.PLAIN, 20));
	        g.setColor(Color.WHITE);
	        g.drawString(mensagemBatalha, 320, 500);
	        
	     // tela de morte
	        if(jogadorMorreu) {
	            g.drawImage(telaMorte, 0, 0, getWidth(), getHeight(), this);
	        }
	        
    }
	
	private void executarAcao() {

	    String acao = opcoes[opcaoSelecionada];
	    // atacar
	    if(acao.equals("ATACAR")) {

	        int dano = jogador.atacar();

	        inimigo.receberDano(dano);
	        mensagemBatalha = jogador.getNome() +" causou " + dano + " de dano!";

	        // inimigo morreu
	        if(!inimigo.estaVivo()) {
	            mensagemBatalha = inimigo.getNome() + " foi derrotado!";

	            jogador.ganharExperiencia(inimigo.getXp());
	            repaint();

	            Timer fechar = new Timer(500, e -> {
	            	Window janela = SwingUtilities.getWindowAncestor(this);
	            	janela.dispose();
	            	});

	            fechar.setRepeats(false);
	            fechar.start();
	            return;
	        }
	        turnoInimigo();
	    }

	    // defender

	    else if(acao.equals("DEFENDER")) {

	        jogador.defender();
	        mensagemBatalha = jogador.getNome() + " está defendendo!";
	        turnoInimigo();
	    }

	    // curar
	    
	    else if(acao.equals("CURAR")) {
	    	
	        jogador.curar(25);
	        mensagemBatalha = jogador.getNome() + " recuperou vida!";
	        turnoInimigo();
	    }
	    // fugir
	    else if(acao.equals("FUGIR")) {
	        Window janela = SwingUtilities.getWindowAncestor(this);
	        janela.dispose();
	    }
	    repaint();
	}
	
	private void turnoInimigo() {

	    if(!inimigo.estaVivo()) {
	        return;
	    }

	    int acao = (int)(Math.random() * 3);
	    
	    // atacar
	    if(acao == 0) {

	        int dano = inimigo.atacar();

	        jogador.receberDano(dano);
	        mensagemBatalha += "  " + inimigo.getNome() + " causou " + dano + " de dano!";

	     // jogador morreu
	        if(!jogador.estaVivo()) {
	            jogadorMorreu = true;
	            repaint();

	            Timer fechar = new Timer(3000, e -> {

	                // fecha batalha
	                Window janelaBatalha = SwingUtilities.getWindowAncestor(this);
	                janelaBatalha.dispose();

	                // fecha mapa
	                for(Window janela : Window.getWindows()) {
	                    if(janela instanceof TelaMapa) {
	                        janela.dispose();
	                    }
	                }
	                // volta menu inicial
	                new TelaInicial();
	            });
	            fechar.setRepeats(false);
	            fechar.start();
	        }
	    }

	    // defender
	    else if(acao == 1) {
	        inimigo.defender();
	        mensagemBatalha += "  " + inimigo.getNome() + " está defendendo!";
	    }
	    
	    // curar
	    else {
	        inimigo.curar(20);
	        mensagemBatalha += "  " + inimigo.getNome() + " se curou!";
	    }
	}
	
	@Override
	public void keyPressed(KeyEvent e) {

	    int tecla = e.getKeyCode();

	    // baixo
	    if(tecla == KeyEvent.VK_S || tecla == KeyEvent.VK_DOWN) {
	        opcaoSelecionada++;

	        if(opcaoSelecionada >= opcoes.length) {

	            opcaoSelecionada = 0;
	        }

	        repaint();
	    }

	    // cima
	    if(tecla == KeyEvent.VK_W || tecla == KeyEvent.VK_UP) {
	        opcaoSelecionada--;
	        if(opcaoSelecionada < 0) {
	            opcaoSelecionada = opcoes.length - 1;
	        }
	        repaint();
	    }
	    // enter
	    if(tecla == KeyEvent.VK_ENTER) {
	        executarAcao();
	    }
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
}
