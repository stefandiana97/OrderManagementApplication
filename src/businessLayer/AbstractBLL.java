package businessLayer;

import dataAccessQueries.AbstractDAO;
import model.Client;
import model.Comanda;
import model.Factura;
import model.Produs;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AbstractBLL {

    private List<Validator<Comanda>> validatorComanda;
    private List<Validator<Produs>> validatorProdus;
    private List<Validator<Client>> validatorClient;
    private List<Validator<Factura>> validatorFactura;
    private AbstractDAO<Comanda> comandaAbstractDAO;
    private AbstractDAO<Produs> produsAbstractDAO;
    private AbstractDAO<Client> clientAbstractDAO;
    private AbstractDAO<Factura> facturaAbstractDAO;
    private PrintFactura printFactura;

    public AbstractBLL() {
        printFactura = new PrintFactura();
        comandaAbstractDAO = new AbstractDAO<>(Comanda.class);
        produsAbstractDAO = new AbstractDAO<>(Produs.class);
        clientAbstractDAO = new AbstractDAO<>(Client.class);
        facturaAbstractDAO = new AbstractDAO<>(Factura.class);
        validatorComanda = new ArrayList<Validator<Comanda>>();
        validatorProdus = new ArrayList<Validator<Produs>>();
        validatorClient = new ArrayList<Validator<Client>>();
        validatorFactura = new ArrayList<Validator<Factura>>();
        validatorComanda.add(new CantitateValidatorComanda());
        validatorProdus.add(new CantitateValidatorProdus());
        validatorClient.add(new EmailValidator());
        validatorFactura.add(new FacturaValidator());
    }

    /**
     * Se verifica existenta unui client pe baza id-ului
     * in caz afirmativ se returneaza
     * in caz negativ se creeaza o exceptie
     *
     * @param id
     * @return Client
     */
    public Client findByIdClient(int id) {
        Client obj = clientAbstractDAO.findById(id, "id_client");
        if (obj == null) {
            throw new NoSuchElementException("The client with id=" + id + " was not found.");
        }
        return obj;
    }

    /**
     * Se verifica existenta unui client pe baza CNP-ului
     * in caz afirmativ se returneaza
     * in caz negativ se creeaza o exceptie
     *
     * @param cnp
     * @return
     */
    public Client findByCNPClient(long cnp) {
        List<Client> obj = clientAbstractDAO.SelectQuery(cnp, "CNP");
        if (obj.size() == 0) {
            throw new NoSuchElementException("CNP incorect.");
        }
        return obj.get(0);
    }

    /**
     * Se verifica existenta unui produs pe baza id-ului
     * in caz afirmativ se returneaza
     * in caz negativ se creeaza o exceptie
     *
     * @param id
     * @return
     */
    public Produs findByIdProdus(int id) {
        Produs obj = produsAbstractDAO.findById(id, "id_produs");
        if (obj == null) {
            throw new NoSuchElementException("The product with id=" + id + " was not found. ");
        }
        return obj;
    }

    /**
     * Se verifica existenta unei comenzi pe baza id-ului
     * in caz afirmativ se returneaza
     * in caz negativ se creeaza o exceptie
     *
     * @param id
     * @return
     */
    public Comanda findByIdComanda(int id) {
        Comanda obj = comandaAbstractDAO.findById(id, "id_comanda");
        if (obj == null) {
            throw new NoSuchElementException("The order with id=" + id + " was not found. ");
        }
        return obj;
    }

    /**
     * Se verifica existenta unei facturi pe baza id-ului
     * in caz afirmativ se returneaza
     * in caz negativ se creeaza o exceptie
     *
     * @param id
     * @return
     */
    public Factura findByIdFactura(int id) {
        Factura obj = facturaAbstractDAO.findById(id, "id_factura");
        if (obj == null) {
            throw new NoSuchElementException("The F with id=" + id + " was not found. ");
        }
        return obj;
    }

    /**
     * Se apeleaza metoda de inserare din AbstractDAO
     * a unui client dupa validarea acestuia
     *
     * @param client
     */
    public void insertClient(Client client) {
        int id = clientAbstractDAO.getLastId();
        client.setId_client(id);
        for (Validator<Client> v : validatorClient) {
            v.validate(client);
        }
        clientAbstractDAO.insertQuery(client);
    }

    /**
     * Se apeleaza metoda de inserare din AbstractDAO
     * a unui produs dupa validarea acestuia
     *
     * @param produs
     */
    public void insertProdus(Produs produs) {
        int id = produsAbstractDAO.getLastId();
        produs.setId_produs(id);
        for (Validator<Produs> v : validatorProdus) {
            v.validate(produs);
        }
        produsAbstractDAO.insertQuery(produs);
    }

    /**
     * Se apeleaza metoda de inserare din AbstractDAO
     * a unei comenzi dupa validarea acesteia
     * Se verifica existenta unei facturi pentru clientul respectiv
     * in caz afirmativ se adauga in factura respectiva comanda
     * in caz negativ se adauga o noua comanda
     *
     * @param comanda
     * @param CNP
     */
    public void insertComanda(Comanda comanda, long CNP) {
        int id = comandaAbstractDAO.getLastId();
        comanda.setId_comanda(id);
        findByCNPClient(CNP);
        int id_client = clientAbstractDAO.SelectQuery(CNP, "CNP").get(0).getId_client();
        comanda.setId_client(id_client);
        Factura factura;
        try {
            factura = facturaAbstractDAO.SelectQuery(id_client, "id_client").get(0);
            comanda.setId_factura(factura.getId_factura());
        } catch (Exception ex) {
            insertFactura(new Factura(0, id_client, 0, comanda.getData()));
            factura = facturaAbstractDAO.SelectQuery(id_client, "id_client").get(0);
            comanda.setId_factura(factura.getId_factura());
        }
        for (Validator<Comanda> v : validatorComanda) {
            v.validate(comanda);
        }
        comandaAbstractDAO.insertQuery(comanda);
        updateProdusComanda(comanda);
        updateFactura(factura.getId_factura());
        calculeazaPret(factura);
        printFactura.printFacturaComanda(factura, clientAbstractDAO.SelectQuery(factura.getId_client(), "id_client").get(0), produseComandate(factura));
    }

    /**
     * Se apeleaza medota de inserare din AbstractDAO
     * a unei facturi dupa validarea acesteia
     *
     * @param factura
     */
    public void insertFactura(Factura factura) {
        int id = facturaAbstractDAO.getLastId();
        factura.setId_factura(id);
        for (Validator<Factura> v : validatorFactura) {
            v.validate(factura);
        }
        facturaAbstractDAO.insertQuery(factura);
    }

    /**
     * Se apeleaza medota de update din AbstractDAO
     * a unui client dupa validarea acestuia
     *
     * @param client
     */
    public void updateClienti(Client client) {
        findByIdClient(client.getId_client());
        for (Validator<Client> v : validatorClient) {
            v.validate(client);
        }
        clientAbstractDAO.updateQuery(client);
    }

    /**
     * Se apeleaza medota de update din AbstractDAO
     * a unui produs dupa validarea acestuia
     *
     * @param produs
     */
    public void updateProduse(Produs produs) {
        findByIdProdus(produs.getId_produs());
        for (Validator<Produs> v : validatorProdus) {
            v.validate(produs);
        }
        produsAbstractDAO.updateQuery(produs);
    }

    /**
     * Se apeleaza metoda de update din AbstractDAO
     * a unui produs dupa validarea acestuia
     *
     * @param comanda
     */
    public void updateProdusComanda(Comanda comanda) {
        findByIdProdus(comanda.getId_produs());
        findByIdComanda(comanda.getId_comanda());
        Produs produs = produsAbstractDAO.SelectQuery(comanda.getId_produs(), "id_produs").get(0);
        produs.setCantitate(produs.getCantitate() - comanda.getCantitate());
        produsAbstractDAO.updateQuery(produs);
    }

    /**
     * Se apeleaza metoda de update din AbstractDAO
     * a unei comenzi dupa validarea acesteia
     *
     * @param comand
     */
    public void updateComenzi(Comanda comand) {
        findByIdProdus(comand.getId_produs());
        findByIdComanda(comand.getId_comanda());
        Comanda comand2 = comandaAbstractDAO.SelectQuery(comand.getId_comanda(), "id_comanda").get(0);
        Produs produs = produsAbstractDAO.SelectQuery(comand.getId_produs(), "id_produs").get(0);
        produs.setCantitate(produs.getCantitate() + comand2.getCantitate());
        updateProduse(produs);
        Factura factura;
        try {
            factura = facturaAbstractDAO.SelectQuery(comand.getId_client(), "id_client").get(0);
            comand.setId_factura(factura.getId_factura());
        } catch (Exception ex) {
            insertFactura(new Factura(0, comand.getId_client(), 0, comand.getData()));
            factura = facturaAbstractDAO.SelectQuery(comand.getId_client(), "id_client").get(0);
            comand.setId_factura(factura.getId_factura());
        }
        for (Validator<Comanda> v : validatorComanda) {
            v.validate(comand);
        }
        comandaAbstractDAO.updateQuery(comand);
        calculeazaPret(factura);
        calculeazaPret(facturaAbstractDAO.SelectQuery(comand2.getId_client(), "id_client").get(0));
        updateProdusComanda(comand);
        //printFactura.printFacturaComanda(factura, clientAbstractDAO.SelectQuery(factura.getId_client(), "id_client").get(0), produseComandate(factura));

    }

    /**
     * Se apeleaza medota de update din AbstractDAO
     * a unui produs dupa validarea acestuia
     *
     * @param id
     */
    public void updateFactura(int id) {
        Factura factura = facturaAbstractDAO.SelectQuery(id, "id_factura").get(0);
        findByIdClient(factura.getId_client());
        Timestamp timeStampDate = new Timestamp(System.currentTimeMillis());
        factura.setData(timeStampDate);
        calculeazaPret(factura);
    }

    /**
     * Se apeleaza medota de update din AbstractDAO
     * a unei Facturi dupa validarea acesteia
     *
     * @param factura
     */
    public void updateFacturi(Factura factura) {
        findByIdFactura(factura.getId_factura());
        findByIdClient(factura.getId_client());
        calculeazaPret(factura);
    }

    /**
     * Se calculeaza pretul total al unei facturi
     *
     * @param factura
     */
    public void calculeazaPret(Factura factura) {
        List<Comanda> comandaList = comandaAbstractDAO.SelectQuery(factura.getId_factura(), "id_factura");
        factura.setPretTotal(0);
        for (Comanda c : comandaList) {
            factura.setPretTotal(factura.getPretTotal() + c.getCantitate() * produsAbstractDAO.SelectQuery(c.getId_produs(), "id_produs").get(0).getPret());
        }
        if (factura.getPretTotal() != 0) {
            facturaAbstractDAO.updateQuery(factura);
            printFactura.printFacturaComanda(factura, clientAbstractDAO.SelectQuery(factura.getId_client(), "id_client").get(0), produseComandate(factura));
        } else {
            deleteFactura(factura.getId_factura());
        }
    }

    /**
     * Se determina produsele comandate
     *
     * @param factura
     * @return ArrayList<Produs>
     */
    public ArrayList<Produs> produseComandate(Factura factura) {
        List<Comanda> comandaList = comandaAbstractDAO.SelectQuery(factura.getId_factura(), "id_factura");
        ArrayList<Produs> produse = new ArrayList<>();
        for (Comanda c : comandaList) {
            Produs produs = produsAbstractDAO.SelectQuery(c.getId_produs(), "id_produs").get(0);
            produs.setCantitate(c.getCantitate());
            for (int i = 0; i < produse.size(); i++) {
                if (produse.get(i).getId_produs() == produs.getId_produs()) {
                    int cantitate = produse.get(i).getCantitate();
                    produse.remove(i);
                    produs.setCantitate(produs.getCantitate() + cantitate);
                }
            }
            produse.add(produs);
        }
        return produse;
    }

    /**
     * Se verifica daca clientul cu id respectiv exista
     * in caz afirmativ se sterge din tabela
     *
     * @param id
     */
    public void deleteClient(int id) {
        Client client = findByIdClient(id);
        clientAbstractDAO.deleteQuery(client);
    }

    /**
     * Se verifica daca produsul cu id respectiv exista
     * in caz afirmativ se sterge din tabela
     *
     * @param id
     */
    public void deleteProdus(int id) {
        Produs produs = findByIdProdus(id);
        produsAbstractDAO.deleteQuery(produs);
    }

    /**
     * Se verifica daca comanda cu id respectiv exista
     * in caz afirmativ se sterge din tabela
     *
     * @param id
     */
    public void deleteComanda(int id) {
        Comanda comanda = findByIdComanda(id);
        comandaAbstractDAO.deleteQuery(comanda);
        calculeazaPret(facturaAbstractDAO.SelectQuery(comanda.getId_factura(), "id_factura").get(0));
    }

    /**
     * Se verifica daca factura cu id respectiv exista
     * in caz afirmativ se sterge din tabela
     *
     * @param id
     */
    public void deleteFactura(int id) {
        Factura factura = findByIdFactura(id);
        facturaAbstractDAO.deleteQuery(factura);
        printFactura.printFacturaComanda(factura, clientAbstractDAO.SelectQuery(factura.getId_client(), "id_client").get(0), produseComandate(new Factura()));

    }

    /**
     * se preiau toate comenzile din baza de date
     *
     * @return List<Object>
     */
    public List<Object> getComandaAbstractDAO() {
        return comandaAbstractDAO.getAll();
    }

    /**
     * se preiau toate produsele din baza de date
     *
     * @return List<Object>
     */
    public List<Object> getProdusAbstractDAO() {
        return produsAbstractDAO.getAll();
    }

    /**
     * se preiau toti clientii din baza de date
     *
     * @return List<Object>
     */
    public List<Object> getClientAbstractDAO() {
        return clientAbstractDAO.getAll();
    }

    /**
     * se preiau toate facturile din baza de date
     *
     * @return List<Object>
     */
    public List<Object> getFacturaAbstractDAO() {
        return facturaAbstractDAO.getAll();
    }

    /**
     * se preiau toate comenzile din baza de date
     * sub forma de JTable
     *
     * @return List<JTable>
     */
    public JTable getComandaAbstractDAOgetall() {
        return comandaAbstractDAO.getAllResultSet();
    }

    /**
     * se preiau toate produsele din baza de date
     * sub forma de JTable
     *
     * @return List<JTable>
     */
    public JTable getProdusAbstractDAOgetall() {
        return produsAbstractDAO.getAllResultSet();
    }

    /**
     * se preiau toti clientii din baza de date
     * sub forma de JTable
     *
     * @return List<JTable>
     */
    public JTable getClientAbstractDAOgetall() {
        return clientAbstractDAO.getAllResultSet();
    }

    /**
     * se preiau toate  facturile din baza de date
     * sub forma de JTable
     *
     * @return List<JTable>
     */
    public JTable getFacturaAbstractDAOgetall() {
        return facturaAbstractDAO.getAllResultSet();
    }
}
