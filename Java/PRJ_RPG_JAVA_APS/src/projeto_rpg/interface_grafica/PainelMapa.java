package projeto_rpg.interface_grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import projeto_rpg.personagens.jogador.*;
import projeto_rpg.personagens.inimigo.*;

public class PainelMapa extends JPanel implements KeyListener, MouseListener {

    private Jogador jogador;
    
    // Frame animação
    private Image[] animacaoAndando;
    private int frameAtual = 0;
    private int contadorFrames = 0;
	
    // Posição jogador
    private int jogadorX = 423;
    private int jogadorY = 305;

    // Velocidade
    private int velocidade = 5;

    // Fundo mapa
    private Image fundoMapa;
    
    // Direções
    private boolean cima;
    private boolean baixo;
    private boolean esquerda;
    private boolean direita;
    
    // Classe do jogador
    private Image spriteParado;
    
    // Spanw Jogador
    private final int spawnX = 423;
    private final int spawnY = 305;
    
    // Inimigos
    // posições
    private int goblinX = 710;
    private int goblinY = 400;

    private int orcX = 110;
    private int orcY = 290;

    private int dragaoX = 400;
    private int dragaoY = 50;

    // sprites
    private Image spriteGoblin;
    private Image spriteOrc;
    private Image spriteDragao;

    private boolean emBatalha = false;
    
    // exibir ficha do jogador
    private Rectangle botaoFicha;
    private Rectangle botaoSair;
    
    public PainelMapa(Jogador jogador) {

        this.jogador = jogador;

        setFocusable(true);

        addKeyListener(this);
        
        addMouseListener(this);
        
        botaoFicha = new Rectangle(45, 430, 90, 35);
        botaoSair = new Rectangle(210, 430, 70, 35);

        // Carregar imagem do mapa
        fundoMapa = new ImageIcon(getClass().getResource("/resources/image/mapa.png")).getImage();

        // Timer do jogo
        Timer timer = new Timer(16, e -> {
            moverJogador();
            atualizarAnimacao();
            repaint();
        });

        timer.start();

        // Pega a classe do jogador
        String classeJogador = jogador.getClasse();

        // Escolher sprite
        if(classeJogador.equals("Guerreiro")) {
            spriteParado = new ImageIcon(getClass().getResource("/resources/image/guerreiro.png")).getImage();
        }

        else if(classeJogador.equals("Mago")) {
            spriteParado = new ImageIcon(getClass().getResource("/resources/image/mago.png")).getImage();
        }

        else if(classeJogador.equals("Arqueiro")) {
            spriteParado = new ImageIcon(getClass().getResource("/resources/image/arqueiro.png")).getImage();
        }
        
        if(classeJogador.equals("Guerreiro")){
        	animacaoAndando = new Image[3];
        	animacaoAndando[0] = new ImageIcon(getClass().getResource("/resources/image/move/guerreiro_walk1.PNG")).getImage();
        	animacaoAndando[1] = new ImageIcon(getClass().getResource("/resources/image/move/guerreiro_walk2.PNG")).getImage();
        	animacaoAndando[2] = new ImageIcon(getClass().getResource("/resources/image/move/guerreiro_walk3.PNG")).getImage();
        }
        else if(classeJogador.equals("Mago")) {
        	animacaoAndando = new Image[3];
        	animacaoAndando[0] = new ImageIcon(getClass().getResource("/resources/image/move/mago_walk1.PNG")).getImage();
        	animacaoAndando[1] = new ImageIcon(getClass().getResource("/resources/image/move/mago_walk2.PNG")).getImage();
        	animacaoAndando[2] = new ImageIcon(getClass().getResource("/resources/image/move/mago_walk3.PNG")).getImage();
        }
        else if(classeJogador.equals("Arqueiro")) {
        	animacaoAndando = new Image[3];
        	animacaoAndando[0] = new ImageIcon(getClass().getResource("/resources/image/move/arqueiro_walk1.PNG")).getImage();
        	animacaoAndando[1] = new ImageIcon(getClass().getResource("/resources/image/move/arqueiro_walk2.PNG")).getImage();
        	animacaoAndando[2] = new ImageIcon(getClass().getResource("/resources/image/move/arqueiro_walk3.PNG")).getImage();
        }
        
        spriteGoblin = new ImageIcon(getClass().getResource("/resources/image/goblin.png")).getImage();
        spriteOrc = new ImageIcon(getClass().getResource("/resources/image/orc.png")).getImage();
        spriteDragao = new ImageIcon(getClass().getResource("/resources/image/dragao.png")).getImage();
    }
    private void moverJogador() {
        // Vertical
        if(cima) jogadorY -= velocidade;
        if(baixo) jogadorY += velocidade;
        // Horizontal
        if(esquerda) jogadorX -= velocidade;
        if(direita) jogadorX += velocidade;
        
        verificarColisoes();
    }
    
