/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author PabloP
 */
    public enum EstadoSesion {
    CONTRASENIA_INCORRECTA,           // nunca intentó iniciar sesión
    LOGIN_CORRECTO,     // tiene la sesión iniciada
    LOGIN_INCORRECTO,    // le erro a la sesión al menos una vez
}

