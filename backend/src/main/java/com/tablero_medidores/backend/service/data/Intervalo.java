package com.tablero_medidores.backend.service.data;

import java.time.LocalDateTime;

public record Intervalo(LocalDateTime start, LocalDateTime end) {}