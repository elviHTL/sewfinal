import javax.swing.*;
import java.awt.*;

/**
 * Hauptklasse der PV Solar App.
 * Startet die Anwendung und zeigt das Hauptmenü.
 *
 * @author Elvi
 * @version 1.0
 */
public class MainApp {

    /**
     * Einstiegspunkt der Anwendung.
     *
     * @param args Kommandozeilenargumente
     */
    public static void main(String[] args) {
        zeigeHauptmenu();
    }

    /**
     * Baut das Hauptmenü auf und zeigt es an.
     */
    public static void zeigeHauptmenu() {
        JFrame frame = new JFrame("☀ PV Solar App");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(26, 26, 46));
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel titel = new JLabel("☀ PV Solar App", SwingConstants.CENTER);
        titel.setFont(new Font("Arial", Font.BOLD, 28));
        titel.setForeground(new Color(245, 166, 35));

        JLabel untertitel = new JLabel("Solarenergie berechnen & spielen", SwingConstants.CENTER);
        untertitel.setFont(new Font("Arial", Font.PLAIN, 14));
        untertitel.setForeground(new Color(170, 170, 170));

        JButton rechnerBtn = erstelleButton("⚡ PV Rechner starten");
        JButton spielBtn   = erstelleButton("🎮 Solar Quiz starten");

        rechnerBtn.addActionListener(e -> {
            frame.dispose();
            new PVRechnerView().zeige();
        });

        spielBtn.addActionListener(e -> {
            frame.dispose();
            new SolarGameView().zeige();
        });

        frame.add(titel);
        frame.add(untertitel);
        frame.add(rechnerBtn);
        frame.add(spielBtn);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Erstellt einen einheitlich gestalteten Button.
     *
     * @param text Der anzuzeigende Button-Text
     * @return Der fertige Button
     */
    public static JButton erstelleButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(22, 33, 62));
        btn.setForeground(new Color(245, 166, 35));
        btn.setFont(new Font("Arial", Font.BOLD, 15));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        return btn;
    }
}