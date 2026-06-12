import javax.swing.*;
import java.awt.*;

/**
 * GUI-Ansicht für das Solar-Quiz Spiel.
 *
 * @author Keron
 * @version 1.0
 */
public class SolarGameView {

    /** Spiellogik */
    private SolarGame spiel = new SolarGame("Spieler");

    /** Fragen und Antworten */
    private GameHelper helper = new GameHelper();

    /**
     * Zeigt das Quiz-Fenster an.
     */
    public void zeige() {
        JFrame frame = new JFrame("🌞 Solar Quiz");
        frame.setSize(420, 380);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(26, 26, 46));
        frame.setLayout(new GridLayout(7, 1, 10, 10));

        JLabel titelLabel      = new JLabel("🌞 Solar Quiz", SwingConstants.CENTER);
        titelLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titelLabel.setForeground(new Color(245, 166, 35));

        JLabel fortschrittLabel = new JLabel("Frage 1 / " + helper.getAnzahlFragen(), SwingConstants.CENTER);
        fortschrittLabel.setForeground(new Color(170, 170, 170));

        JLabel frageLabel = new JLabel(helper.getFrage(0), SwingConstants.CENTER);
        frageLabel.setForeground(Color.WHITE);
        frageLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JTextField antwortField = new JTextField();
        antwortField.setBackground(new Color(22, 33, 62));
        antwortField.setForeground(Color.WHITE);
        antwortField.setCaretColor(Color.WHITE);
        antwortField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel feedbackLabel = new JLabel("", SwingConstants.CENTER);
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 13));

        JLabel punkteLabel = new JLabel("Punkte: 0", SwingConstants.CENTER);
        punkteLabel.setForeground(new Color(245, 166, 35));
        punkteLabel.setFont(new Font("Arial", Font.BOLD, 13));

        JButton antwortBtn = MainApp.erstelleButton("✔ Antworten");
        antwortBtn.addActionListener(e -> {
            String eingabe = antwortField.getText();
            if (eingabe.isEmpty()) {
                feedbackLabel.setText("⚠ Bitte Antwort eingeben!");
                feedbackLabel.setForeground(new Color(255, 107, 107));
                return;
            }

            int index = spiel.getAktuelleFrageIndex();
            if (helper.pruefeAntwort(index, eingabe)) {
                spiel.punkteHinzufuegen(10);
                feedbackLabel.setText("✔ Richtig! +10 Punkte");
                feedbackLabel.setForeground(new Color(76, 175, 80));
            } else {
                feedbackLabel.setText("✘ Falsch! Richtig: " + helper.getAntwort(index));
                feedbackLabel.setForeground(new Color(255, 107, 107));
            }

            spiel.naechsteFrage();
            antwortField.setText("");
            punkteLabel.setText("Punkte: " + spiel.getPunkte());

            if (spiel.laeuftNoch(helper.getAnzahlFragen())) {
                int naechster = spiel.getAktuelleFrageIndex();
                frageLabel.setText(helper.getFrage(naechster));
                fortschrittLabel.setText("Frage " + (naechster + 1) + " / " + helper.getAnzahlFragen());
            } else {
                frame.dispose();
                zeigeErgebnis();
            }
        });

        frame.add(titelLabel);
        frame.add(fortschrittLabel);
        frame.add(frageLabel);
        frame.add(antwortField);
        frame.add(feedbackLabel);
        frame.add(punkteLabel);
        frame.add(antwortBtn);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Zeigt das Endergebnis an.
     */
    private void zeigeErgebnis() {
        JFrame frame = new JFrame("🏆 Ergebnis");
        frame.setSize(380, 280);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(26, 26, 46));
        frame.setLayout(new GridLayout(5, 1, 10, 10));

        int max = helper.getAnzahlFragen() * 10;

        JLabel titel = new JLabel("🏆 Spiel beendet!", SwingConstants.CENTER);
        titel.setFont(new Font("Arial", Font.BOLD, 22));
        titel.setForeground(new Color(245, 166, 35));

        JLabel ergebnis = new JLabel(spiel.getPunkte() + " / " + max + " Punkte", SwingConstants.CENTER);
        ergebnis.setForeground(Color.WHITE);
        ergebnis.setFont(new Font("Arial", Font.BOLD, 16));

        String bewertungText;
        if (spiel.getPunkte() == max)          bewertungText = "🌟 Perfekt!";
        else if (spiel.getPunkte() >= max / 2) bewertungText = "👍 Gut gemacht!";
        else                                   bewertungText = "📚 Noch üben!";

        JLabel bewertung = new JLabel(bewertungText, SwingConstants.CENTER);
        bewertung.setForeground(new Color(245, 166, 35));
        bewertung.setFont(new Font("Arial", Font.BOLD, 16));

        JButton nochmalBtn = MainApp.erstelleButton("🔄 Nochmal");
        nochmalBtn.addActionListener(e -> {
            frame.dispose();
            spiel = new SolarGame("Spieler");
            zeige();
        });

        JButton menuBtn = MainApp.erstelleButton("← Hauptmenü");
        menuBtn.addActionListener(e -> {
            frame.dispose();
            MainApp.zeigeHauptmenu();
        });

        frame.add(titel);
        frame.add(ergebnis);
        frame.add(bewertung);
        frame.add(nochmalBtn);
        frame.add(menuBtn);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}