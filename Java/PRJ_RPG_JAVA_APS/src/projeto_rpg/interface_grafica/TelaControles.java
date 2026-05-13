package projeto_rpg.interface_grafica;

import javax.swing.*;

public class TelaControles extends JFrame {

    public TelaControles() {

        setTitle("Controles");

        setSize(900, 600);

        setLocationRelativeTo(null);

        setResizable(false);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new PainelControles());

        setVisible(true);
    }
}