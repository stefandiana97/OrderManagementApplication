package businessLayer;

import dataAccessQueries.AbstractDAO;
import model.Comanda;
import model.Produs;

public class CantitateValidatorComanda implements Validator<Comanda> {
    AbstractDAO abstractDAO = new AbstractDAO(Produs.class);

    /**
     * se valideaza comanda
     *
     * @param comanda
     */
    @Override
    public void validate(Comanda comanda) {
        Produs produs = (Produs) abstractDAO.findById(comanda.getId_produs(), "id_produs");
        if (comanda.getCantitate() > produs.getCantitate()) {
            throw new IllegalArgumentException("Cantitatea dorita depaseste cantitatea din stoc!");
        }
        if (comanda.getCantitate() <= 0) {
            throw new IllegalArgumentException("Cantitatea dorita este invalida!");
        }
    }
}
