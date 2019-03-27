package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUIClient {
    private JFrame frame = new JFrame("Tema3 Client");
    private JPanel downPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel centerPanel = new JPanel();

    private JLabel newClientL = new JLabel("Client nou:");
    private JLabel numeClientL = new JLabel("Nume:");
    private JLabel prenumeClientL = new JLabel("Prenume:");
    private JLabel CNPClientL = new JLabel("CNP:");
    private JLabel adresaClientL = new JLabel("Adresa:");
    private JLabel emailClientL = new JLabel("E-mail:");
    private JLabel telefonClientL = new JLabel("Telefon:");

    private JTextField numeClient = new JTextField();
    private JTextField prenumeClient = new JTextField();
    private JTextField CNPClient = new JTextField();
    private JTextField adresaClient = new JTextField();
    private JTextField emailClient = new JTextField();
    private JTextField telefonClient = new JTextField();

    private JLabel comandaNoua = new JLabel("Comanda noua:");
    private JLabel CNPcomandaL = new JLabel("CNP Client:");
    private JLabel idProdusComandaL = new JLabel("ID produs:");
    private JLabel cantitateProdusComandaL = new JLabel("Cantitate produs:");
    //private JLabel dataComandaL=new JLabel("Data comanda:");

    private JTextField CNPcomanda = new JTextField();
    private JTextField idProdusComanda = new JTextField();
    private JTextField cantitateProdusComanda = new JTextField();

    private JLabel produseStocL = new JLabel("Produse in stoc:");
    private JScrollPane scrollPane = null;

    private JButton clientNouB = new JButton("SIGN UP");
    private JButton comandaNouaB = new JButton("Comanda Noua");
    private JButton produseB = new JButton("Produse in stoc");

    public GUIClient() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(300, 20));
        leftPanel.add(newClientL);
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
        leftPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        leftPanel.add(Box.createRigidArea(new Dimension(110, 0)));

        frame.add(leftPanel, BorderLayout.WEST);

        rightPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(300, 20));
        rightPanel.add(comandaNoua);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel rightSubPanel1 = new JPanel();
        rightSubPanel1.setLayout(new BoxLayout(rightSubPanel1, BoxLayout.X_AXIS));
        rightSubPanel1.add(Box.createRigidArea(new Dimension(10, 0)));
        rightSubPanel1.add(CNPcomandaL);
        setSizeLabel(CNPcomandaL);
        rightSubPanel1.add(CNPcomanda);
        setSizeTextField(CNPcomanda);
        rightSubPanel1.add(Box.createRigidArea(new Dimension(10, 0)));
        rightPanel.add(rightSubPanel1);
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
        rightPanel.add(Box.createRigidArea(new Dimension(0, 300)));
        rightPanel.add(Box.createRigidArea(new Dimension(110, 0)));

        frame.add(rightPanel, BorderLayout.EAST);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setPreferredSize(new Dimension(7000, 20));
        centerPanel.add(produseStocL);
        centerPanel.add(Box.createRigidArea(new Dimension(110, 25)));

        frame.add(centerPanel, BorderLayout.CENTER);

        downPanel.setLayout(new BoxLayout(downPanel, BoxLayout.X_AXIS));
        downPanel.setPreferredSize(new Dimension(1200, 60));
        downPanel.add(Box.createRigidArea(new Dimension(110, 0)));
        downPanel.add(clientNouB);
        downPanel.add(Box.createRigidArea(new Dimension(310, 0)));
        downPanel.add(produseB);
        downPanel.add(Box.createRigidArea(new Dimension(370, 0)));
        downPanel.add(comandaNouaB);
        downPanel.add(Box.createRigidArea(new Dimension(1200, 50)));

        frame.add(downPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void setSizeLabel(JLabel jLabel) {
        jLabel.setPreferredSize(new Dimension(100, 0));
    }

    public void setSizeTextField(JTextField jTextField) {
        jTextField.setPreferredSize(new Dimension(100, 0));
    }

    public void ProduseClientActionListener(ActionListener a) {
        produseB.addActionListener(a);
    }

    public void ClientNouActionListener(ActionListener a) {
        clientNouB.addActionListener(a);
    }

    public void ComandaNouaActionListener(ActionListener a) {
        comandaNouaB.addActionListener(a);
    }

    public void setProduse(JTable jTable) {
        this.scrollPane = new JScrollPane
                (jTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        centerPanel.add(scrollPane);
        frame.add(centerPanel);
        frame.revalidate();
        frame.repaint();
    }

    public void resetProduse() {
        if (scrollPane != null) {
            centerPanel.remove(scrollPane);
            frame.add(centerPanel);
            frame.revalidate();
            frame.repaint();
        }
    }

    public String getNumeClient() throws Exception {
        if (numeClient.getText().equals("")) {
            throw new Exception("Nume invalid!");
        }
        return numeClient.getText();
    }

    public String getPrenumeClient() throws Exception {
        if (prenumeClient.getText().equals("")) {
            throw new Exception("Prenume invalid!");
        }
        return prenumeClient.getText();
    }

    public long getCNPClient() throws Exception {

        try {
            return (Long.parseLong(CNPClient.getText()));
        } catch (Exception e) {
            throw new Exception("CNP incorect");
        }
    }

    public String getAdresaClient() {
        return adresaClient.getText();
    }

    public String getEmailClient() {
        return emailClient.getText();
    }

    public int getTelefonClient() throws Exception {

        try {
            return (Integer.parseInt(telefonClient.getText()));
        } catch (Exception e) {
            throw new Exception("Numar de telefon incorect");
        }
    }

    public long getCNPcomanda() throws Exception {

        try {
            return Long.parseLong(CNPcomanda.getText());
        } catch (Exception e) {
            throw new Exception("CNP incorect");
        }
    }

    public int getIdProdusComanda() throws Exception {

        try {
            return Integer.parseInt(idProdusComanda.getText());
        } catch (Exception e) {
            throw new Exception("ID incorect");
        }
    }

    public int getCantitateProdusComanda() throws Exception {
        try {
            return Integer.parseInt(cantitateProdusComanda.getText());
        } catch (Exception e) {
            throw new Exception("Cantitate incorecta");
        }
    }

    public void setCNPcomanda(long CNPcomanda) {
        this.CNPcomanda.setText("" + CNPcomanda);
    }

    public void setIdProdusComanda(int idProdusComanda) {
        this.idProdusComanda.setText(idProdusComanda + "");
    }

    public void setCantitateProdusComanda(int cantitateProdusComanda) {
        this.cantitateProdusComanda.setText(cantitateProdusComanda + "");
    }
}
