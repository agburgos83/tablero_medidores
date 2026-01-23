<script lang="ts">
	import KpiCard from './KpiCard.svelte';

	export let kpis: {
		totalMedidores: number;
		totalMedidoresConBateriaBaja: number;
		totalMedidoresConCaudalCero: number;
		totalMedidoresConFlujoInverso: number;
	};
</script>

{#if kpis}
	<div class="kpi-grid">
		<KpiCard title="Total Medidores" value={kpis.totalMedidores} />

		<KpiCard title="Con flujo inverso" value={kpis.totalMedidoresConFlujoInverso} />

		<KpiCard title="Con caudal cero" value={kpis.totalMedidoresConCaudalCero} />

		<KpiCard title="Con batería baja" value={kpis.totalMedidoresConBateriaBaja} />
	</div>
{:else}
	<p>Cargando KPIs...</p>
{/if}

<style>
	.kpi-grid {
		display: grid;
		grid-template-columns: repeat(4, minmax(0, 1fr));
		gap: 1rem;
	}
</style>
