package sk.patrikscerba.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Trieda predstavuje model jedného klienta so základnými údajmi.
 * Obsahuje metódy na formátovanie a parsovanie dátumu narodenia.
 */

public class Klient {

    //Atríbúty klienta
    private String krstneMeno;
    private String priezvisko;
    private int vek;
    private String email;
    private String telefonneCislo;
    private LocalDate datumNarodenia;

    //metóda na formatovanie dátumu
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    //Konštruktor triedy Klient
    public Klient(String krstneMeno, String priezvisko, int vek, String email, String telefonneCislo,LocalDate datumNarodenia) {
        this.krstneMeno = krstneMeno;
        this.priezvisko = priezvisko;
        this.vek = vek;
        this.email = email;
        this.telefonneCislo = telefonneCislo;
        this.datumNarodenia = datumNarodenia;

    }
    // --- Gettre a settre ---
    public String getKrstneMeno() { return krstneMeno; }
    public void setKrstneMeno(String krstneMeno) { this.krstneMeno = krstneMeno; }

    public String getPriezvisko() { return priezvisko; }
    public void setPriezvisko(String priezvisko) { this.priezvisko = priezvisko; }

    public int getVek() { return vek; }
    public void setVek(int vek) { this.vek = vek; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefonneCislo() { return telefonneCislo; }
    public void setTelefonneCislo(String telefonneCislo) { this.telefonneCislo = telefonneCislo; }

    public LocalDate getDatumNarodenia() { return datumNarodenia; }
    public void setDatumNarodenia(LocalDate datumNarodenia) { this.datumNarodenia = datumNarodenia; }

    public String getDatumNarodeniaFormatted() {
        return datumNarodenia != null ? datumNarodenia.format(FORMATTER) : "";
    }
    // Metóda, ktorá nastaví dátum narodenia z reťazca
    public void parseDatumNarodenia(String text) {
        if (text == null || text.isEmpty()) return;
        try {
            this.datumNarodenia = LocalDate.parse(text, FORMATTER);
        } catch (Exception e) {
            this.datumNarodenia = LocalDate.now();
        }
    }
    //prepísanie metódy toString pre lepšie zobrazenie klienta
    @Override
    public String toString() {
        return krstneMeno + " " + priezvisko + " (" + vek + " rokov)";
    }
}
