package org.test.nuevo;

import org.cliente.Cliente;
import org.cuenta.CalculadoraDescuento;
import org.cuenta.Cuenta;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class TestPagoNuevo {


    @Test
    public void testPago() {

        Cliente cliente = new Cliente();
        //cliente.addCuenta(new Cuenta(cliente, new BigDecimal(500)));

        Iterator cuentas = cliente.obtenerCuentas().iterator();

        while (cuentas.hasNext()) {
            Cuenta cuenta = (Cuenta) cuentas.next();
            BigDecimal pagarMonto = cuenta.pagar();
            assertEquals("Pago del monto no es correcto.", new BigDecimal(485).setScale(2), pagarMonto);
        }
    }

    @Test
    public void testPagoSinCliente() {
        Cuenta cuenta = new Cuenta(new CalculadoraDescuento() {
            public BigDecimal obtenerMontoDescuento() { return new BigDecimal(0.1); }
        }, new BigDecimal(500));

        BigDecimal pagarMonto = cuenta.pagar();
        assertEquals("Pago monto no es correcto.", new BigDecimal(450).setScale(2), pagarMonto);
    }

}
