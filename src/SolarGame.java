/**
 * Verwaltet den Spielzustand des Solar-Quiz.
 *
 * @author Keron
 * @version 1.0
 */
public class SolarGame {

    /** Name des Spielers */
    private String spielerName;

    /** Aktuelle Punktzahl */
    private int punkte;

    /** Index der aktuellen Frage */
    private int aktuelleFrageIndex;

    /**
     * Erstellt ein neues Spiel.
     *
     * @param spielerName Name des Spielers
     */
    public SolarGame(String spielerName) {
        this.spielerName = spielerName;
        this.punkte = 0;
        this.aktuelleFrageIndex = 0;
    }

    /**
     * Fügt Punkte hinzu.
     *
     * @param anzahl Anzahl der Punkte
     */
    public void punkteHinzufuegen(int anzahl) {
        punkte += anzahl;
    }

    /**
     * Geht zur nächsten Frage.
     */
    public void naechsteFrage() {
        aktuelleFrageIndex++;
    }

    /**
     * Gibt den Spielernamen zurück.
     *
     * @return Spielername
     */
    public String getSpielerName() {
        return spielerName;
    }

    /**
     * Gibt die aktuelle Punktzahl zurück.
     *
     * @return Punkte
     */
    public int getPunkte() {
        return punkte;
    }

    /**
     * Gibt den Index der aktuellen Frage zurück.
     *
     * @return Frageindex
     */
    public int getAktuelleFrageIndex() {
        return aktuelleFrageIndex;
    }

    /**
     * Prüft ob das Spiel noch läuft.
     *
     * @param gesamtFragen Gesamtanzahl der Fragen
     * @return true wenn noch Fragen übrig sind
     */
    public boolean laeuftNoch(int gesamtFragen) {
        return aktuelleFrageIndex < gesamtFragen;
    }
}