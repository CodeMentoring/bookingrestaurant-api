package com.pe.hampcode.service;

import com.pe.hampcode.entity.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MathServiceTest {

    @Mock
    private Calculator calculator; // Mock de la dependencia

    @InjectMocks
    private MathService mathService; // Clase a probar

    @BeforeEach
    public void setUp() {
        // Arrange (preparación)
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAdd() {
        // Arrange (preparación)
        when(calculator.add(2, 3)).thenReturn(5); // Configurar el comportamiento del mock

        // Act (actuar)
        int result = mathService.add(2, 3); // Invocar a la lógica de negocio

        // Assert (afirmar)
        assertEquals(5, result); // Verificar que el resultado es el esperado
        verify(calculator, times(1)).add(2, 3); // Verificar que el método add de Calculator fue llamado exactamente una vez
    }

    @Test
    public void testSubtract() {
        // Arrange (preparación)
        when(calculator.subtract(5, 2)).thenReturn(3); // Configurar el comportamiento del mock

        // Act (actuar)
        int result = mathService.subtract(5, 2); // Invocar a la lógica de negocio

        // Assert (afirmar)
        assertEquals(3, result); // Verificar que el resultado es el esperado
        verify(calculator, times(1)).subtract(5, 2); // Verificar que el método subtract de Calculator fue llamado exactamente una vez
    }
}
