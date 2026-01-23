import type { kpiTotals } from './kpiTotals';
import type { bar } from './bar'

export function kpisToBars(k: kpiTotals): bar[] {
    return [
        { key: 'aire', label: 'Aire', value: k.aire },
        { key: 'caudalCero', label: 'Caudal cero', value: k.caudal },
        { key: 'flujoInverso', label: 'Flujo inverso', value: k.flujo },
        { key: 'nivelDeBateria', label: 'Batería baja', value: k.bateria },
        { key: 'perdida', label: 'Pérdida', value: k.perdida }
    ]
}