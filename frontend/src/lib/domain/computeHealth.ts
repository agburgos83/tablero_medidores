import { getSeverity } from '$lib/domain/severity';
import type { medicion } from '$lib/domain/medicion';
import type { kpisApi } from '$lib/domain/kpisApi';

export let meters: medicion[] = [];

export function computeHealth(meters: medicion[]) {
	let total = meters.length
	let normales = 0;
	let alertas = 0;
	let criticos = 0;

	for (const m of meters) {
		const s = getSeverity(m);
		if (s === 3) criticos++;
		else if (s === 2) alertas++;
	}

	let pctAlertas: number;
	pctAlertas = alertas * 100 / total;

	let pctCriticos: number;
	pctCriticos = criticos * 100 / total;

	let pctNormales: number;
	pctNormales = normales * 100 / total;

	return {
        normal: pctNormales,
        alerta: pctAlertas,
        critico: pctCriticos
    };
}


