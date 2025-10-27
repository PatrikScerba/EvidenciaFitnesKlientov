package sk.patrikscerba.ui;

import sk.patrikscerba.model.Klient;
import sk.patrikscerba.data.XMLNacitanieKlientov;

import javax.swing.*;
import java.util.List;

// Trieda Vymazanie umožňuje vyhľadávať klientov podľa mena a priezviska na účely ich vymazania
public class Vymazanie extends JFrame {
    private JPanel mainPanel;
    private JTextField jTextKrstneMeno;
    private JTextField jTextPriezvisko;
    private JButton hladat;
    private JLabel priezviskoLabel;
    private JLabel krstneMenoLabel;

    // konštruktor triedy Vymazanie a nastavenie akcií tlačidiel
    public Vymazanie() {
        setContentPane(mainPanel);
        setTitle("Vyhľadávanie klienta");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Akcia tlačidla Hľadať a spracovanie vyhľadávania klientov podľa mena alebo priezviska.
        hladat.addActionListener(e -> {
            String krstneMeno = jTextKrstneMeno.getText().trim();
            String priezvisko = jTextPriezvisko.getText().trim();

            //Načítanie klientov z XML a vyhľadávanie zhodných klientov
            XMLNacitanieKlientov xmlNacitanieKlientov = new XMLNacitanieKlientov();
            List<Klient> klienti = xmlNacitanieKlientov.nacitajKlientov();

            // Vyhľadanie klienta podľa mena a priezviska a následné zobrazenie detailov alebo správy o nenájdení
            boolean nasiel = false;
            for (Klient k : klienti) {
                if (k.getKrstneMeno().equalsIgnoreCase(krstneMeno)
                        && k.getPriezvisko().equalsIgnoreCase(priezvisko)) {

                    new DetailKlienta(k,true).setVisible(true);
                    nasiel = true;
                    break;
                }
            }
            if (!nasiel) {
                JOptionPane.showMessageDialog(this,
                        "Klient nebol nájdený.",
                        "Výsledok vyhľadávania",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        });
    }
}


