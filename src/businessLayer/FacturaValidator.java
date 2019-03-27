package businessLayer;

import model.Factura;

public class FacturaValidator implements Validator<Factura> {

    /**
     * se valideaza factura
     *
     * @param factura
     */
    @Override
    public void validate(Factura factura) {
        if (factura.getPretTotal() < 0) {
            throw new IllegalArgumentException("Pretul produselor invalid!");
        }
    }
}
