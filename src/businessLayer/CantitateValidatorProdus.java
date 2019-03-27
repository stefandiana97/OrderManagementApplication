package businessLayer;

import model.Produs;

public class CantitateValidatorProdus implements Validator<Produs> {

    /**
     * se valideaza produsul
     *
     * @param produs
     */
    @Override
    public void validate(Produs produs) {
        if (produs.getNume().equals("") || produs.getCategorie().equals("")) {
            throw new IllegalArgumentException("Date invalide!");
        }
        if (produs.getCantitate() < 0) {
            throw new IllegalArgumentException("Cantitatea produselor invalida!");
        }
    }
}
