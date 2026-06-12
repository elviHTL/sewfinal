import javax.swing.*;
import java.awt.*;

/**
 * GUI-Ansicht für den PV-Rechner.
 * Zeigt Eingabefelder und berechnet Jahresertrag und Amortisation.
 *
 * @author Elvi
 * @version 1.0
 */
public class PVRechnerView {

    /** Berechnungslogik */
    private PVCalculator calculator = new PVCalculator();

    /**
     * Zeigt das PV-Rechner Fenster an.
     */
    public void zeige() {
        JFrame frame = new JFrame("⚡ PV Rechner");
        frame.setSize(420, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(26, 26, 46));
        frame.setLayout(new GridLayout(8, 2, 10, 10));

        // Felder
        JTextField leistungField      = erstelleField();
        JTextField sonnenstundenField = erstelleField();
        JTextField strompreisField    = erstelleField();
        JTextField kostenField        = erstelleField();

        JLabel ertragsLabel = erstelleErgebnisLabel("--- kWh");
        JLabel amortLabel   = erstelleErgebnisLabel("--- Jahre");
        JLabel fehlerLabel  = new JLabel("");
        fehlerLabel.setForeground(new Color(255, 107, 107));

        // Formular
        frame.add(erstelleLabel("Leistung (kWp):"));
        frame.add(leistungField);
        frame.add(erstelleLabel("Sonnenstunden/Jahr:"));
        frame.add(sonnenstundenField);
        frame.add(erstelleLabel("Strompreis (€/kWh):"));
        frame.add(strompreisField);
        frame.add(erstelleLabel("Anlagenkosten (€):"));
        frame.add(kostenField);

        JButton berechnenBtn = MainApp.erstelleButton("⚡ Berechnen");
        berechnenBtn.addActionListener(e -> {
            fehlerLabel.setText("");
            try {
                double leistung      = Double.parseDouble(leistungField.getText().replace(",", "."));
                double sonnenstunden = Double.parseDouble(sonnenstundenField.getText().replace(",", "."));
                double strompreis    = Double.parseDouble(strompreisField.getText().replace(",", "."));
                double kosten        = Double.parseDouble(kostenField.getText().replace(",", "."));

                double ertrag = calculator.berechneJahresertrag(leistung, sonnenstunden);
                double amort  = calculator.berechneAmortisation(kosten, ertrag, strompreis);

                ertragsLabel.setText(String.format("%.0f kWh", ertrag));
                amortLabel.setText(String.format("%.1f Jahre", amort));
            } catch (NumberFormatException ex) {
                fehlerLabel.setText("⚠ Nur Zahlen eingeben!");
            }
        });

        JButton zurueckBtn = MainApp.erstelleButton("← Zurück");
        zurueckBtn.addActionListener(e -> {
            frame.dispose();
            MainApp.zeigeHauptmenu();
        });

        frame.add(erstelleLabel("Jahresertrag:"));
        frame.add(ertragsLabel);
        frame.add(erstelleLabel("Amortisation:"));
        frame.add(amortLabel);
        frame.add(fehlerLabel);
        frame.add(berechnenBtn);
        frame.add(new JLabel(""));
        frame.add(zurueckBtn);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Erstellt ein einheitliches Eingabefeld.
     *
     * @return Das fertige Textfeld
     */
    private JTextField erstelleField() {
        JTextField tf = new JTextField();
        tf.setBackground(new Color(22, 33, 62));
        tf.setForeground(Color.WHITE);
        tf.setCaretColor(Color.WHITE);
        tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return tf;
    }

    /**
     * Erstellt ein Beschriftungs-Label.
     *
     * @param text Der anzuzeigende Text
     * @return Das fertige Label
     */
    private JLabel erstelleLabel(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(new Color(200, 200, 200));
        l.setFont(new Font("Arial", Font.PLAIN, 13));
        return l;
    }

    /**
     * Erstellt ein hervorgehobenes Ergebnis-Label.
     *
     * @param text Anfänglicher Text
     * @return Das fertige Label
     */
    private JLabel erstelleErgebnisLabel(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(new Color(245, 166, 35));
        l.setFont(new Font("Arial", Font.BOLD, 14));
        return l;
    }
}