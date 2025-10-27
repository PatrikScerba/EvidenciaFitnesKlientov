package sk.patrikscerba.ui;

import sk.patrikscerba.data.XMLNacitanieKlientov;
import sk.patrikscerba.model.Klient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Trieda ZoznamKlientov zobrazuje všetkých klientov v tabuľke.
 * Umožňuje používateľovi prezerať uložené údaje o klientoch
 * načítané zo súboru klienti.xml.
 */
public class ZoznamKlientov extends JFrame {
    private JPanel hlavnyPanel;
    private JTable tabulkaKlientov;

    /**
     * Konštruktor triedy ZoznamKlientov.
     * Načíta údaje z XML súboru, pripraví tabuľku
     * a zobrazí ich v hlavnom okne.
     */
    public ZoznamKlientov() {
        setTitle("Zoznam všetkých klientov");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Načítanie klientov zo súboru XML
        XMLNacitanieKlientov xmlNacitanieKlientov = new XMLNacitanieKlientov();
        List<Klient> klienti = xmlNacitanieKlientov.nacitajKlientov();

        // Vytvorenie hlavičky tabuľky
        String[] stlpce = {"Meno", "Priezvisko", "Vek", "E-mail", "Telefón", "Dátum narodenia"};
        DefaultTableModel model = new DefaultTableModel(stlpce, 0);

        //Formatovanie dátumu narodenia pre zobrazenie
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        // Naplnenie tabuľky údajmi a každý klient sa pridá na nový riadok
        for (Klient k : klienti) {

            Object[] riadok = {
                    k.getKrstneMeno(),
                    k.getPriezvisko(),
                    k.getVek(),
                    k.getEmail(),
                    k.getTelefonneCislo(),
                    (k.getDatumNarodenia() != null) ? k.getDatumNarodenia().format(formatter) : ""
            };
            model.addRow(riadok);
        }
        // Vytvorenie tabuľky
        tabulkaKlientov = new JTable(model);
        tabulkaKlientov.setFillsViewportHeight(true);
        tabulkaKlientov.setAutoCreateRowSorter(true);

        // Pridanie tabuľky do hlavného panela s posuvníkom
        JScrollPane posuvnik = new JScrollPane(tabulkaKlientov);
        hlavnyPanel = new JPanel(new BorderLayout());
        hlavnyPanel.add(posuvnik, BorderLayout.CENTER);

        setContentPane(hlavnyPanel);
    }
}
