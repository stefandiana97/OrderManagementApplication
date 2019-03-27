package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUITabbedAdministrator {

    protected static final Logger LOGGER = Logger.getLogger(GUIAdministrator.class.getName());
    private JFrame frame = new JFrame("Tema3 Administrator");

    private JPanel ClientJPanel = new JPanel();
    private JPanel ProdusJPanel = new JPanel();
    private JPanel ComandaJPanel = new JPanel();
    private JPanel FacturaJPanel = new JPanel();

    private JPanel downPanel = new JPanel();
    private JPanel downPanelProdus = new JPanel();
    private JPanel downPanelComanda = new JPanel();
    private JPanel downPanelFactura = new JPanel();

    private JPanel centerPanel = new JPanel();
    private JPanel centerPanelClient = new JPanel();
    private JPanel centerPanelProdus = new JPanel();
    private JPanel centerPanelComanda = new JPanel();
    private JPanel centerPanelFactura = new JPanel();

    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel rightPanel1 = new JPanel();
    private JPanel rightPanel2 = new JPanel();
    private JPanel rightPanel0 = new JPanel();

    private JLabel newClientL = new JLabel("Client:");
    private JLabel idClientL = new JLabel("ID:");
    private JLabel numeClientL = new JLabel("Nume:");
    private JLabel prenumeClientL = new JLabel("Prenume:");
    private JLabel CNPClientL = new JLabel("CNP:");
    private JLabel adresaClientL = new JLabel("Adresa:");
    private JLabel emailClientL = new JLabel("E-mail:");
    private JLabel telefonClientL = new JLabel("Telefon:");

    private JTextField idClient = new JTextField();
    private JTextField numeClient = new JTextField();
    private JTextField prenumeClient = new JTextField();
    private JTextField CNPClient = new JTextField();
    private JTextField adresaClient = new JTextField();
    private JTextField emailClient = new JTextField();
    private JTextField telefonClient = new JTextField();

    private JLabel comandaNoua = new JLabel("Comanda:");
    private JLabel idComandaL = new JLabel("ID Comanda:");
    private JLabel idClientComandaL = new JLabel("ID Client:");
    private JLabel idProdusComandaL = new JLabel("ID produs:");
    private JLabel idFacturaComandaL = new JLabel("ID factura:");
    private JLabel cantitateProdusComandaL = new JLabel("Cantitate produs:");
    private JLabel dataComandaL = new JLabel("Data comanda:");

    private JTextField idComanda = new JTextField();
    private JTextField idClientComanda = new JTextField();
    private JTextField idProdusComanda = new JTextField();
    private JTextField idFacturaComanda = new JTextField();
    private JTextField cantitateProdusComanda = new JTextField();
    private JTextField dataComanda = new JTextField();

    private JLabel produsEdit = new JLabel("Produs:");
    private JLabel idProdusL = new JLabel("ID produs:");
    private JLabel numeProdusL = new JLabel("Nume produs:");
    private JLabel categorieProdusL = new JLabel("Categorie:");
    private JLabel cantitateProdusL = new JLabel("Cantitate:");
    private JLabel pretProdusL = new JLabel("Pret:");

    private JTextField idProdus = new JTextField();
    private JTextField numeProdus = new JTextField();
    private JTextField categorieProdus = new JTextField();
    private JTextField cantitateProdus = new JTextField();
    private JTextField pretProdus = new JTextField();

    private JLabel factura = new JLabel("Factura:");
    private JLabel idFacturaL = new JLabel("ID Factura:");
    private JLabel idClientFacturaL = new JLabel("ID Client:");
    private JLabel pretTotalFacturaL = new JLabel("Pret Factura:");
    private JLabel dataFacturaL = new JLabel("Data:");

    private JTextField idFactura = new JTextField();
    private JTextField idClientFactura = new JTextField();
    private JTextField pretTotalFactura = new JTextField();
    private JTextField dataFactura = new JTextField();

    private JScrollPane scrollPane; //= new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private JScrollPane scrollPaneClient;
    private JScrollPane scrollPaneProdus;
    private JScrollPane scrollPaneComanda;
    private JScrollPane scrollPaneFactura;

    private JButton clientEditB = new JButton("Editare");
    private JButton clientDeleteB = new JButton("Stergere");
    private JButton comandaEditB = new JButton("Editare");
    private JButton comandaDeleteB = new JButton("Stergere");
    private JButton produsDeleteB = new JButton("Stergere");
    private JButton produsEditB = new JButton("Editare");
    private JButton produsAddB = new JButton("Adauga");

    private JButton facturaDeleteB = new JButton("Stergere");
    private JButton facturaEditB = new JButton("Editare");

    private JButton produseB = new JButton("Produse");
    private JButton clientiB = new JButton("Clienti");
    private JButton comenziB = new JButton("Comenzi");
    private JButton facturiB = new JButton("Facturi");


    public GUITabbedAdministrator() {

        frame.setSize(1200, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(1, 1));
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Client", ClientPanel());
        tabbedPane.addTab("Produs", ProdusPanel());
        tabbedPane.addTab("Comanda", ComandaPanel());
        tabbedPane.addTab("Factura", FacturaPanel());
        frame.getContentPane().add(tabbedPane);
    }

    public JPanel ClientPanel() {

        ClientJPanel.setLayout(new BorderLayout());

        leftPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(350, 20));
        leftPanel.add(newClientL);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel leftSubPanel0 = new JPanel();
        leftSubPanel0.setLayout(new BoxLayout(leftSubPanel0, BoxLayout.X_AXIS));
        leftSubPanel0.add(Box.createRigidArea(new Dimension(10, 0)));
        leftSubPanel0.add(idClientL);
        setSizeLabel(idClientL);
        leftSubPanel0.add(idClient);
        setSizeTextField(idClient);
        leftSubPanel0.add(Box.createRigidArea(new Dimension(10, 0)));
        leftPanel.add(leftSubPanel0);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel leftSubPanel1 = new JPanel();
        leftSubPanel1.setLayout(new BoxLayout(leftSubPanel1, BoxLayout.X_AXIS));
        leftSubPanel1.add(Box.createRigidArea(new Dimension(10, 0)));
        leftSubPanel1.add(numeClientL);
        setSizeLabel(numeClientL);
        leftSubPanel1.add(numeClient);
        setSizeTextField(numeClient);
        leftSubPanel1.add(Box.createRigidArea(new Dimension(10, 0)));
        leftPanel.add(leftSubPanel1);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel leftSubPanel2 = new JPanel();
        leftSubPanel2.setLayout(new BoxLayout(leftSubPanel2, BoxLayout.X_AXIS));
        leftSubPanel2.add(Box.createRigidArea(new Dimension(10, 0)));
        leftSubPanel2.add(prenumeClientL);
        setSizeLabel(prenumeClientL);
        leftSubPanel2.add(prenumeClient);
        setSizeTextField(prenumeClient);
        leftSubPanel2.add(Box.createRigidArea(new Dimension(10, 0)));
        leftPanel.add(leftSubPanel2);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel leftSubPanel3 = new JPanel();
        leftSubPanel3.setLayout(new BoxLayout(leftSubPanel3, BoxLayout.X_AXIS));
        leftSubPanel3.add(Box.createRigidArea(new Dimension(10, 0)));
        leftSubPanel3.add(CNPClientL);
        setSizeLabel(CNPClientL);
        leftSubPanel3.add(CNPClient);
        setSizeTextField(CNPClient);
        leftSubPanel3.add(Box.createRigidArea(new Dimension(10, 0)));
        leftPanel.add(leftSubPanel3);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel leftSubPanel4 = new JPanel();
        leftSubPanel4.setLayout(new BoxLayout(leftSubPanel4, BoxLayout.X_AXIS));
        leftSubPanel4.add(Box.createRigidArea(new Dimension(10, 0)));
        leftSubPanel4.add(adresaClientL);
        setSizeLabel(adresaClientL);
        leftSubPanel4.add(adresaClient);
        setSizeTextField(adresaClient);
        leftSubPanel4.add(Box.createRigidArea(new Dimension(10, 0)));
        leftPanel.add(leftSubPanel4);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel leftSubPanel5 = new JPanel();
        leftSubPanel5.setLayout(new BoxLayout(leftSubPanel5, BoxLayout.X_AXIS));
        leftSubPanel5.add(Box.createRigidArea(new Dimension(10, 0)));
        leftSubPanel5.add(emailClientL);
        setSizeLabel(emailClientL);
        leftSubPanel5.add(emailClient);
        setSizeTextField(emailClient);
        leftSubPanel5.add(Box.createRigidArea(new Dimension(10, 0)));
        leftPanel.add(leftSubPanel5);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel leftSubPanel6 = new JPanel();
        leftSubPanel6.setLayout(new BoxLayout(leftSubPanel6, BoxLayout.X_AXIS));
        leftSubPanel6.add(Box.createRigidArea(new Dimension(10, 0)));
        leftSubPanel6.add(telefonClientL);
        setSizeLabel(telefonClientL);
        leftSubPanel6.add(telefonClient);
        setSizeTextField(telefonClient);
        leftSubPanel6.add(Box.createRigidArea(new Dimension(10, 0)));
        leftPanel.add(leftSubPanel6);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        ClientJPanel.add(leftPanel, BorderLayout.WEST);

        centerPanelClient.add(Box.createRigidArea(new Dimension(10, 60)));
        centerPanelClient.setLayout(new BoxLayout(centerPanelClient, BoxLayout.Y_AXIS));
//        centerPanelClient.setPreferredSize(new Dimension(900, 100));
//        centerPanelClient.add(Box.createRigidArea(new Dimension(75, 30)));
//
        ClientJPanel.add(centerPanelClient, BorderLayout.EAST);

        downPanel.setLayout(new BoxLayout(downPanel, BoxLayout.X_AXIS));
        downPanel.setPreferredSize(new Dimension(1200, 60));
        downPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        downPanel.add(clientEditB);
        downPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        downPanel.add(clientDeleteB);
        downPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        downPanel.add(clientiB);

        ClientJPanel.add(downPanel, BorderLayout.SOUTH);


        return ClientJPanel;

    }


    public JPanel ProdusPanel() {


        ProdusJPanel.setLayout(new BorderLayout());

        rightPanel1.add(Box.createRigidArea(new Dimension(0, 15)));
        rightPanel1.setLayout(new BoxLayout(rightPanel1, BoxLayout.Y_AXIS));
        rightPanel1.setPreferredSize(new Dimension(350, 20));
        rightPanel1.add(produsEdit);
        rightPanel1.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel rightSubPanel10 = new JPanel();
        rightSubPanel10.setLayout(new BoxLayout(rightSubPanel10, BoxLayout.X_AXIS));
        rightSubPanel10.add(Box.createRigidArea(new Dimension(10, 0)));
        rightSubPanel10.add(idProdusL);
        setSizeLabel(idProdusL);
        rightSubPanel10.add(idProdus);
        setSizeTextField(idProdus);
        rightSubPanel10.add(Box.createRigidArea(new Dimension(10, 0)));
        rightPanel1.add(rightSubPanel10);
        rightPanel1.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel rightSubPanel11 = new JPanel();
        rightSubPanel11.setLayout(new BoxLayout(rightSubPanel11, BoxLayout.X_AXIS));
        rightSubPanel11.add(Box.createRigidArea(new Dimension(10, 0)));
        rightSubPanel11.add(numeProdusL);
        setSizeLabel(numeProdusL);
        rightSubPanel11.add(numeProdus);
        setSizeTextField(numeProdus);
        rightSubPanel11.add(Box.createRigidArea(new Dimension(10, 0)));
        rightPanel1.add(rightSubPanel11);
        rightPanel1.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel rightSubPanel12 = new JPanel();
        rightSubPanel12.setLayout(new BoxLayout(rightSubPanel12, BoxLayout.X_AXIS));
        rightSubPanel12.add(Box.createRigidArea(new Dimension(10, 0)));
        rightSubPanel12.add(categorieProdusL);
        setSizeLabel(categorieProdusL);
        rightSubPanel12.add(categorieProdus);
        setSizeTextField(categorieProdus);
        rightSubPanel12.add(Box.createRigidArea(new Dimension(10, 0)));
        rightPanel1.add(rightSubPanel12);
        rightPanel1.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel rightSubPanel13 = new JPanel();
        rightSubPanel13.setLayout(new BoxLayout(rightSubPanel13, BoxLayout.X_AXIS));
        rightSubPanel13.add(Box.createRigidArea(new Dimension(10, 0)));
        rightSubPanel13.add(cantitateProdusL);
        setSizeLabel(cantitateProdusL);
        rightSubPanel13.add(cantitateProdus);
        setSizeTextField(cantitateProdus);
        rightSubPanel13.add(Box.createRigidArea(new Dimension(10, 0)));
        rightPanel1.add(rightSubPanel13);
        rightPanel1.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel rightSubPanel14 = new JPanel();
        rightSubPanel14.setLayout(new BoxLayout(rightSubPanel14, BoxLayout.X_AXIS));
        rightSubPanel14.add(Box.createRigidArea(new Dimension(10, 0)));
        rightSubPanel14.add(pretProdusL);
        setSizeLabel(pretProdusL);
        rightSubPanel14.add(pretProdus);
        setSizeTextField(pretProdus);
        rightSubPanel14.add(Box.createRigidArea(new Dimension(10, 0)));
        rightPanel1.add(rightSubPanel14);
        rightPanel1.add(Box.createRigidArea(new Dimension(0, 30)));

        rightPanel1.add(Box.createRigidArea(new Dimension(110, 70)));

        ProdusJPanel.add(rightPanel1, BorderLayout.WEST);

        centerPanelProdus.add(Box.createRigidArea(new Dimension(10, 60)));
        centerPanelProdus.setLayout(new BoxLayout(centerPanelProdus, BoxLayout.Y_AXIS));
//        centerPanelProdus.setPreferredSize(new Dimension(800, 100));
//        centerPanelProdus.add(Box.createRigidArea(new Dimension(75, 30)));
//
        ProdusJPanel.add(centerPanelProdus, BorderLayout.EAST);

        downPanelProdus.setLayout(new BoxLayout(downPanelProdus, BoxLayout.X_AXIS));
        downPanelProdus.setPreferredSize(new Dimension(1200, 60));
        downPanelProdus.add(Box.createRigidArea(new Dimension(20, 0)));
        downPanelProdus.add(produsEditB);
        downPanelProdus.add(Box.createRigidArea(new Dimension(5, 50)));
        downPanelProdus.add(produsAddB);
        downPanelProdus.add(Box.createRigidArea(new Dimension(5, 50)));
        downPanelProdus.add(produsDeleteB);
        downPanelProdus.add(Box.createRigidArea(new Dimension(10, 0)));
        downPanelProdus.add(produseB);

        ProdusJPanel.add(downPanelProdus, BorderLayout.SOUTH);

        return ProdusJPanel;

    }

    public JPanel ComandaPanel() {
        ComandaJPanel.setLayout(new BorderLayout());

        rightPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(350, 20));
        rightPanel.add(comandaNoua);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel rightSubPanel0 = new JPanel();
        rightSubPanel0.setLayout(new BoxLayout(rightSubPanel0, BoxLayout.X_AXIS));
        rightSubPanel0.add(Box.createRigidArea(new Dimension(10, 0)));
        rightSubPanel0.add(idComandaL);
        setSizeLabel(idComandaL);
        rightSubPanel0.add(idComanda);
        setSizeTextField(idComanda);
        rightSubPanel0.add(Box.createRigidArea(new Dimension(10, 0)));
        rightPanel.add(rightSubPanel0);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel rightSubPanel1 = new JPanel();
        rightSubPanel1.setLayout(new BoxLayout(rightSubPanel1, BoxLayout.X_AXIS));
        rightSubPanel1.add(Box.createRigidArea(new Dimension(10, 0)));
        rightSubPanel1.add(idClientComandaL);
        setSizeLabel(idClientComandaL);
        rightSubPanel1.add(idClientComanda);
        setSizeTextField(idClientComanda);
        rightSubPanel1.add(Box.createRigidArea(new Dimension(10, 0)));
        rightPanel.add(rightSubPanel1);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel rightSubPanel4 = new JPanel();
        rightSubPanel4.setLayout(new BoxLayout(rightSubPanel4, BoxLayout.X_AXIS));
        rightSubPanel4.add(Box.createRigidArea(new Dimension(10, 0)));
        rightSubPanel4.add(idFacturaComandaL);
        setSizeLabel(idFacturaComandaL);
        rightSubPanel4.add(idFacturaComanda);
        idFacturaComanda.setEditable(false);
        setSizeTextField(idFacturaComanda);
        rightSubPanel4.add(Box.createRigidArea(new Dimension(10, 0)));
        rightPanel.add(rightSubPanel4);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel rightSubPanel2 = new JPanel();
        rightSubPanel2.setLayout(new BoxLayout(rightSubPanel2, BoxLayout.X_AXIS));
        rightSubPanel2.add(Box.createRigidArea(new Dimension(10, 0)));
        rightSubPanel2.add(idProdusComandaL);
        setSizeLabel(idProdusComandaL);
        rightSubPanel2.add(idProdusComanda);
        setSizeTextField(idProdusComanda);
        rightSubPanel2.add(Box.createRigidArea(new Dimension(10, 0)));
        rightPanel.add(rightSubPanel2);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel rightSubPanel3 = new JPanel();
        rightSubPanel3.setLayout(new BoxLayout(rightSubPanel3, BoxLayout.X_AXIS));
        rightSubPanel3.add(Box.createRigidArea(new Dimension(10, 0)));
        rightSubPanel3.add(cantitateProdusComandaL);
        setSizeLabel(cantitateProdusComandaL);
        rightSubPanel3.add(cantitateProdusComanda);
        setSizeTextField(cantitateProdusComanda);
        rightSubPanel3.add(Box.createRigidArea(new Dimension(10, 0)));
        rightPanel.add(rightSubPanel3);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));


        JPanel rightSubPanel5 = new JPanel();
        rightSubPanel5.setLayout(new BoxLayout(rightSubPanel5, BoxLayout.X_AXIS));
        rightSubPanel5.add(Box.createRigidArea(new Dimension(10, 0)));
        rightSubPanel5.add(dataComandaL);
        setSizeLabel(dataComandaL);
        rightSubPanel5.add(dataComanda);
        setSizeTextField(dataComanda);
        rightSubPanel5.add(Box.createRigidArea(new Dimension(10, 0)));
        rightPanel.add(rightSubPanel5);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        rightPanel.add(Box.createRigidArea(new Dimension(110, 70)));

        ComandaJPanel.add(rightPanel, BorderLayout.WEST);

        centerPanelComanda.add(Box.createRigidArea(new Dimension(10, 60)));
        centerPanelComanda.setLayout(new BoxLayout(centerPanelComanda, BoxLayout.Y_AXIS));
