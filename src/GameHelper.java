/**
 * Enthält alle Fragen und Antworten für das Solar-Quiz.
 *
 * @author Keron
 * @version 1.0
 */
public class GameHelper {

    /** Liste aller Fragen */
    private String[] fragen = {
            "Wie viel Watt hat ein typisches Solarmodul?",
            "Was bedeutet kWp?",
            "Welche Farbe haben die meisten Solarzellen?",
            "Was wandelt ein Wechselrichter um?",
            "Sonnenstunden in Deutschland pro Jahr?"
    };

    /** Richtige Antworten */
    private String[] antworten = {
            "300",
            "Kilowatt Peak",
            "Dunkelblau",
            "Gleichstrom in Wechselstrom",
            "1600"
    };

    /**
     * Gibt eine Frage zurück.
     *
     * @param index Index der Frage
     * @return Die Frage als Text
     */
    public String getFrage(int index) {
        return fragen[index];
    }

    /**
     * Gibt die richtige Antwort zurück.
     *
     * @param index Index der Frage
     * @return Die richtige Antwort
     */
    public String getAntwort(int index) {
        return antworten[index];
    }

    /**
     * Prüft ob die Antwort korrekt ist.
     *
     * @param index   Index der Frage
     * @param antwort Eingegebene Antwort
     * @return true wenn richtig
     */
    public boolean pruefeAntwort(int index, String antwort) {
        return antworten[index].equalsIgnoreCase(antwort.trim());
    }

    /**
     * Gibt die Anzahl der Fragen zurück.
     *
     * @return Anzahl der Fragen
     */
    public int getAnzahlFragen() {
        return fragen.length;
    }
}