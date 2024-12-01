package org.test.nuevo;

import cl.psp.base.Cuenta;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.test.antiguo.TestPagoAntiguo;

import java.lang.reflect.Method;

import static org.junit.Assert.fail;

public class TestPagoNuevo {

    private static final Logger logger = LogManager.getLogger(TestPagoAntiguo.class);

    @Test
    public void testCrearCuentaTieneDependenciaDirectaConCuenta () {
        logger.info("Iniciando test: Verificar si crearCuenta tiene dependencia directa con Cuenta.");
        try {
            // Obtener la clase Cliente
            Class<?> clienteClass = Class.forName("org.cliente.Cliente");
            logger.debug("Clase Cliente encontrada: {}", clienteClass.getName());

            // Inspeccionar los métodos de Cliente
            Method ingresarCuentaMethod = clienteClass.getDeclaredMethod("ingresarCuenta", Cuenta.class);
            logger.debug("Método crearCuenta encontrado: {}", ingresarCuentaMethod.getName());

            // Verificar si el método ingresarCuenta usa Cuenta
            String metodoCode = ingresarCuentaMethod.toString();
            logger.debug("Inspeccionando el código del método ingresarCuenta...");
            if (metodoCode.contains("CuentaDefecto")) {
                logger.error("El método ingresarCuenta tiene una dependencia directa con la clase CuentaDefecto.");
                fail("El método ingresarCuenta tiene una dependencia directa con la clase CuentaDefecto.");
            } else {
                logger.info("El método ingresarCuenta no tiene dependencia directa con la clase CuentaDefecto.");
            }

        } catch (NoSuchMethodException e) {
            logger.error("El método especificado no se encontró.", e);
            fail("No se pudo encontrar el método crearCuenta.");
        } catch (ClassNotFoundException e) {
            logger.error("La clase especificada no se encontró.", e);
            fail("No se pudo encontrar la clase Cliente.");
        }
    }

}
