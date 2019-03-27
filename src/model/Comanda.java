package model;

import java.sql.Timestamp;

public class Comanda {
    private int id_comanda;
    private int id_client;
    private int id_factura;
    private int id_produs;
    private int cantitate;
    private Timestamp data;

    public Comanda(int id_comanda, int id_client, int id_factura, int id_produs, int cantitate, Timestamp data) {
        this.id_comanda = id_comanda;
        this.id_client = id_client;
        this.id_factura = id_factura;
        this.id_produs = id_produs;
        this.cantitate = cantitate;
        this.data = data;
    }

    public Comanda() {
        this(0, 0, 0, 0, 0, new Timestamp(5));
    }

    public int getId_comanda() {
        return id_comanda;
    }

    public void setId_comanda(int id_comanda) {
        this.id_comanda = id_comanda;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_produs() {
        return id_produs;
    }

    public void setId_produs(int id_produs) {
        this.id_produs = id_produs;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "id_comanda=" + id_comanda +
                ", id_client=" + id_client +
                ", id_factura=" + id_factura +
                ", id_produs=" + id_produs +
                ", cantitate=" + cantitate +
                ", data=" + data +
                '}';
    }
}
