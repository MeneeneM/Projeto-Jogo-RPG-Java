package projeto_rpg.interface_grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaInicial extends JFrame {

    private Musica musica = new Musica();

    public TelaInicial() {

        setTitle("Bem vindo ao RPG em Java");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        musica.iniciarPlaylist();

        // Painel fundo
        JPanel painel = new JPanel() {

            Image imagem = new ImageIcon("src/resources/image/background_2_edit.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                g.drawImage(imagem, 0, 0,
                        getWidth(), getHeight(), this);

                // Escurecer fundo
                g.setColor(new Color(0, 0, 0, 120));
                g.fillRect(0, 0, getWidth(), getHeight());
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Sombra título
                g2.setFont(new Font("Serif", Font.BOLD, 48));
                g2.setColor(new Color(0, 0, 0, 180));
                g2.drawString("Bem vindo ao RPG em Java", 177, 107);

                // Título
                g2.setColor(Color.WHITE);

                g2.drawString("Bem vindo ao RPG em Java", 170, 100);
            }
        };

        painel.setLayout(null);

        // Opções do menu
        JLabel jogar = criarOpcao("JOGAR", 360, 220);
        JLabel controles = criarOpcao("CONSTROLES", 320, 290);
        JLabel creditos = criarOpcao("CRÉDITOS", 340, 360);
        JLabel sair = criarOpcao("SAIR", 370, 420);

        // Eventos
        
        jogar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
                new TelaCriarPersonagem();
                dispose();
            }
        });

        controles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new TelaControles();
            }
        });
        
        creditos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new TelaCreditos();
            }
        });

        sair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        painel.add(jogar);

        painel.add(controles);
        
        painel.add(creditos);

        painel.add(sair);

        // Copyright
        JLabel copyright = new JLabel("──────────  © 2026 Jogo RPG JAVA  ──────────", SwingConstants.CENTER);

        copyright.setBounds(0, 530, 900, 30);

        copyright.setFont(new Font("Arial", Font.PLAIN, 14));

        copyright.setForeground(new Color(255, 255, 255, 180));

        painel.add(copyright);

        add(painel);

        setVisible(true);
    }

    // Método criar opção
    private JLabel criarOpcao(String texto, int x, int y) {

        JLabel label = new JLabel(texto);

        label.setBounds(x, y, 400, 50);

        label.setFont(new Font("Serif", Font.PLAIN, 30));

        label.setForeground(
                new Color(210, 190, 140));

        label.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        // Hover
        label.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                label.setText("► " + texto);

                label.setForeground(
                        new Color(255, 220, 120));
            }

            @Override
            public void mouseExited(MouseEvent e) {

                label.setText(texto);

                label.setForeground(
                        new Color(210, 190, 140));
            }
        });

        return label;
    }
}