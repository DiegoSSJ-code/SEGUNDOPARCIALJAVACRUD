/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.db_tareajava;

import datos.ClsVendedorJDBC;
import dominio.*;
import java.util.*;

/**
 *
 * @author Win10
 */
public class ClsPrincipalCRUD {
    private static final Scanner scanner = new Scanner(System.in);
    private static int opcion = -1;
    public static ClsVendedorJDBC al = new ClsVendedorJDBC();
  
    public static void main(String[] args) {
        while (opcion != 0){
            try{
                System.out.println("Elige opcion:\n1.- Ver lista completa de vendedores"
                + "\n2.- Agregar vendedor\n"
                + "3.- Actualizar vendedor\n"
                + "4.- Borrar vendedor\n"
                + "5.- Buscar vendedor\n"        
                + "0.- Salir");

                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        LeerTodo();
                        break;
                    case 2:
                        Crear();
                        break;
                    case 3:
                        Actualizar();
                        break;
                    case 4:
                        eliminar();
                        break;
                    case 5:
                        buscarCod();
                        break;
                    case 0:
                        System.out.println("!Hasta pronto!");
                        break;
                    default:
                        System.out.println("Opcion no reconocida");
                        break;
                }
                System.out.println("\n");

            } catch (Exception e) {
                System.out.println("Error!");
            }
            }
        }
    
    private static void LeerTodo(){
        ClsVendedorJDBC alJDBC = new ClsVendedorJDBC();
        List<ClsVendedores> todos = alJDBC.SQLSelect();
        
        for(ClsVendedores alum : todos){
            System.out.println("Alumnos: "+alum);
        }
    }
    
    private static void Crear(){
        int codigo_v = 0;
        String nombre = "";
        double enero = 0;
        double febrero  = 0;
        double marzo = 0;
        System.out.println("Ingrese el codiog del vendedor");
        codigo_v = Integer.parseInt(scanner.nextLine());
        System.out.println("Ingrese el nombre del vendedor: ");
        nombre = scanner.nextLine();
        System.out.println("Ingrese la venta de enero: ");
        enero = Double.parseDouble(scanner.nextLine());
        System.out.println("Ingrese la venta de febrero: ");
        febrero = Double.parseDouble(scanner.nextLine());
        System.out.println("Ingrese la venta de marzo: ");
        marzo = Double.parseDouble(scanner.nextLine());
        ClsVendedores vendedor = new ClsVendedores(codigo_v, nombre, enero, febrero, marzo);   
        System.out.println(al.SQLCreate(vendedor));
        System.out.println("AGREGADO CON EXITO :)");
    }

    private static void Actualizar() {
        int codigo_v = 0;
        String nombre = "";
        double enero = 0;
        double febrero = 0;
        double marzo = 0;
        System.out.println("Ingrese el codigo del vendedor que desea actualizar: ");
        codigo_v = Integer.parseInt(scanner.nextLine());
        System.out.println("Ingrese el nuevo nombre del vendedor: ");
        nombre = scanner.nextLine();
        System.out.println("Ingrese la nueva venta de enero: ");
        enero = Double.parseDouble(scanner.nextLine());
        System.out.println("Ingrese la nueva venta de febrero: ");
        febrero = Double.parseDouble(scanner.nextLine());
        System.out.println("Ingrese la nueva venta de marzo");
        marzo = Double.parseDouble(scanner.nextLine());
        ClsVendedores alumno = new ClsVendedores(codigo_v, nombre, enero, febrero, marzo);   
        System.out.println(al.SQLCreate(alumno));
        System.out.println("ACTUALIZADO CON EXITO :)");
    }

    private static void eliminar() {
       int cod = 0;
        System.out.println("Ingrese el codigo del vendedor que desea eliminar: ");
        cod = Integer.parseInt(scanner.nextLine());
        System.out.println(al.SQLDelete(cod));
        System.out.println("ELIMINADO CON EXITO :)");
    }

    private static void buscarCod() {
        int cod = 0;
        System.out.println("Ingrese el codigo del vendedor que desea buscar: ");
        System.out.println(al.SQLSelectOnlyOne(cod));
        System.out.println("ENCONTRADO");
    }
    
}
