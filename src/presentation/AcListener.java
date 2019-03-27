package presentation;

import businessLayer.AbstractBLL;
import dataAccessQueries.AbstractDAO;
import model.Client;
import model.Comanda;
import model.Factura;
import model.Produs;
import reflection.ReflectionPrint;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class AcListener {
    protected static final Logger LOGGER = Logger.getLogger(AcListener.class.getName());

    private GUITabbedAdministrator GUITabbedAdministrator;
    private GUIClient guiClient;
    private AbstractBLL abstractBLL;
    private JTable jTableClienti;
    private JTable jTableProduse;
    private JTable jTableComenzi;
    private JTable jTableFacturi;

    public AcListener() {
        abstractBLL = new AbstractBLL();
        GUITabbedAdministrator = new GUITabbedAdministrator();
        GUITabbedAdministrator.ClientiAdminActionListener(new ClientiAdmActionListener());
        GUITabbedAdministrator.ProduseAdminActionListener(new ProduseAdmActionListener());
        GUITabbedAdministrator.ComenziAdminActionListener(new ComenziAdmActionListener());
        GUITabbedAdministrator.FacturiAdminActionListener(new FacturiAdmActionListener());
        GUITabbedAdministrator.updateClienti(new UpdateClienti());
        GUITabbedAdministrator.deleteClienti(new DeleteClienti());
        GUITabbedAdministrator.updateProduse(new UpdateProduse());
        GUITabbedAdministrator.insertProduse(new InsertProduse());
        GUITabbedAdministrator.deleteProduse(new DeleteProduse());
        GUITabbedAdministrator.updateComenzi(new UpdateComenzi());
        GUITabbedAdministrator.deleteComenzi(new DeleteComenzi());
        GUITabbedAdministrator.updateFacturi(new UpdateFacturi());
        GUITabbedAdministrator.deleteFacturi(new DeleteFacturi());
        guiClient = new GUIClient();
        guiClient.ProduseClientActionListener(new ProduseClActionListener());
        guiClient.ClientNouActionListener(new ClientNouActionListener());
        guiClient.ComandaNouaActionListener(new ComandaNouaActionListener());
    }


    class ClientiAdmActionListener implements ActionListener {

        /**
         * Se preiau clientii din baza de date
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            GUITabbedAdministrator.resetJlabelClient();
            AbstractBLL clientAbstractDAO = new AbstractBLL();
            //AbstractDAO<Client> clientAbstractDAO = new AbstractDAO<>(Client.class);
            List<Object> clientiList = clientAbstractDAO.getClientAbstractDAO();
            if (clientiList.size() != 0) {
                jTableClienti = ReflectionPrint.print_table(clientiList);
            } else {
                jTableClienti = clientAbstractDAO.getClientAbstractDAOgetall();
            }
            ListSelectionModel listSelectionModel = jTableClienti.getSelectionModel();
            listSelectionModel.addListSelectionListener(new ClientiListSelectionListener());
            jTableClienti.setSelectionModel(listSelectionModel);
            jTableClienti.setFillsViewportHeight(true);
            GUITabbedAdministrator.setJlabelClient(jTableClienti);
        }
    }

    class ClientiListSelectionListener implements ListSelectionListener {

        /**
         * se preiau datele din tabela JTable si sunt introduse in interfata
         *
         * @param e
         */
        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            if (lsm.isSelectionEmpty()) {
            } else {
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                int id = 0, telefon = 0;
                long CNP = 0;
                String nume = "", prenume = "", adresa = "", email = "";
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
                        id = Integer.parseInt((String) jTableClienti.getValueAt(i, 0));
                        nume = (String) jTableClienti.getValueAt(i, 2);
                        prenume = (String) jTableClienti.getValueAt(i, 3);
                        CNP = Long.parseLong((String) jTableClienti.getValueAt(i, 1));
                        adresa = (String) jTableClienti.getValueAt(i, 4);
                        email = (String) jTableClienti.getValueAt(i, 5);
                        telefon = Integer.parseInt((String) jTableClienti.getValueAt(i, 6));
                    }
                }
                GUITabbedAdministrator.setIdClient(id);
                GUITabbedAdministrator.setNumeClient(nume);
                GUITabbedAdministrator.setPrenumeClient(prenume);
                GUITabbedAdministrator.setCNPClient(CNP);
                GUITabbedAdministrator.setAdresaClient(adresa);
                GUITabbedAdministrator.setEmailClient(email);
                GUITabbedAdministrator.setTelefonClient(telefon);
            }
        }
    }


    class ProduseAdmActionListener implements ActionListener {

        /**
         * Se preiau produsele din baza de date
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            GUITabbedAdministrator.resetJlabelProdus();

            AbstractBLL produsAbstractDAO = new AbstractBLL();
            //AbstractDAO<Produs> produsAbstractDAO = new AbstractDAO<>(Produs.class);
            List<Object> produseList = produsAbstractDAO.getProdusAbstractDAO();
            if (produseList.size() != 0) {
                jTableProduse = ReflectionPrint.print_table(produseList);
            } else {
                jTableProduse = produsAbstractDAO.getProdusAbstractDAOgetall();
            }
            ListSelectionModel listSelectionModel = jTableProduse.getSelectionModel();
            listSelectionModel.addListSelectionListener(new ProduseListSelectionListener());
            jTableProduse.setSelectionModel(listSelectionModel);
            jTableProduse.setFillsViewportHeight(true);
            GUITabbedAdministrator.setJlabelProdus(jTableProduse);
        }
    }

    class ProduseListSelectionListener implements ListSelectionListener {

        /**
         * se preiau datele din tabela JTable si sunt introduse in interfata
         *
         * @param e
         */
        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            if (lsm.isSelectionEmpty()) {
            } else {
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                int id = 0, cantitate = 0, pret = 0;
                String nume = "", categorie = "";
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
                        id = Integer.parseInt((String) jTableProduse.getValueAt(i, 0));
                        nume = (String) jTableProduse.getValueAt(i, 1);
                        categorie = (String) jTableProduse.getValueAt(i, 2);
                        cantitate = Integer.parseInt((String) jTableProduse.getValueAt(i, 3));
                        pret = Integer.parseInt((String) jTableProduse.getValueAt(i, 4));
                    }
                }
                GUITabbedAdministrator.setIdProdus(id);
                GUITabbedAdministrator.setNumeProdus(nume);
                GUITabbedAdministrator.setCategorieProdus(categorie);
                GUITabbedAdministrator.setCantitateProdus(cantitate);
                GUITabbedAdministrator.setPretProdus(pret);
            }
        }
    }


    class ComenziAdmActionListener implements ActionListener {

        /**
         * Se preiau comenzile din baza de date
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            GUITabbedAdministrator.resetJlabelComanda();

            AbstractBLL comandaAbstractDAO = new AbstractBLL();
            //AbstractDAO<Comanda> comandaAbstractDAO = new AbstractDAO<>(Comanda.class);
            List<Object> comenziList = comandaAbstractDAO.getComandaAbstractDAO();
            if (comenziList.size() != 0) {
                jTableComenzi = ReflectionPrint.print_table(comenziList);
            } else {
                jTableComenzi = comandaAbstractDAO.getComandaAbstractDAOgetall();
            }
            ListSelectionModel listSelectionModel = jTableComenzi.getSelectionModel();
            listSelectionModel.addListSelectionListener(new ComenziListSelectionListener());
            jTableComenzi.setSelectionModel(listSelectionModel);
            jTableComenzi.setFillsViewportHeight(true);
            GUITabbedAdministrator.setJlabelComanda(jTableComenzi);
        }
    }

    class ComenziListSelectionListener implements ListSelectionListener {

        /**
         * se preiau datele din tabela JTable si sunt introduse in interfata
         *
         * @param e
         */
        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            if (lsm.isSelectionEmpty()) {
            } else {
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                int id_comanda = 0, id_client = 0, id_factura = 0, id_produs = 0, cantitate = 0;
                Timestamp data = new Timestamp(0);
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
                        id_comanda = Integer.parseInt((String) jTableComenzi.getValueAt(i, 0));
                        id_client = Integer.parseInt((String) jTableComenzi.getValueAt(i, 1));
                        id_factura = Integer.parseInt((String) jTableComenzi.getValueAt(i, 2));
                        id_produs = Integer.parseInt((String) jTableComenzi.getValueAt(i, 3));
                        cantitate = Integer.parseInt((String) jTableComenzi.getValueAt(i, 4));
                        try {
                            DateFormat formatter;
                            formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            Date date = null;
                            date = (Date) formatter.parse((String) jTableComenzi.getValueAt(i, 5));
                            data = new Timestamp(date.getTime());
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                GUITabbedAdministrator.setIdComanda(id_comanda);
                GUITabbedAdministrator.setIdClientComanda(id_client);
                GUITabbedAdministrator.setIdFacturaComanda(id_factura);
                GUITabbedAdministrator.setIdProdusComanda(id_produs);
                GUITabbedAdministrator.setCantitateProdusComanda(cantitate);
                GUITabbedAdministrator.setDataComanda(data);
            }
        }
    }


    class FacturiAdmActionListener implements ActionListener {

        /**
         * Se preiau facturile din baza de date
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            GUITabbedAdministrator.resetJlabelFactura();

            AbstractBLL facturaAbstractDAO = new AbstractBLL();
            //AbstractDAO<Factura> facturaAbstractDAO = new AbstractDAO<>(Factura.class);
            List<Object> facturiList = facturaAbstractDAO.getFacturaAbstractDAO();
            if (facturiList.size() != 0) {
                jTableFacturi = ReflectionPrint.print_table(facturiList);
            } else {
                jTableFacturi = facturaAbstractDAO.getFacturaAbstractDAOgetall();
            }
            ListSelectionModel listSelectionModel = jTableFacturi.getSelectionModel();
            listSelectionModel.addListSelectionListener(new FacturiListSelectionListener());
            jTableFacturi.setSelectionModel(listSelectionModel);
            jTableFacturi.setFillsViewportHeight(true);
            GUITabbedAdministrator.setJlabelFactura(jTableFacturi);
        }
    }


    class FacturiListSelectionListener implements ListSelectionListener {

        /**
         * se preiau datele din tabela JTable si sunt introduse in interfata
         *
         * @param e
         */
        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            if (lsm.isSelectionEmpty()) {
            } else {
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                int id_client = 0, id_factura = 0, pretTotal = 0;
                Timestamp data = new Timestamp(0);
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
                        id_factura = Integer.parseInt((String) jTableFacturi.getValueAt(i, 0));
                        id_client = Integer.parseInt((String) jTableFacturi.getValueAt(i, 1));
                        pretTotal = Integer.parseInt((String) jTableFacturi.getValueAt(i, 2));
                        try {
                            DateFormat formatter;
                            formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            Date date = null;
                            date = (Date) formatter.parse((String) jTableFacturi.getValueAt(i, 3));
                            data = new Timestamp(date.getTime());
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                GUITabbedAdministrator.setIdFactura(id_factura);
                GUITabbedAdministrator.setIdClientFactura(id_client);
                GUITabbedAdministrator.setPretTotalFactura(pretTotal);
                GUITabbedAdministrator.setDataFactura(data);
            }
        }
    }


    class UpdateClienti implements ActionListener {

        /**
         * Se editeaza clientii din baza de date
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int ID = GUITabbedAdministrator.getIdClient();
                String nume = GUITabbedAdministrator.getNumeClient();
                String prenume = GUITabbedAdministrator.getPrenumeClient();
                long CNP = GUITabbedAdministrator.getCNPClient();
                String adresa = GUITabbedAdministrator.getAdresaClient();
                String email = GUITabbedAdministrator.getEmailClient();
                int telefon = GUITabbedAdministrator.getTelefonClient();
                Client client = new Client(ID, CNP, nume, prenume, adresa, email, telefon);
                abstractBLL.updateClienti(client);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! Atentie la formatul acestora. " + ex.getMessage());
                ex.printStackTrace();
                //LOGGER.log(Level.WARNING, "Date invalide!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    class UpdateProduse implements ActionListener {

        /**
         * Se editeaza produsele din baza de date
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int ID = GUITabbedAdministrator.getIdProdus();
                String nume = GUITabbedAdministrator.getNumeProdus();
                String categorie = GUITabbedAdministrator.getCategorieProdus();
                int cantitate = GUITabbedAdministrator.getCantitateProdus();
                int pret = GUITabbedAdministrator.getPretProdus();
                Produs produs = new Produs(ID, nume, categorie, cantitate, pret);
                abstractBLL.updateProduse(produs);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! Atentie la formatul acestora. " + ex.getMessage());
                ex.printStackTrace();
                //LOGGER.log(Level.WARNING, "Date invalide!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    class UpdateComenzi implements ActionListener {

        /**
         * Se editeaza comenzile din baza de date
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int idComanda = GUITabbedAdministrator.getIdComanda();
                int idClient = GUITabbedAdministrator.getIdClientComanda();
                int idProdus = GUITabbedAdministrator.getIdProdusComanda();
                int cantitate = GUITabbedAdministrator.getCantitateProdusComanda();
                Timestamp data = GUITabbedAdministrator.getDataComanda();
                Comanda comanda = new Comanda(idComanda, idClient, 0, idProdus, cantitate, data);
                abstractBLL.updateComenzi(comanda);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
                //LOGGER.log(Level.WARNING, "Date invalide!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    class UpdateFacturi implements ActionListener {

        /**
         * Se editeaza facturile din baza de date
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id_factura = GUITabbedAdministrator.getIdFactura();
                int id_client = GUITabbedAdministrator.getIdClientFactura();
                Timestamp data = GUITabbedAdministrator.getDataFactura();
                Factura factura = new Factura(id_factura, id_client, 0, data);
                abstractBLL.updateFacturi(factura);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
                //LOGGER.log(Level.WARNING, "Date invalide!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    class DeleteClienti implements ActionListener {

        /**
         * Se sterge un client din baza de date
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = GUITabbedAdministrator.getIdClient();
                abstractBLL.deleteClient(id);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
                //LOGGER.log(Level.WARNING, "Date invalide!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    class DeleteProduse implements ActionListener {

        /**
         * Se sterge un produs din baza de date
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = GUITabbedAdministrator.getIdProdus();
                abstractBLL.deleteProdus(id);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
                //LOGGER.log(Level.WARNING, "Date invalide!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    class DeleteComenzi implements ActionListener {

        /**
         * Se sterge o comanda din baza de date
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = GUITabbedAdministrator.getIdComanda();
                abstractBLL.deleteComanda(id);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
                //LOGGER.log(Level.WARNING, "Date invalide!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    class DeleteFacturi implements ActionListener {

        /**
         * Se sterge o factura din baza de date
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = GUITabbedAdministrator.getIdFactura();
                abstractBLL.deleteFactura(id);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
                //LOGGER.log(Level.WARNING, "Date invalide!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    class InsertProduse implements ActionListener {

        /**
         * Se insereaza un produs in baza de date
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String nume = GUITabbedAdministrator.getNumeProdus();
                String categorie = GUITabbedAdministrator.getCategorieProdus();
                int cantitate = GUITabbedAdministrator.getCantitateProdus();
                int pret = GUITabbedAdministrator.getPretProdus();
                Produs produs = new Produs(0, nume, categorie, cantitate, pret);
                abstractBLL.insertProdus(produs);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
                //LOGGER.log(Level.WARNING, "Date invalide!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    class ProduseClActionListener implements ActionListener {

        /**
         * Se preiau produsele din baza de date
         * pentru interfata clienti
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            guiClient.resetProduse();
            AbstractDAO<Produs> produsAbstractDAO = new AbstractDAO<>(Produs.class);
            List<Object> produseList = produsAbstractDAO.getAll();
            if (produseList.size() != 0) {
                jTableProduse = ReflectionPrint.print_table(produseList);
            } else {
                jTableProduse = produsAbstractDAO.getAllResultSet();
            }
            ListSelectionModel listSelectionModel = jTableProduse.getSelectionModel();
            listSelectionModel.addListSelectionListener(new ProduseListClientSelectionListener());
            jTableProduse.setSelectionModel(listSelectionModel);
            jTableProduse.setFillsViewportHeight(true);
            guiClient.setProduse(jTableProduse);
        }
    }

    class ProduseListClientSelectionListener implements ListSelectionListener {

        /**
         * se preiau datele din tabela JTable si sunt introduse in interfata
         *
         * @param e
         */
        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();

            if (lsm.isSelectionEmpty()) {

            } else {
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                int id_produs = 0, cantitate = 1;
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
                        id_produs = Integer.parseInt((String) jTableProduse.getValueAt(i, 0));
                    }
                }
                guiClient.setIdProdusComanda(id_produs);
                guiClient.setCantitateProdusComanda(1);
                long CNP = 0;
                try {
                    CNP = guiClient.getCNPClient();
                } catch (Exception e1) {
                    CNP = 0;
                }
                guiClient.setCNPcomanda(CNP);
            }
        }
    }

    class ClientNouActionListener implements ActionListener {

        /**
         * Se insereaza un client in baza de date
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String nume = guiClient.getNumeClient();
                String prenume = guiClient.getPrenumeClient();
                long CNP = guiClient.getCNPClient();
                String adresa = guiClient.getAdresaClient();
                String email = guiClient.getEmailClient();
                int telefon = guiClient.getTelefonClient();
                Client client = new Client(0, CNP, nume, prenume, adresa, email, telefon);
                abstractBLL.insertClient(client);
                JOptionPane.showMessageDialog(new Frame(),
                        "Bine ai venit! Te asteptam sa comanzi din gama noastra de produse variate.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "A-ti introdus datele gresit! " + ex.getMessage());
                ex.printStackTrace();
                //LOGGER.log(Level.WARNING, "Date invalide!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    class ComandaNouaActionListener implements ActionListener {

        /**
         * Se insereaza o comanda in baza de date
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                long CNP = guiClient.getCNPcomanda();
                int id_produs = guiClient.getIdProdusComanda();
                int cantitate = guiClient.getCantitateProdusComanda();
                Timestamp timeStampDaten = new Timestamp(System.currentTimeMillis());
                Comanda comanda = new Comanda(0, 0, 0, id_produs, cantitate, timeStampDaten);
                abstractBLL.insertComanda(comanda, CNP);
                JOptionPane.showMessageDialog(new Frame(),
                        "Comanda efectuata!");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
                //LOGGER.log(Level.WARNING, "Date invalide! ");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new Frame(),
                        "Introduceti date valide! " + ex.getMessage());
                ex.printStackTrace();
            }

        }
    }
}
