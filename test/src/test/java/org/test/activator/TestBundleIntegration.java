package org.test.activator;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import static org.ops4j.pax.exam.CoreOptions.*;
import org.osgi.framework.Bundle;
import org.ops4j.pax.exam.junit.Configuration;
import org.junit.Assert;
import org.osgi.framework.BundleContext;
import org.test.antiguo.TestPagoAntiguo;
import org.test.nuevo.TestPagoNuevo;

import javax.inject.Inject;

@RunWith(PaxExam.class)
public class TestBundleIntegration{


    @Inject
    BundleContext context;

    @Configuration
    public Option[] config() {
        return options(
                junitBundles(),
                cleanCaches(),
                mavenBundle("cl.psp", "cliente", "1.0"),
                mavenBundle("cl.psp", "cuenta", "1.0")
        );
    }

    @Test
    public void testBundlesActivos() {
        Bundle clienteBundle = getBundleBySymbolicName("cl.psp.cliente");
        Bundle cuentaBundle = getBundleBySymbolicName("cl.psp.cuenta");

        Assert.assertNotNull(clienteBundle);
        Assert.assertNotNull(cuentaBundle);
        Assert.assertEquals(Bundle.ACTIVE, clienteBundle.getState());
        Assert.assertEquals(Bundle.ACTIVE, cuentaBundle.getState());
    }

    @Test
    public void testTestPagoAntiguo() {
        // Run the tests from TestPagoAntiguo using JUnitCore
        Result result = JUnitCore.runClasses(TestPagoAntiguo.class);

        if (result.wasSuccessful()) {
            System.out.println("¡Todas las pruebas pasaron exitosamente!");
        } else {
            System.out.println("Pruebas fallidas:");
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }

        Assert.assertTrue(result.wasSuccessful());
    }

    @Test
    public void testTestPagoNuevo() {
        // Run the tests from TestPagoAntiguo using JUnitCore
        Result result = JUnitCore.runClasses(TestPagoNuevo.class);

        if (result.wasSuccessful()) {
            System.out.println("¡Todas las pruebas pasaron exitosamente!");
        } else {
            System.out.println("Pruebas fallidas:");
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
        Assert.assertTrue(result.wasSuccessful());
    }

    private Bundle getBundleBySymbolicName(String symbolicName) {
        for (Bundle bundle : context.getBundles()) {
            if (symbolicName.equals(bundle.getSymbolicName())) {
                return bundle;
            }
        }
        return null;
    }
}
