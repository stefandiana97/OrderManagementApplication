package model;

public class Client {
    private int id_client;
    private long CNP;
    private String nume;
    private String prenume;
    private String adresa;
    private String email;
    private int telefon;

    public Client(int id_client, long CNP, String nume, String prenume, String adresa, String email, int telefon) {
        this.id_client = id_client;
        this.CNP = CNP;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.email = email;
        this.telefon = telefon;
    }

    public Client() {
        this(0, 0, "", "", "", "", 0);
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public long getCNP() {
        return CNP;
    }

    public void setCNP(long CNP) {
        this.CNP = CNP;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client +
                ", CNP=" + CNP +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", email='" + email + '\'' +
                ", telefon=" + telefon +
                '}';
    }
}
