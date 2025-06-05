package com.entrevista.prueba;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CsvObjetos {
    @CsvBindByName
    private int id;

    @CsvBindByName
    private String tipo;

    @CsvBindByName
    private double monto;



}
