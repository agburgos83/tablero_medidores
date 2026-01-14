<script lang="ts">
	import Map from '$lib/components/Map.svelte';
	import KPIGrid from '$lib/components/KpiCard.svelte';

	export let data;

	let selectedMeter: any = null;

	let selectedFilter: 'todos' | 'normales' | 'alerta' | 'critico' = 'todos';

	const { map, kpis, meters } = data;

	const currentDate = new Date();

	const formattedDate = currentDate.toLocaleDateString('es-ES', {
		year: 'numeric',
		month: 'long',
		day: 'numeric'
	});

	function setFilter(f: 'todos' | 'normales' | 'alerta' | 'critico') {
		selectedFilter = f;
	}

	function handleMapSelect(m) {
		selectedMeter = m;
		// hacer lo que necesites con selectedMeter
	}
</script>

<svelte:head>
	<title>Dashboard de Métricas — Coop. Ltda San Juan</title>
</svelte:head>

<div class="dashboard">
	<header class="dashboard-header">
		<h3 style="text-align: left;">Dashboard de Métricas — Coop. Ltda San Juan</h3>
	</header>

	<div class="main-surface">
		<!-- primera banda, kpis -->
		<section class="kpi-band">
			<h3><em>Indicadores {formattedDate}, La Plata (Arg)</em></h3>
			<br />
			<KPIGrid {kpis} />
		</section>

		<!-- segunda banda, mapa -->
		<section class="map-band">
			<div class="map-surface">
				<div class="map-container">
					<div class="map-header">
						<h3>Mapa de medidores</h3>
					</div>

					<div class="map-canvas">
						<div class="map-filters">
							<button
								on:click={() => setFilter('todos')}
								class:selected={selectedFilter === 'todos'}>Todos</button
							>
							<button
								on:click={() => setFilter('normales')}
								class:selected={selectedFilter === 'normales'}>Normales</button
							>
							<button
								on:click={() => setFilter('alerta')}
								class:selected={selectedFilter === 'alerta'}>Alerta</button
							>
							<button
								on:click={() => setFilter('critico')}
								class:selected={selectedFilter === 'critico'}>Críticos</button
							>
						</div>

						{#if map}
							<Map {map} {meters} filter={selectedFilter} onSelect={handleMapSelect}/>
						{:else}
							<p>Cargando mapa…</p>
						{/if}
					</div>
				</div>
			</div>
		</section>

		<!-- tercera banda, charts -->
		<section class="charts-band">
			<div class="chart-container">
				<div class="chart-header">
					<h3>Medidores por estado</h3>
				</div>
				<div class="chart-canvas">
					<div class="chart-placeholder">[Gráfico de barras]</div>
				</div>
			</div>

			<div class="chart-container">
				<div class="chart-header">
					<h3>Estado general</h3>
				</div>
				<div class="chart-canvas">
					<div class="chart-placeholder">[Gauge]</div>
				</div>
			</div>
		</section>
	</div>

	<footer class="dashboard-footer">
		<p>Cooperativa 30 de Agosto - Sistema de monitoreo de medidores</p>
	</footer>
</div>

<style>
	.dashboard {
		background: #eef1f6;
	}

	.main-surface {
		background: white;
		width: 100%;
	}

	.kpi-band,
	.map-band,
	.charts-band {
		padding: 1rem 2rem;
		background: transparent;
	}

	.charts-band {
		display: grid;
		grid-template-columns: 1fr 1fr;
		gap: 1.5rem;
	}

	.map-surface {
		width: 100%;
		height: 600px;
		background: white;
		border-radius: 8px;
		box-shadow: 0 0 6px rgba(0, 0, 0, 0.1);
		display: flex;
		flex-direction: column;
		overflow: hidden;
	}

	.map-container {
		flex: 1;
		display: flex;
		flex-direction: column;
	}

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

	.map-container {
		width: 100%;
		height: 600px;
		background: white;
		border-radius: 8px;
		overflow: hidden;
		box-shadow: 0 0 6px rgba(0, 0, 0, 0.1);
		display: flex;
		flex-direction: column;
	}

	.map-header {
		flex: 0 0 auto;
		padding: 0.5rem 1rem;
		background: #eef1f6;
		display: flex;
		justify-content: space-between;
		align-items: center;
		font-size: 1rem;
		font-weight: bold;
		text-align: left;
		color: #222;
	}

	.map-canvas {
		flex: 1 1 auto;
		position: relative;
	}

	.map-filters {
		position: absolute;
		top: 16px; /* separación respecto al tope */
		right: 16px; /* separación respecto al borde derecho */
		display: flex;
		gap: 0.5rem;
		padding: 6px;
		background: rgba(255, 255, 255, 0.9);
		border-radius: 6px;
		box-shadow: 0 2px 6px rgba(0, 0, 0, 0.08);
		z-index: 10;
	}

	.map-filters button {
		margin: 0; /* eliminar margin-left usado antes */
		padding: 0.35rem 0.7rem;
		border-radius: 4px;
		border: 1px solid #aaa;
		background: white;
		color: #222;
		cursor: pointer;
		transition:
			background 120ms ease,
			color 120ms ease,
			border-color 120ms ease;
	}

	.map-filters button:hover {
		filter: brightness(0.97);
	}
	.map-filters button.selected {
		background: #1e3a5f;
		color: #ffffff;
		border-color: #163047;
	}

	/* cada chart es una tarjeta, como map-container */
	.chart-container {
		background: white;
		border-radius: 8px;
		box-shadow: 0 0 6px rgba(0, 0, 0, 0.1);
		display: flex;
		flex-direction: column;
		overflow: hidden;
	}

	/* header gris como el del mapa */
	.chart-header {
		background: #eef1f6;
		padding: 0.5rem 1rem;
		font-weight: bold;
		color: #222;
	}

	/* área blanca de contenido */
	.chart-canvas {
		flex: 1;
		padding: 1rem;
		box-sizing: border-box;
	}
	.chart-placeholder {
		flex: 1;
		min-height: 300px;
		display: flex;
		align-items: center;
		justify-content: center;
		color: #999;
		border: 2px dashed #ccc;
	}

	.dashboard-footer {
		margin-top: auto;
		background: #1e3a5f;
		color: white;
		text-align: center;
		padding: 0.5rem;
		font-size: 0.85;
	}
</style>