    private void verificarColisoes() {
    	
    	// evita abrir várias batalhas
        if(emBatalha) {
            return;
        }

        // Jogador
        Rectangle jogadorRect = new Rectangle(jogadorX, jogadorY, 64, 64);

        // Goblin
        Rectangle goblinRect = new Rectangle(goblinX, goblinY, 64, 64);

        if(jogadorRect.intersects(goblinRect)) {
        	emBatalha = true;
        	
        	pararMovimento();
        	
            jogadorX = spawnX;
            jogadorY = spawnY;
            
            new TelaBatalha(jogador, new Goblin());
            
            emBatalha = false;
            
            return;
        }

        // Orc
        Rectangle orcRect = new Rectangle(orcX, orcY, 64, 64);

        if(jogadorRect.intersects(orcRect)) {
        	emBatalha = true;
        	
        	pararMovimento();
        	
            jogadorX = spawnX;
            jogadorY = spawnY;
            
        	new TelaBatalha(jogador, new Orc());
        	
        	emBatalha = false;
        	
            return;
        }

        // Dragão
        Rectangle dragaoRect = new Rectangle(dragaoX, dragaoY, 96, 96);

        if(jogadorRect.intersects(dragaoRect)) {
        	emBatalha = true;
        	
        	pararMovimento();
        	
            jogadorX = spawnX;
            jogadorY = spawnY;
            
            new TelaBatalha(jogador, new Dragao());
            
            emBatalha = false;
            
            return;
        }
    }
    
    // Parar movimento quando estiver em batalha
    private void pararMovimento() {

        cima = false;
        baixo = false;
        esquerda = false;
        direita = false;
    }
    
