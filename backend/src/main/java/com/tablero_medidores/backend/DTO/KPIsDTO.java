package com.tablero_medidores.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KPIsDTO {
    private int totalMedidores;
    private int medidoresConFalla;
    private Double consumoDiarioTotal;
    private Double temperaturaPromedio;
}

