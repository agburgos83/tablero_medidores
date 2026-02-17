<script lang="ts">
	import MapSection from '$lib/components/MapSection.svelte';
	import KPIGrid from '$lib/components/KpiGrid.svelte';
	import BarChartSection from '$lib/components/BarChartSection.svelte';
	import GaugeChartSection from '$lib/components/GaugeChartSection.svelte';

	export let data;

	const { map, kpis, meters } = data;

	const currentDate = new Date();

	const formattedDate = currentDate.toLocaleDateString('es-ES', {
		year: 'numeric',
		month: 'long',
		day: 'numeric'
	});
</script>

<svelte:head>
	<title>Dashboard de Métricas — Coop. Ltda. San Juan</title>
</svelte:head>

<div class="dashboard">
	<header class="dashboard-header">
		<h3 style="text-align: left;">Dashboard de Métricas — Coop. Ltda San Juan</h3>
	</header>

	<div class="main-surface">
		<section class="greeting-area">
			<h3><em>Indicadores {formattedDate}, La Plata (Arg)</em></h3>
		</section>

		<section class="kpi-area">
			<KPIGrid {kpis} />
		</section>

		<section class="map-band">
			<MapSection {map} {meters} />
		</section>

		<section class="charts-band">
			<div>
				<BarChartSection {kpis} />
			</div>

			<div>
				<GaugeChartSection {meters} />
			</div>
		</section>
	</div>

	<footer class="dashboard-footer">
		<p>Coop. Ltda. San Juan</p>
	</footer>
</div>

<style>
	.dashboard {
		background: #E9EBEF;
		display: flex;
		flex-direction: column;
		min-height: 100vh;
	}

	.main-surface {
		background: white;
		width: 100%;
		display: flex;
		flex-direction: column;
		background: white;
		width: 100%;

		flex: 1;
	}

	.greeting-area {
		padding: 1rem 2rem 0rem 2rem;
	}

	.kpi-area,
	.map-band {
		padding: 1rem 2rem;
		background: transparent;
	}

	.charts-band {
		display: grid;
		grid-template-columns: 1fr 1fr; /* 50% + 50% */
		gap: 1.5rem;
		padding: 1rem 2rem;
		height: 400px;
		margin-bottom: 15px;
	}

	/* .chart-cell {
		display: flex;
		height: 100%;
	} */

	.dashboard {
		display: flex;
		flex-direction: column;
		min-height: 100vh;
		background: white;
	}

	.dashboard-header {
		background: #1e3a5f;
		color: white;
		padding-left: 2rem;
		padding-right: 2rem;
		font-size: 1.2rem;
		font-weight: bold;
		text-align: left;
	}

	.dashboard-footer {
		margin-top: auto;
		background: #1e3a5f;
		color: white;
		text-align: center;
		padding: 0.5rem;
		font-size: 0.85;
	}

	/* estilos 2 layout */
/* 
	.kpi-area {
		padding: 1rem 2rem;
	}

	.bottom-band {
		flex: 1;
		display: grid;
		grid-template-columns:
		gap: 1.5rem;
		padding: 1rem 2rem;
		min-height: 0; 
	}

	.map-cell {
		display: flex;
		min-height: 0;
	}

	.charts-cell {
		display: flex;
		flex-direction: column;
		gap: 1.5rem;
		min-height: 0;
	}

	.chart-top,
	.chart-bottom {
		flex: 1;
		min-height: 0;
		display: flex;
	} */
</style>
