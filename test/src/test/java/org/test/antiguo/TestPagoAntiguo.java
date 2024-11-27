package org.test.antiguo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.lang.reflect.Method;
import java.math.BigDecimal;


import static org.junit.Assert.*;

public class TestPagoAntiguo {

    private static final Logger logger = LogManager.getLogger(TestPagoAntiguo.class);

    @Test
    public void testCrearCuentaTieneDependenciaDirectaConCuenta() {
        logger.info("Iniciando test: Verificar si crearCuenta tiene dependencia directa con Cuenta.");
        try {
            // Obtener la clase Cliente
            Class<?> clienteClass = Class.forName("org.cliente.Cliente");
            logger.debug("Clase Cliente encontrada: {}", clienteClass.getName());

            // Inspeccionar los métodos de Cliente
            Method crearCuentaMethod = clienteClass.getDeclaredMethod("crearCuenta", BigDecimal.class);
            logger.debug("Método crearCuenta encontrado: {}", crearCuentaMethod.getName());

            // Verificar si el método crearCuenta usa Cuenta
            String metodoCode = crearCuentaMethod.toString();
            logger.debug("Inspeccionando el código del método crearCuenta...");
            if (metodoCode.contains("Cuenta")) {
                logger.error("El método crearCuenta tiene una dependencia directa con la clase Cuenta.");
                fail("El método crearCuenta tiene una dependencia directa con la clase Cuenta.");
            } else {
                logger.info("El método crearCuenta no tiene dependencia directa con la clase Cuenta.");
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