//        centerPanelComanda.setPreferredSize(new Dimension(800, 100));
//        centerPanelComanda.add(Box.createRigidArea(new Dimension(75, 30)));
//
        ProdusJPanel.add(centerPanelComanda, BorderLayout.EAST);

        downPanelComanda.setLayout(new BoxLayout(downPanelComanda, BoxLayout.X_AXIS));
        downPanelComanda.setPreferredSize(new Dimension(1200, 60));
        downPanelComanda.add(Box.createRigidArea(new Dimension(70, 0)));
        downPanelComanda.add(comandaEditB);
        downPanelComanda.add(Box.createRigidArea(new Dimension(5, 0)));
        downPanelComanda.add(comandaDeleteB);
        downPanelComanda.add(Box.createRigidArea(new Dimension(5, 0)));
        downPanelComanda.add(comenziB);

        ComandaJPanel.add(downPanelComanda, BorderLayout.SOUTH);

        return ComandaJPanel;
    }

    public JPanel FacturaPanel() {

        FacturaJPanel.setLayout(new BorderLayout());

        rightPanel0.add(Box.createRigidArea(new Dimension(0, 15)));
        rightPanel0.setLayout(new BoxLayout(rightPanel0, BoxLayout.Y_AXIS));
        rightPanel0.setPreferredSize(new Dimension(350, 20));
        rightPanel0.add(factura);
        rightPanel0.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel rightSubPanel00 = new JPanel();
        rightSubPanel00.setLayout(new BoxLayout(rightSubPanel00, BoxLayout.X_AXIS));
        rightSubPanel00.add(Box.createRigidArea(new Dimension(10, 0)));
        rightSubPanel00.add(idFacturaL);
        setSizeLabel(idFacturaL);
        rightSubPanel00.add(idFactura);
        setSizeTextField(idFactura);
        rightSubPanel00.add(Box.createRigidArea(new Dimension(10, 0)));
        rightPanel0.add(rightSubPanel00);
        rightPanel0.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel rightSubPanel01 = new JPanel();
        rightSubPanel01.setLayout(new BoxLayout(rightSubPanel01, BoxLayout.X_AXIS));
        rightSubPanel01.add(Box.createRigidArea(new Dimension(10, 0)));
        rightSubPanel01.add(idClientFacturaL);
        setSizeLabel(idClientFacturaL);
        rightSubPanel01.add(idClientFactura);
        idClientFactura.setEditable(false);
        setSizeTextField(idClientFactura);
        rightSubPanel01.add(Box.createRigidArea(new Dimension(10, 0)));
        rightPanel0.add(rightSubPanel01);
        rightPanel0.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel rightSubPanel02 = new JPanel();
        rightSubPanel02.setLayout(new BoxLayout(rightSubPanel02, BoxLayout.X_AXIS));
        rightSubPanel02.add(Box.createRigidArea(new Dimension(10, 0)));
        rightSubPanel02.add(pretTotalFacturaL);
        setSizeLabel(pretTotalFacturaL);
        rightSubPanel02.add(pretTotalFactura);
        pretTotalFactura.setEditable(false);
        setSizeTextField(pretTotalFactura);
        rightSubPanel02.add(Box.createRigidArea(new Dimension(10, 0)));
        rightPanel0.add(rightSubPanel02);
        rightPanel0.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel rightSubPanel03 = new JPanel();
        rightSubPanel03.setLayout(new BoxLayout(rightSubPanel03, BoxLayout.X_AXIS));
        rightSubPanel03.add(Box.createRigidArea(new Dimension(10, 0)));
        rightSubPanel03.add(dataFacturaL);
        setSizeLabel(dataFacturaL);
        rightSubPanel03.add(dataFactura);
        setSizeTextField(dataFactura);
        rightSubPanel03.add(Box.createRigidArea(new Dimension(10, 0)));
        rightPanel0.add(rightSubPanel03);
        rightPanel0.add(Box.createRigidArea(new Dimension(0, 30)));

        rightPanel0.add(Box.createRigidArea(new Dimension(110, 255)));

        FacturaJPanel.add(rightPanel0, BorderLayout.WEST);

        centerPanelFactura.add(Box.createRigidArea(new Dimension(10, 60)));
        centerPanelFactura.setLayout(new BoxLayout(centerPanelFactura, BoxLayout.Y_AXIS));
//        centerPanelFactura.setPreferredSize(new Dimension(800, 100));
//        centerPanelFactura.add(Box.createRigidArea(new Dimension(75, 30)));
//
        ClientJPanel.add(centerPanelFactura, BorderLayout.EAST);


        downPanelFactura.setLayout(new BoxLayout(downPanelFactura, BoxLayout.X_AXIS));
        downPanelFactura.setPreferredSize(new Dimension(1200, 60));
        downPanelFactura.add(Box.createRigidArea(new Dimension(70, 50)));
        downPanelFactura.add(facturaEditB);
        downPanelFactura.add(Box.createRigidArea(new Dimension(5, 50)));
        downPanelFactura.add(facturaDeleteB);
        downPanelFactura.add(Box.createRigidArea(new Dimension(5, 0)));
        downPanelFactura.add(facturiB);

        FacturaJPanel.add(downPanelFactura, BorderLayout.SOUTH);

        return FacturaJPanel;
    }


    public void setSizeLabel(JLabel jLabel) {
        jLabel.setPreferredSize(new Dimension(100, 0));
    }

    public void setSizeTextField(JTextField jTextField) {
        jTextField.setPreferredSize(new Dimension(50, 0));
    }

    public void updateClienti(ActionListener a) {
        clientEditB.addActionListener(a);
    }

    public void deleteClienti(ActionListener a) {
        clientDeleteB.addActionListener(a);
    }

    public void updateProduse(ActionListener a) {
        produsEditB.addActionListener(a);
    }

    public void insertProduse(ActionListener a) {
        produsAddB.addActionListener(a);
    }

    public void deleteProduse(ActionListener a) {
        produsDeleteB.addActionListener(a);
    }

    public void updateComenzi(ActionListener a) {
        comandaEditB.addActionListener(a);
    }

    public void deleteComenzi(ActionListener a) {
        comandaDeleteB.addActionListener(a);
    }


    public void updateFacturi(ActionListener a) {
        facturaEditB.addActionListener(a);
    }

    public void deleteFacturi(ActionListener a) {
        facturaDeleteB.addActionListener(a);
    }

    public void ClientiAdminActionListener(ActionListener a) {
        clientiB.addActionListener(a);
    }

    public void ProduseAdminActionListener(ActionListener a) {
        produseB.addActionListener(a);
    }

    public void ComenziAdminActionListener(ActionListener a) {
        comenziB.addActionListener(a);
    }

    public void FacturiAdminActionListener(ActionListener a) {
        facturiB.addActionListener(a);
    }


    public void setJlabelClient(JTable jTable) {
        this.scrollPaneClient = new JScrollPane
                (jTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        centerPanelClient.add(scrollPaneClient);
        ClientJPanel.add(centerPanelClient, BorderLayout.CENTER);
        ClientJPanel.revalidate();
        ClientJPanel.repaint();
    }

    public void resetJlabelClient() {
        if (scrollPaneClient != null) {
            centerPanelClient.remove(scrollPaneClient);
            ClientJPanel.add(centerPanelClient, BorderLayout.CENTER);
            ClientJPanel.revalidate();
            ClientJPanel.repaint();
        }
    }

    public void setJlabelProdus(JTable jTable) {
        this.scrollPaneProdus = new JScrollPane
                (jTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        centerPanelProdus.add(scrollPaneProdus);
        ProdusJPanel.add(centerPanelProdus, BorderLayout.CENTER);
        ProdusJPanel.revalidate();
        ProdusJPanel.repaint();

    }

    public void resetJlabelProdus() {
        if (scrollPaneProdus != null) {
            centerPanelProdus.remove(scrollPaneProdus);
            ProdusJPanel.add(centerPanelProdus, BorderLayout.CENTER);
            ProdusJPanel.revalidate();
            ProdusJPanel.repaint();
        }
    }

    public void setJlabelComanda(JTable jTable) {
        this.scrollPaneComanda = new JScrollPane
                (jTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        centerPanelComanda.add(scrollPaneComanda);
        ComandaJPanel.add(centerPanelComanda, BorderLayout.CENTER);
        ComandaJPanel.revalidate();
        ComandaJPanel.repaint();
    }

    public void resetJlabelComanda() {
        if (scrollPaneComanda != null) {
            centerPanelComanda.remove(scrollPaneComanda);
            ComandaJPanel.add(centerPanelComanda, BorderLayout.CENTER);
            ComandaJPanel.revalidate();
            ComandaJPanel.repaint();
        }
    }

    public void setJlabelFactura(JTable jTable) {
        this.scrollPaneFactura = new JScrollPane
                (jTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        centerPanelFactura.add(scrollPaneFactura);
        FacturaJPanel.add(centerPanelFactura, BorderLayout.CENTER);
        FacturaJPanel.revalidate();
        FacturaJPanel.repaint();
    }

    public void resetJlabelFactura() {
        if (scrollPaneFactura != null) {
            centerPanelFactura.remove(scrollPaneFactura);
            FacturaJPanel.add(centerPanelFactura, BorderLayout.CENTER);
            FacturaJPanel.revalidate();
            FacturaJPanel.repaint();
        }
    }


    public int getIdClient() {
        return Integer.parseInt(idClient.getText());
    }

    public String getNumeClient() {
        return numeClient.getText();
    }

    public String getPrenumeClient() {
        return prenumeClient.getText();
    }

    public long getCNPClient() {
        return (Long.parseLong(CNPClient.getText()));
    }

    public String getAdresaClient() {
        return adresaClient.getText();
    }

    public String getEmailClient() {
        return emailClient.getText();
    }

    public int getTelefonClient() {
        return (Integer.parseInt(telefonClient.getText()));
    }

    public void setIdClient(int idClient) {
        this.idClient.setText(idClient + "");
    }

    public void setNumeClient(String numeClient) {
        this.numeClient.setText(numeClient + "");
    }

    public void setPrenumeClient(String prenumeClient) {
        this.prenumeClient.setText(prenumeClient + "");
    }

    public void setCNPClient(long CNPClient) {
        this.CNPClient.setText(CNPClient + "");
    }

    public void setAdresaClient(String adresaClient) {
        this.adresaClient.setText(adresaClient + "");
    }

    public void setEmailClient(String emailClient) {
        this.emailClient.setText(emailClient + "");
    }

    public void setTelefonClient(int telefonClient) {
        this.telefonClient.setText(telefonClient + "");
    }


    public int getIdProdus() {
        return Integer.parseInt(idProdus.getText());
    }

    public String getNumeProdus() {
        return numeProdus.getText();
    }

    public String getCategorieProdus() {
        return categorieProdus.getText();
    }

    public int getCantitateProdus() {
        try {
            return Integer.parseInt(cantitateProdus.getText());
        } catch (IllegalArgumentException ex) {
            LOGGER.log(Level.WARNING, "Cantitatea produselor este incorecta " + ex.getMessage());
            return 0;
        }
    }

    public int getPretProdus() {

        try {
            return Integer.parseInt(pretProdus.getText());
        } catch (IllegalArgumentException ex) {
            LOGGER.log(Level.WARNING, "Pretul produselor este incorect " + ex.getMessage());
            return 0;
        }
    }

    public void setIdProdus(int idProdus) {
        this.idProdus.setText(idProdus + "");
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus.setText(numeProdus + "");
    }

    public void setCategorieProdus(String categorieProdus) {
        this.categorieProdus.setText(categorieProdus + "");
    }

    public void setCantitateProdus(int cantitateProdus) {
        this.cantitateProdus.setText(cantitateProdus + "");
    }

    public void setPretProdus(int pretProdus) {
        this.pretProdus.setText(pretProdus + "");
    }

    public int getIdComanda() {
        return Integer.parseInt(idComanda.getText());
    }

    public int getIdClientComanda() {
        return Integer.parseInt(idClientComanda.getText());
    }

    public int getIdProdusComanda() {
        return Integer.parseInt(idProdusComanda.getText());
    }

    public int getCantitateProdusComanda() {
        return Integer.parseInt(cantitateProdusComanda.getText());
    }

    public Timestamp getDataComanda() {
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = (Date) formatter.parse(dataComanda.getText());
            java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
            return timeStampDate;
        } catch (ParseException e) {
            try {
                DateFormat formatter;
                formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = (Date) formatter.parse(dataComanda.getText() + " 08:00:00.0");
                java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
                return timeStampDate;
            } catch (ParseException e2) {
                LOGGER.log(Level.WARNING, "Data: " + dataComanda.getText() + " are formatul incorect " + e.getMessage());
                Timestamp timeStampDaten = new Timestamp(System.currentTimeMillis());
                return timeStampDaten;
            }
        }
    }

    public void setIdComanda(int idComanda) {
        this.idComanda.setText(idComanda + "");
    }

    public void setIdClientComanda(int idClientComanda) {
        this.idClientComanda.setText(idClientComanda + "");
    }

    public void setIdProdusComanda(int idProdusComanda) {
        this.idProdusComanda.setText(idProdusComanda + "");
    }

    public void setIdFacturaComanda(int idFacturaComanda) {
        this.idFacturaComanda.setText(idFacturaComanda + "");
    }

    public void setCantitateProdusComanda(int cantitateProdusComanda) {
        this.cantitateProdusComanda.setText(cantitateProdusComanda + "");
    }

    public void setDataComanda(Timestamp dataComanda) {
        this.dataComanda.setText(dataComanda + "");
    }


    public int getIdFactura() {
        return Integer.parseInt(idFactura.getText());
    }

    public int getIdClientFactura() {
        return Integer.parseInt(idClientFactura.getText());
    }

    public Timestamp getDataFactura() {
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = (Date) formatter.parse(dataFactura.getText());
            java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
            return timeStampDate;
        } catch (ParseException e) {
            try {
                DateFormat formatter;
                formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = (Date) formatter.parse(dataFactura.getText() + " 08:00:00.0");
                java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
                return timeStampDate;
            } catch (ParseException e2) {
                LOGGER.log(Level.WARNING, "Data: " + dataFactura.getText() + " are formatul incorect " + e.getMessage());
                Timestamp timeStampDaten = new Timestamp(System.currentTimeMillis());
                return timeStampDaten;

            }
        }
    }

    public void setIdFactura(int idFactura) {
        this.idFactura.setText(idFactura + "");
    }

    public void setIdClientFactura(int idClientFactura) {
        this.idClientFactura.setText(idClientFactura + "");
    }

    public void setPretTotalFactura(int pretTotalFactura) {
        this.pretTotalFactura.setText(pretTotalFactura + "");
    }

    public void setDataFactura(Timestamp dataFactura) {
        this.dataFactura.setText(dataFactura + "");
    }

}