package model;

public class Produs {
    private int id_produs;
    private String nume;
    private String categorie;
    private int cantitate;
    private int pret;

    public Produs(int id_produs, String nume, String categorie, int cantitate, int pret) {
        this.id_produs = id_produs;
        this.nume = nume;
        this.categorie = categorie;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    public Produs() {
        this(0, "", "", 0, 0);
    }

    public int getId_produs() {
        return id_produs;
    }

    public void setId_produs(int id_produs) {
        this.id_produs = id_produs;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "id_produs=" + id_produs +
                ", nume='" + nume + '\'' +
                ", categorie='" + categorie + '\'' +
                ", cantitate=" + cantitate +
                ", pret=" + pret +
                '}';
    }
}
