@Google
Feature: Google
  Busquedas en Google

  @ENCONTRAR_TEMPERATURA
  Scenario: Obtener temperatura
    Busqueda para Obtener Temperatura
    Given Ingresar Ciudad
    When Obtener tempreratura
    Then Imprimir temperatura