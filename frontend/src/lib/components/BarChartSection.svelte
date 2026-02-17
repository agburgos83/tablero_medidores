<script lang="ts">
	import type { kpisApi } from '$lib/domain/kpisApi';
	import type { bar } from '$lib/domain/bar';
	import { mapKpis } from '$lib/domain/kpis.mapper';
	import { kpisToBars } from '$lib/domain/kpisToBars';
	import BarChart from './BarChart.svelte';

	export let kpis: kpisApi;

	let barras: bar[] = [];
	let container: HTMLDivElement;

	let hoveredBar: bar | null = null;
	let tooltipPos = { x: 0, y: 0 };
	let tooltipEl: HTMLDivElement;

	$: kpiTotals = kpis ? mapKpis(kpis) : null;
	$: barras = kpiTotals ? kpisToBars(kpiTotals) : [];

	$: if (kpiTotals && kpiTotals.total > 0) {
		const total = kpiTotals.total;

		barras = [
			{ key: 'aire', label: 'Aire', value: (kpiTotals.aire / total) * 100 },
			{ key: 'caudalCero', label: 'Caudal cero', value: (kpiTotals.caudal / total) * 100 },
			{ key: 'flujoInverso', label: 'Flujo inverso', value: (kpiTotals.flujo / total) * 100 },
			{ key: 'nivelDeBateria', label: 'Batería baja', value: (kpiTotals.bateria / total) * 100 },
			{ key: 'perdida', label: 'Pérdida', value: (kpiTotals.perdida / total) * 100 }
		];
	}

	function handleHoverBar(b: bar, x: number, y: number) {
		const rect = container.getBoundingClientRect();

		let tx = x - rect.left - 120; // pequeño offset
		let ty = y - rect.top - 30;

		hoveredBar = b;

		// Esperamos al DOM para medir tooltip
		requestAnimationFrame(() => {
			if (!tooltipEl) return;

			const w = tooltipEl.offsetWidth;
			const h = tooltipEl.offsetHeight;

			const maxX = rect.width - w - 8;
			const maxY = rect.height - h - 8;

			tooltipPos = {
				x: Math.max(8, Math.min(tx, maxX)),
				y: Math.max(18, Math.min(ty, maxY))
			};
		});
	}

	function handleLeaveBar() {
		hoveredBar = null;
	}
</script>

{#if barras}
	<div class="barChart-container">
		<div class="barChart-header">
			<h3>Porcentaje de fallas</h3>
		</div>

		<div class="barChart-canvas" bind:this={container}>
			<BarChart {barras} hoverBar={handleHoverBar} leaveBar={handleLeaveBar} />
		</div>

		{#if hoveredBar}
			<div
				class="tooltip"
				bind:this={tooltipEl}
				style="left:{tooltipPos.x + 15}px; top:{tooltipPos.y - 10}px"
			>
				<strong>{hoveredBar.label}</strong><br />
				Porcentaje: {hoveredBar.value.toFixed(1)} %
			</div>
		{/if}
	</div>
{:else}
	<p>cargando gráfico de barras</p>
{/if}

<style>
	.tooltip text {
		font-family: system-ui, Arial, sans-serif;
		text-align: right;
		font-size: 5px;
	}

	.tooltip {
		position: absolute;
		background: rgb(247, 253, 158);
		border: 1px solid #333;
		padding: 8px;
		border-radius: 6px;
		box-shadow: 0 2px 6px rgba(0, 0, 0, 0.15);
		pointer-events: auto;
		/* z-index: 1000; */
		z-index: 9999;
	}


	.tooltip {
		position: absolute;
		pointer-events: none;
	}

	.barChart-container {
		width: 100%;
		height: 100%;
		background: white;
		border-radius: 8px;
		box-shadow: 0 0 6px rgba(0, 0, 0, 0.1);
		display: flex;
		flex-direction: column; /* HEADER arriba, CANVAS abajo */
		overflow: hidden;
		position: relative;
		z-index: 1;
	}

	.barChart-canvas {
		flex: 1;
		min-height: 0; /* CLAVE en flex */
		position: relative;
		padding-left: 10px;
		padding-right: 10px;

		flex: 1;
		min-height: 0; /* evita colapso dentro de flex */
	}

	.barChart-header {
		flex: 0 0 auto;
		padding: 0.5rem 1rem;
		background: #E9EBEF;
		display: flex;
		justify-content: space-between;
		align-items: center;
		font-size: 1rem;
		font-weight: bold;
		text-align: left;
		color: #222;
		border-bottom: 1px solid #ddd;
	}
</style>
