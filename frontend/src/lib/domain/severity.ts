export type Severity = 'normales' | 'alerta' | 'critico';

export const THRESHOLDS = {
    aire: { warn: 10, crit: 30 },
    caudalCero: { warn: 5, crit: 20 },
    flujoInverso: { warn: 1, crit: 5 },
    bateria: { warn: 30, crit: 15 }, // % (al revés)
    perdida: { warn: 5, crit: 15 },
};

export function getSeverity(m: Medicion): Severity {
    let isCritical = false;
    let isAlert = false;

    if (m.aire >= THRESHOLDS.aire.crit) isCritical = true;
    else if (m.aire >= THRESHOLDS.aire.warn) isAlert = true;

    if (m.caudalCero >= THRESHOLDS.caudalCero.crit) isCritical = true;
    else if (m.caudalCero >= THRESHOLDS.caudalCero.warn) isAlert = true;

    if (m.flujoInverso >= THRESHOLDS.flujoInverso.crit) isCritical = true;
    else if (m.flujoInverso >= THRESHOLDS.flujoInverso.warn) isAlert = true;

    if (m.nivelDeBateria <= THRESHOLDS.bateria.crit) isCritical = true;
    else if (m.nivelDeBateria <= THRESHOLDS.bateria.warn) isAlert = true;

    if (isCritical) {
        m.estado = 'critico';
        return 'critico';
    } else if (isAlert) {
        m.estado = 'alerta';
        return 'alerta';
    }  else {
        m.estado = 'mormales';
        return 'normales';
    }
    
}