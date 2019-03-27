package model;

import java.sql.Timestamp;

public class Factura {
    private int id_factura;
    private int id_client;
    private int pretTotal;
    private Timestamp data;

    public Factura(int id_factura, int id_client, int pretTotal, Timestamp data) {
        this.id_factura = id_factura;
        this.id_client = id_client;
        this.pretTotal = pretTotal;
        this.data = data;
    }

    public Factura() {
        this(0, 0, 0, new Timestamp(0));
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

    public int getPretTotal() {
        return pretTotal;
    }

    public void setPretTotal(int pretTotal) {
        this.pretTotal = pretTotal;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id_factura=" + id_factura +
                ", id_client=" + id_client +
                ", pretTotal=" + pretTotal +
                ", data=" + data +
                '}';
    }
}
