# Ejercicios Formativo PSP II-2024

En este ejercicio, usted trabajará con un sistema modular desarrollado en OSGi que consta de tres bundles: Cliente, 
Cuenta y Pruebas. El sistema está diseñado para gestionar clientes y sus cuentas, con cálculos de descuentos aplicados a 
los montos de pago. Actualmente, los bundles presentan dependencias directas que dificultan la modularidad, lo cual será 
evidenciado a través de las pruebas automatizadas. El objetivo principal es refactorizar el sistema para eliminar estas 
dependencias, aplicando el Principio de Inversión de Dependencias (DIP), garantizando así la independencia entre los módulos.

## Compilación

<code>
mvn -pl cliente,test,cuenta clean deploy
</code>

## Repositorio

En Apache Felix:

<code>
repos add file:///ruta/a/repository.xml
</code>

Listar:
<code>
list
</code>

## Ejecutar Tests

<code>
mvn -pl cliente,test,cuenta clean test
</code>