import type { medicion } from "./medicion";

export type Severity = 1 | 2 | 3;

export const LIMITES = {
    aire: { warn: 10, crit: 30 },
    caudalCero: { warn: 5, crit: 20 },
    flujoInverso: { warn: 1, crit: 5 },
    bateria: { warn: 30, crit: 15 }, // % (al revés)
    perdida: { warn: 5, crit: 15 },
};

export function getSeverity(m: medicion): Severity {
    let isCritical = false;
    let isAlert = false;

    if (m.aire >= LIMITES.aire.crit) isCritical = true;
    else if (m.aire >= LIMITES.aire.warn) isAlert = true;

    if (m.caudalCero >= LIMITES.caudalCero.crit) isCritical = true;
    else if (m.caudalCero >= LIMITES.caudalCero.warn) isAlert = true;

    if (m.flujoInverso >= LIMITES.flujoInverso.crit) isCritical = true;
    else if (m.flujoInverso >= LIMITES.flujoInverso.warn) isAlert = true;

    if (m.nivelDeBateria <= LIMITES.bateria.crit) isCritical = true;
    else if (m.nivelDeBateria <= LIMITES.bateria.warn) isAlert = true;

    if (isCritical) {
        return 3;
    } else if (isAlert) {
        return 2;
    } else {
        return 1;
    }

}