    private void atualizarAnimacao() {
        boolean andando = cima || baixo || esquerda || direita;
        if(andando) {
            contadorFrames++;
            if(contadorFrames >= 10) {
                frameAtual++;
                if(frameAtual >= animacaoAndando.length) {
                    frameAtual = 0;
                }
                contadorFrames = 0;
            }
        }
        else {
            frameAtual = 0;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Anti serrilhado
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Desenhar mapa
        g2.drawImage(fundoMapa, 0, 0, getWidth(), getHeight(), this);

        // Desnhar jogador
        boolean andando = cima || baixo || esquerda || direita;
        if(andando) 
        	g.drawImage(animacaoAndando[frameAtual], jogadorX, jogadorY, 64, 64, this);
        else 
        	g.drawImage(spriteParado, jogadorX, jogadorY, 64, 64, this);
        
     // Nome do jogador
        g.setFont(new Font("Arial", Font.BOLD, 16));

        g.setColor(Color.WHITE);

        String nome = jogador.getNome();

        // Centralizar nome
        FontMetrics fm = g.getFontMetrics();
        int larguraTexto = fm.stringWidth(nome);
        int xNome = jogadorX + (64 / 2) - (larguraTexto / 2);
        int yNome = jogadorY - 10;

        // Sombra
        g.setColor(Color.BLACK);

        g.drawString(nome, xNome + 2, yNome + 2);

        // Texto principal
        g.setColor(Color.WHITE);
        g.drawString(nome, xNome, yNome);
        
        int hudX = 20;
        int hudY = 430;
        int hudLargura = 260;
        int hudAltura = 140;

        // Fundo escuro transparente
        g.setColor(new Color(0, 0, 0, 170));
        g.fillRoundRect(hudX, hudY, hudLargura, hudAltura, 25, 25);

        // Borda dourada medieval
        g.setColor(new Color(210, 190, 140));
        g.drawRoundRect(hudX, hudY, hudLargura, hudAltura, 25, 25);

        // Fonte
        g.setFont(new Font("Serif", Font.BOLD, 18));

        // Nome
        g.setColor(new Color(255, 220, 120));
        g.drawString(jogador.getNome(), hudX + 15, hudY -5);

        // Linha separadora
        g.drawLine(hudX + 10, hudY + 35, hudX + 250, hudY + 35);
        
        // Inimigos
        g.drawImage(spriteGoblin, goblinX, goblinY, 64, 64, this);
        g.drawImage(spriteOrc, orcX, orcY, 72, 72, this);
        g.drawImage(spriteDragao, dragaoX, dragaoY, 96, 96, this);

        // Informações
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.setColor(Color.WHITE);
        g.drawString("Level: " + jogador.getLevel(), hudX + 15, hudY + 60);
        g.drawString("XP: " + jogador.getExperiencia(), hudX + 140, hudY + 60);
        g.drawString("Vida: " + jogador.getPontosVida() + "/" + jogador.getVidaMaxima(), hudX + 15, hudY + 85);
        g.drawString("Ataque: " + jogador.getAtaque(),hudX + 15, hudY + 110);
        g.drawString("Defesa: " + jogador.getDefesa(),hudX + 140, hudY + 110);
        
     // ficha

        String textoFicha = "FICHA";
        int fichaX = 45;
        int fichaY = 455;

        g.setColor(Color.BLACK);
        g.drawString(textoFicha, fichaX + 2, fichaY + 2);

        g.setColor(new Color(210, 190, 140));
        g.drawString(textoFicha, fichaX, fichaY);
        
        // sair

        String textoSair = "SAIR";
        int sairX = 210;
        int sairY = 455;

        g.setColor(Color.BLACK);
        g.drawString(textoSair, sairX + 2, sairY + 2);

        g.setColor(new Color(210, 190, 140));
        g.drawString(textoSair, sairX, sairY);    
    }

	@Override
	public void keyPressed(KeyEvent e) {
	
	    int tecla = e.getKeyCode();
	
	    if(tecla == KeyEvent.VK_W) cima = true;
	    if(tecla == KeyEvent.VK_S) baixo = true;
	    if(tecla == KeyEvent.VK_A) esquerda = true;
	    if(tecla == KeyEvent.VK_D) direita = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {

	    int tecla = e.getKeyCode();

	    if(tecla == KeyEvent.VK_W) cima = false;
	    if(tecla == KeyEvent.VK_S) baixo = false;
	    if(tecla == KeyEvent.VK_A) esquerda = false;
	    if(tecla == KeyEvent.VK_D) direita = false;
	}

    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void mouseClicked(MouseEvent e) {

    	Point p = e.getPoint();
        // ficha
        if(botaoFicha.contains(p)) {
            exibirFicha();
        }
        // sair
        if(botaoSair.contains(p)) {
            Window janela = SwingUtilities.getWindowAncestor(PainelMapa.this);
            janela.dispose();
            new TelaInicial();
        }
    }
    
    private void exibirFicha() {

        String ficha =
                "----- FICHA -----\n\n" +

                "Nome: " + jogador.getNome() + "\n" +
                "Classe: " + jogador.getClasse() + "\n\n" +

                "Level: " + jogador.getLevel() + "\n" +
                "Experiência: " + jogador.getExperiencia() + "\n\n" +

                "Vida: " +
                jogador.getPontosVida() + "/" + jogador.getVidaMaxima() + "\n\n" +

                "Ataque: " + jogador.getAtaque() + "\n" +
                "Defesa: " + jogador.getDefesa() + "\n" +
                "Força: " + jogador.getForca() + "\n" +
                "Precisão: " + jogador.getPrecisao() + "\n\n" +

                "Curas: " + jogador.getCurasVidaDisponiveis();

        JTextArea area = new JTextArea(ficha);

        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.BOLD, 16));
        area.setBackground(new Color(30, 30, 30));
        area.setForeground(new Color(220, 220, 220));

        JOptionPane.showMessageDialog(
                this,
                new JScrollPane(area),
                "Ficha do Personagem",
                JOptionPane.PLAIN_MESSAGE
        );
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}