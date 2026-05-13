package projeto_rpg.interface_grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PainelControles extends JPanel {

    private Image fundo;

    private Rectangle botaoVoltar;

    public PainelControles() {

        fundo = new ImageIcon(getClass().getResource("/resources/image/background_controles_edit.png")).getImage();

        botaoVoltar = new Rectangle(340, 500, 220, 50);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(botaoVoltar.contains(e.getPoint())) {
                    Window janela = SwingUtilities.getWindowAncestor( PainelControles.this);
                    janela.dispose();
                }
            }
        });
        
        setLayout(null);
        
        JLabel voltar = criarOpcao("VOLTAR", 360, 500);
        
        voltar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Window janela = SwingUtilities.getWindowAncestor(PainelControles.this);
        		janela.dispose();
        	}
        });
        
        add(voltar);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // fundo
        g.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);

        // caixa central
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRoundRect(150, 80, 600, 380, 30, 30);

        g.setColor(new Color(210, 190, 140));
        g.drawRoundRect(150, 80, 600, 380, 30, 30);

        // título
        g.setFont(new Font("Serif", Font.BOLD, 42));

        g.setColor(Color.BLACK);
        g.drawString("CONTROLES", 303, 143);

        g.setColor(new Color(210, 190, 140));
        g.drawString("CONTROLES", 300, 140);

        // texto
        g.setFont(new Font("Serif", Font.BOLD, 24));

        int y = 180;

        desenharLinha(g, "W  - mover para cima", y);
        y += 50;

        desenharLinha(g, "S  - mover para baixo", y);
        y += 50;

        desenharLinha(g, "A  - mover para esquerda", y);
        y += 50;

        desenharLinha(g, "D  - mover para direita", y);
        y += 50;
        
        desenharLinha(g, "ENTER - confirmar acao", y);
        y += 50;

        desenharLinha(g, "MOUSE - navegar por interfaces", y);
    }

    private void desenharLinha(Graphics g, String texto, int y) {

        g.setColor(Color.BLACK);
        g.drawString(texto, 243, y + 2);

        g.setColor(Color.WHITE);
        g.drawString(texto, 240, y);
    }
    
    private JLabel criarOpcao(String texto, int x, int y) {
        JLabel label = new JLabel(texto);
        label.setBounds(x, y, 300, 50);
        label.setFont(new Font("Serif", Font.PLAIN, 30));
        label.setForeground(new Color(210, 190, 140));

        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // hover
        label.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                label.setText("► " + texto);
                label.setForeground(new Color(255, 220, 120));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                label.setText(texto);
                label.setForeground(new Color(210, 190, 140));
            }
        });
        return label;
    }

}