<script lang="ts">

	let cliente = "Cooperativa Eléctrica 30 de Agosto";	

	import KpiCard from '$lib/components/KpiCard.svelte';
	import LineChart from '$lib/components/LineChart.svelte';
	import StackedBarChart from '$lib/components/StackedBarChart.svelte';
	import Map from '$lib/components/Map.svelte';

	export let data;
	const { kpis } = data;

</script>

<KpiCard {kpis} />

<svelte:head>
	<title>Home</title>
	<meta name="description" content="Svelte demo app" />
</svelte:head>



<section>
	<h1>Tablero de medidores: <br>
		<strong>{cliente}</strong>
	</h1>
	
	<hr>

	
<!-- 
	<div style="display: flex; gap: 1rem; margin-top: 1rem;">
		<KpiCard titulo="Medidores totales" valor={data} colorFondo="#e0f7fa" />
  		<KpiCard titulo="Con fallas" valor={medidoresConFalla} colorFondo="#ffebee" />
  		<KpiCard titulo="Consumo hoy" valor={`${consumoTotalHoy} kWh`} colorFondo="#e8f5e9" />
	</div>
 -->

	<section class="p-4">
		<h1 class="text-2xl font-bold mb-4">Panel de Indicadores</h1>
		<KpiCard {kpis}/>
	</section>
	
	<div class="chart-wrapper">
		<div class="chart-box">
			<h2>Evolución del consumo</h2>
			<LineChart />
		</div>
	
		<div class="chart-box">
			<h2>Consumo por zona</h2>
			<StackedBarChart />
		</div>

		<div class="chart-box">
			<h2>Mapa de medidores</h2>
			<Map />
		</div>

		<div id="map" class="h-[500px] w-full">
			<h2>Mapa de medidores</h2>
			<Map />
		</div>

	</div>

</section>

<style>
	section {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		flex: 0.6;
	}

	h1 {
		width: 100%;
	}


	.chart-container {
		display: flex;
		justify-content: space-between;
		gap: 1rem;
	}

	.chart-container > * {
		flex: 1;
		height: 400px;
	}

	.chart-wrapper {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
	gap: 2rem;
	margin-top: 2rem;
	width: 100%;
}

	.chart-box {
		flex: 1 1 45%;
		display: flex;
		flex-direction: column;
		align-items: center;
	}

	.chart-box canvas {
		width: 100% !important;
		height: 300px !important;
	}

	.map {
		height: 500px;
    width: 100%;
	}

	html, body, #svelte {
	height: 100%;
}


</style>