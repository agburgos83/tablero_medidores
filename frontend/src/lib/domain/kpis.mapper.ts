import type { kpisApi } from './kpisApi';
import type { kpiTotals } from './kpiTotals';

export function mapKpis(api: kpisApi): kpiTotals {
  return {
    total: api.totalMedidores,
    bateria: api.totalMedidoresConBateriaBaja,
    flujo: api.totalMedidoresConFlujoInverso,
    caudal: api.totalMedidoresConCaudalCero,
    aire: api.totalMedidoresConAire,
    perdida: api.totalMedidoresConPerdida
  };
}
