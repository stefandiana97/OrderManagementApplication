package businessLayer;

import model.Client;
import model.Factura;
import model.Produs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PrintFactura {
    private ArrayList<Factura> facturi;

    public PrintFactura() {
        facturi = new ArrayList<>();
    }

    public ArrayList<Factura> getFacturi() {
        return facturi;
    }

    public void setFacturi(ArrayList<Factura> facturi) {
        this.facturi = facturi;
    }

    public void addFactura(Factura factura) {
        facturi.add(factura);
    }

    /**
     * se printeaza intr-un fisier factura clientului
     *
     * @param factura
     * @param client
     * @param produse
     */
    public void printFacturaComanda(Factura factura, Client client, ArrayList<Produs> produse) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("factura" + factura.getId_factura() + ".txt"));
            out.write(String.format("%-50s %-20s %-10s", "", "Numar factura", factura.getId_factura()));
            out.newLine();
            out.newLine();
            out.write(String.format("Client:"));
            out.newLine();
            out.newLine();
            out.write(String.format("%-10s %-20s", "CNP", client.getCNP()));
            out.newLine();
            out.write(String.format("%-10s %-20s", "Nume:", client.getNume()));
            out.newLine();
            out.write(String.format("%-10s %-20s", "Prenume:", client.getPrenume()));
            out.newLine();
            out.write(String.format("%-10s %-20s", "Adresa:", client.getAdresa()));
            out.newLine();
            out.write(String.format("%-10s %-20s", "Email:", client.getEmail()));
            out.newLine();
            out.write(String.format("%-10s %-20s", "Telefon:", client.getTelefon()));
            out.newLine();
            out.newLine();
            out.write("Produse comandate:");
            out.newLine();
            out.newLine();

            out.write(String.format("%-20s %-20s %-20s %-20s\n", "Id produs", "Nume", "Cantitate", "Pret"));
            for (Produs p : produse) {
                // out.newLine();
                out.write(String.format("%-20s %-20s %-20s %-20s\n", p.getId_produs(), p.getNume(), p.getCantitate(), p.getPret()));
            }
            out.newLine();
            out.newLine();
            out.write(String.format("%-60s %-10s %-10s\n", "", "Total:", factura.getPretTotal()));
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
