package org.cuenta;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class Cuenta {

    private BigDecimal monto;
    private CalculadoraDescuento descuento;

    public Cuenta(CalculadoraDescuento descuento, BigDecimal monto) {
        this.descuento = descuento;
        this.monto = monto;
    }
    public BigDecimal obtenerMontoCargo() {
        return this.monto;
    }

    public BigDecimal pagar() {
        BigDecimal descuento = new BigDecimal(1).subtract(this.descuento.obtenerMontoDescuento()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal montoPagar = this.monto.multiply(descuento).setScale(2);
        return montoPagar;
    }

}