package org.test.antiguo;

import org.cliente.Cliente;
import org.cuenta.CalculadoraDescuento;
import org.cuenta.Cuenta;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestPagoAntiguo {
    private static final Logger logger = LoggerFactory.getLogger(TestPagoAntiguo.class);


    @Test
    public void testCrearCuenta() {
        Cliente cliente = new Cliente();
        BigDecimal chargeAmount = new BigDecimal(500);

        cliente.crearCuenta(chargeAmount);
        assertNotNull(cliente.obtenerCuentas());
        assertEquals(1, cliente.obtenerCuentas().size());

        Cuenta cuenta = (Cuenta) cliente.obtenerCuentas().get(0);
        assertEquals(chargeAmount, cuenta.obtenerMontoCargo());
    }
}
