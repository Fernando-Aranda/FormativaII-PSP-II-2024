package org.test.activator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import static org.ops4j.pax.exam.CoreOptions.*;
import org.osgi.framework.Bundle;
import org.ops4j.pax.exam.Configuration;
import org.junit.Assert;
import org.osgi.framework.BundleContext;
import org.test.antiguo.TestPagoAntiguo;
import org.test.nuevo.TestPagoNuevo;
import static org.apache.sling.testing.paxexam.SlingOptions.paxLoggingLog4j2;

import javax.inject.Inject;

@RunWith(PaxExam.class)
public class TestBundleIntegration{

    private static final Logger logger = LogManager.getLogger(TestBundleIntegration.class);

    @Inject
    BundleContext context;

    @Configuration
    public Option[] config() {
        return options(
                junitBundles(),
                paxLoggingLog4j2(),
                cleanCaches(),
                systemProperty("org.ops4j.pax.url.mvn.localRepository").value(System.getProperty("user.home") + "/.m2/repository"),
                systemProperty("org.ops4j.pax.logging.DefaultServiceLog.level").value("INFO"),
                mavenBundle("cl.psp", "cl.psp.cliente", "1.0"),
                mavenBundle("cl.psp", "cl.psp.cuenta", "1.0"),
                mavenBundle("org.apache.felix", "org.apache.felix.configadmin", "1.9.26"),
                mavenBundle("org.apache.felix", "org.apache.felix.fileinstall", "3.7.4"),
                mavenBundle("org.ops4j.pax.logging", "pax-logging-log4j2", "2.2.1"),
                mavenBundle("org.ops4j.pax.logging", "pax-logging-api", "2.2.1")
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

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.stop();
        context.start();
    }

    @Test
    public void testPagoAntiguo() {




        // Run the tests from TestPagoAntiguo using JUnitCore
        Result result = JUnitCore.runClasses(TestPagoAntiguo.class);

        if (result.wasSuccessful()) {
            logger.info("¡Todas las pruebas pasaron exitosamente!");
        } else {
            logger.info("Pruebas fallidas:");
            for (Failure failure : result.getFailures()) {
                logger.info(failure.toString());
            }
        }

        Assert.assertTrue(result.wasSuccessful());

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.stop();
        context.start();
    }

    @Test
    public void testPagoNuevo() {
        // Run the tests from TestPagoAntiguo using JUnitCore
        Result result = JUnitCore.runClasses(TestPagoNuevo.class);

        if (result.wasSuccessful()) {
            logger.info("¡Todas las pruebas pasaron exitosamente!");
        } else {
            logger.info("Pruebas fallidas:");
            for (Failure failure : result.getFailures()) {
                logger.info(failure.toString());
            }
        }
        Assert.assertTrue(result.wasSuccessful());

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.stop();
        context.start();
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
