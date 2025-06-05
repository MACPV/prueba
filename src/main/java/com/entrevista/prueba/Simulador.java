package com.entrevista.prueba;

import java.io.FileReader;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvConstraintViolationException;

import java.util.List;

public class Simulador {
    public static void main(String[] args) {
        String datos = Simulador.class.getClassLoader().getResource("datitos.csv").getFile();

        try {
            CsvToBean<CsvObjetos> beanCSV = new CsvToBeanBuilder<CsvObjetos>(new FileReader(datos))
                    .withType(CsvObjetos.class)
                    .withIgnoreLeadingWhiteSpace(true).withExceptionHandler(CsvConstraintViolationException::new)
                    .build();
            List<CsvObjetos> objetosCsv = beanCSV.parse();

            double totalDebitos = 0;
            double totalCreditos = 0;

            int contDebito = 0;
            int contCredito = 0;

            CsvObjetos transaccionMayor = null;

            for (CsvObjetos co : objetosCsv) {

                double monto = co.getMonto();
                String tipo = co.getTipo().toLowerCase();

                //Balance y Conteo
                if (co.getTipo().equalsIgnoreCase("débito")) {
                    totalDebitos += co.getMonto();
                    contDebito++;
                } else if (co.getTipo().equalsIgnoreCase("crédito")) {
                    totalCreditos += co.getMonto();
                    contCredito++;
                }

                //Transaccion mayor
                if (transaccionMayor == null || monto > transaccionMayor.getMonto()) {
                    transaccionMayor = co;
                }

            }

            double balance = totalDebitos - totalCreditos;

            //Impresiones
            System.out.println("Total créditos: S/. " + totalDebitos);
            System.out.println("Total débitos: S/. " + totalCreditos);
            System.out.println("Transacción de mayor monto: ID: "+ transaccionMayor.getId() + " - S/. "+ transaccionMayor.getMonto());
            System.out.println("Total de créditos:  " + contDebito);
            System.out.println("Total de débitos:  " + contCredito);

            if (balance >= 0) {
                System.out.println("Balance positivo: +" + balance);
            } else {
                System.out.println("Balance negativo: " + balance);
            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo :" + e.getMessage());
            System.err.println();
        }
    }
}
