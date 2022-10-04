/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.pmp.vocabulario;

import java.util.Scanner;
import java.util.ArrayList;
import com.pmp.vocabulario.dao.VocabularioDao;

/**
 *
 * @author zunig
 */
public class Vocabulario {
    public static Scanner input;
    public static ArrayList<VocabularioPJO> arrVocabulario;
    public static VocabularioDao vocabDao;
    public static void main(String[] args) {
        input = new Scanner(System.in);
        arrVocabulario = new ArrayList();
        vocabDao = new VocabularioDao();
        VocabularioUX.header("CRUD Vocabulario");
        String optionSelected = "";
        while (!optionSelected.equalsIgnoreCase("S")) {
            VocabularioUX.generateMenu();
            optionSelected = input.nextLine();
            mainController(optionSelected);
        }
    }
    
    private static void mainController(String optionSelected) {
        arrVocabulario = vocabDao.obtenerPalabra();
        switch (optionSelected.toUpperCase()) {
            case "M":
                mostrarVocabulario();
                break;
            case "N":
                nuevoVocabulario();
                break;
            case "A":
                actualizarVocabulario();
                break;
            case "E":
                eliminarVocabulario();
                break;
            case "V":
                detallesVocabulario();
                break;
            case "S":
                System.out.println("Opcion S seleccionada");
                break;
            default:
                VocabularioUX.header("Opción no válida");
        }
    }
    
    private static VocabularioPJO inputForm (VocabularioPJO basePalabra){
        basePalabra.setId(
                Integer.parseInt(
                VocabularioUX.getFieldInput(input, "ID", basePalabra.getId().toString())
            )
        );
        basePalabra.setEspaniol(
                VocabularioUX.getFieldInput(input, "Espaniol", basePalabra.getEspaniol())
        );
        basePalabra.setIngles(
                VocabularioUX.getFieldInput(input, "Ingles", basePalabra.getIngles())
        );
        basePalabra.setItaliano(
                VocabularioUX.getFieldInput(input, "Italiano", basePalabra.getItaliano())
        );
        basePalabra.setAleman(
                VocabularioUX.getFieldInput(input, "Aleman", basePalabra.getAleman())
        );
        return basePalabra;
    }
    
    private static VocabularioPJO validarEntradaRegistro() {
        if (arrVocabulario.isEmpty()) {
            System.out.println("No Hay Datos!");
            return null;
        }
        int index = Integer.parseInt(
            VocabularioUX.getFieldInput(input, "Número de Linea: ", "1")
        );
        for (int i = 0; i < arrVocabulario.size(); i++){
            if (index == arrVocabulario.get(i).getId()) {
                return arrVocabulario.get(i);
            }
        }
        return null;
    }
    
    private static void nuevoVocabulario() {
        VocabularioPJO nuevoVocabularioIns = new VocabularioPJO();
        nuevoVocabularioIns.setId(1);
        nuevoVocabularioIns.setEspaniol("Casa");
        nuevoVocabularioIns.setIngles("House");
        nuevoVocabularioIns.setItaliano("Casa");
        nuevoVocabularioIns.setAleman("Haus");
        nuevoVocabularioIns = inputForm(nuevoVocabularioIns);
        vocabDao.insertPalabra(nuevoVocabularioIns);
        arrVocabulario.add(nuevoVocabularioIns);
    }    
    
    private static void mostrarVocabulario() {
        arrVocabulario = vocabDao.obtenerPalabra();
        if (!arrVocabulario.isEmpty()) {
            for (int i = 0; i < arrVocabulario.size(); i++) {
                VocabularioPJO palabra = arrVocabulario.get(i);
                System.out.println(
                        palabra.getId().toString() + " " +
                        palabra.getEspaniol() + " " +
                        palabra.getIngles() + " " +
                        palabra.getItaliano() + " " +
                        palabra.getAleman()
                );
            }
        } else {
            System.out.println("No hay datos que mostrar.");
        }
    }
    
    private static void actualizarVocabulario() {
        VocabularioPJO actualizarVocabularioIns = validarEntradaRegistro();
        if (actualizarVocabularioIns != null) {
            actualizarVocabularioIns = inputForm(actualizarVocabularioIns);
            vocabDao.updatePalabra(actualizarVocabularioIns);
            System.out.println("Registro Modificado");
        }
    }
    
    private static void eliminarVocabulario(){
        VocabularioPJO eliminarVocabularioIns = validarEntradaRegistro();
        if (eliminarVocabularioIns != null) {
            vocabDao.deletePalabra(eliminarVocabularioIns);
            System.out.println("Registro Modificado");
        }
    }
    
    private static void detallesVocabulario() {
        arrVocabulario = vocabDao.obtenerPalabra();
        if (arrVocabulario.isEmpty()) {
            System.out.println("No Hay Datos!");
            return;
        }
        int index = Integer.parseInt(
            VocabularioUX.getFieldInput(input, "Número de Linea: ", "1")
            );
        for (int i = 0; i < arrVocabulario.size(); i++){
            if (index == arrVocabulario.get(i).getId()) {
                VocabularioPJO palabra = arrVocabulario.get(i);
                System.out.println(
                    "Id: " + palabra.getId().toString() + "\n" +
                    "Espaniol: " + palabra.getEspaniol() + "\n" +
                    "Ingles: " + palabra.getIngles() + "\n" +
                    "Italiano: " + palabra.getItaliano() + "\n" +
                    "Aleman: " + palabra.getAleman()
                );
            }
        }
    }
}