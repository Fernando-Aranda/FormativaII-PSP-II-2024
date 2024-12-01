package org.cliente;

import cl.psp.base.CalculadoraDescuento;
import cl.psp.base.Cuenta;

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

    public void ingresarCuenta(Cuenta cuenta) {

        if (cuenta == null) {
            cuentas = new ArrayList();
        }
        cuentas.add(cuenta);
    }

}