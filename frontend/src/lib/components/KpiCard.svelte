<script lang="ts">
	// export let titulo: string;
	// export let valor: string | number;
	// export let colorFondo: string = "#f5f5f5";

	export let kpis: {
		totalMedidores: number;
		totalMedidoresConBateriaBaja: number;
		totalMedidoresConCaudalCero: number;
		totalMedidoresConFlujoInverso: number;
	};
</script>

{#if kpis}
	<div class="grid grid-cols-1 gap-4 md:grid-cols-4">
		<div class="kpi-card">
			<h2 class="mb-2 font-bold text-gray-600">Total medidores</h2>
			<p class="text-5xl font-bold text-red-500">
				{kpis.totalMedidores}
			</p>
		</div>

		<div class="kpi-card">
			<h2 class="mb-2 font-bold text-gray-600">Total batería baja</h2>
			<p class="text-5xl font-bold text-red-500">
				{kpis.totalMedidoresConBateriaBaja}
			</p>
		</div>

		<div class="kpi-card">
			<h2 class="mb-2 font-bold text-gray-600">Total caudal cero</h2>
			<p class="text-5xl font-bold text-red-500">
				{kpis.totalMedidoresConCaudalCero}
			</p>
		</div>

		<div class="kpi-card">
			<h2 class="mb-2 font-bold text-gray-600">Total flujo inverso</h2>
			<p class="text-5xl font-bold text-red-500">
				{kpis.totalMedidoresConFlujoInverso}
			</p>
		</div>
	</div>
{:else}
	<p>Cargando KPIs...</p>
{/if}

<style>
	.kpi-card {
		background: linear-gradient(180deg, #fff, rgb(255, 234, 253));
		/* background: linear-gradient(180deg, #fff, #ffffe0); */
		/* border: 1px solid #f5c2cd; */
		/* background: #fde8ec;  */
		border-radius: 0.75rem;
		padding: 1rem;
		text-align: center;
		box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
	}
</style>
