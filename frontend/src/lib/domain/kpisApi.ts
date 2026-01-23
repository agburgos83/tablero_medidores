export type kpisApi = {
  fecha: string;
  totalMedidores: number;
  totalMedidoresTemperaturaAlta: number;
  totalMedidoresCaudalPromedio: number;
  totalMedidoresConSuccion: number;
  totalMedidoresConAire: number;
  totalMedidoresConBateriaBaja: number;
  totalMedidoresConCaudalCero: number;
  totalMedidoresConFlujoInverso: number;
  totalMedidoresConMuestraInvalida: number | null;
  totalMedidoresConPerdida: number;
};
