package org.cliente;

import org.cuenta.CalculadoraDescuento;
import org.cuenta.Cuenta;

import java.util.*;
import java.math.BigDecimal;

public class Cliente implements CalculadoraDescuento {
    private List cuentas;

    public BigDecimal obtenerMontoDescuento() {
        if (cuentas.size() > 5) {
            return new BigDecimal(0.1);
        } else {
            return new BigDecimal(0.03);
        }
    }

    public List obtenerCuentas() {
        return this.cuentas;
    }

    public void crearCuenta(BigDecimal chargeAmount) {
        Cuenta bill = new Cuenta(this, chargeAmount);
        if (cuentas == null) {
            cuentas = new ArrayList();
        }
        cuentas.add(bill);
    }

}