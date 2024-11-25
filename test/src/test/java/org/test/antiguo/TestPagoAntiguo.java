package org.test.antiguo;

import org.cliente.Cliente;
import org.cuenta.CalculadoraDescuento;
import org.cuenta.Cuenta;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestPagoAntiguo {
    private static final Logger logger = LoggerFactory.getLogger(TestPagoAntiguo.class);


    @Test
    public void testPago() {

        Cliente cliente = new Cliente();
        cliente.crearCuenta(new BigDecimal(500));

        Iterator bills = cliente.obtenerCuentas().iterator();

        while (bills.hasNext()) {
            Cuenta cuenta = (Cuenta) bills.next();
            BigDecimal paidAmount = cuenta.pagar();
            assertEquals("Pago del monto no es correcto.", new BigDecimal(485).setScale(2), paidAmount);
        }
    }


    @Test
    public void testPagoSinCliente() {
        Cuenta cuenta = new Cuenta(new CalculadoraDescuento() {
            public BigDecimal obtenerMontoDescuento() { return new BigDecimal(0.1); }
        }, new BigDecimal(500));

        BigDecimal montoPago = cuenta.pagar();
        assertEquals("Pago del monto no es correcto.", new BigDecimal(450).setScale(2), montoPago);
    }
}
