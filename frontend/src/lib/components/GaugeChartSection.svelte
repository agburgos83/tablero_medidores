<script lang="ts">
	import type { medicion } from '$lib/domain/medicion';
    import type {fraction} from '$lib/domain/fraction';
	import { computeHealth } from '$lib/domain/computeHealth';
	import GaugeChart from './GaugeChart.svelte';


	// Lista completa de mediciones que llega desde +page.svelte
	export let meters: medicion[] = [];

	$: valores = computeHealth(meters);

	export let fractions: fraction[] = [];

	$: if (valores) {
		fractions = [
			{ key: 'normal', label: 'Normal', value: valores.normal },
			{ key: 'alerta', label: 'Alerta', value: valores.alerta },
			{ key: 'critico', label: 'Crítico', value: valores.critico }
		];
	}

	let container: HTMLDivElement;

	let hoveredFraction: fraction | null = null;
	let tooltipPos = { x: 0, y: 0 };
	let tooltipEl: HTMLDivElement;

	function handleHoverfraction(f: fraction, x: number, y: number) {
		const rect = container.getBoundingClientRect();

		let tx = x - rect.left - 120; // pequeño offset
		let ty = y - rect.top - 30;

		hoveredFraction = f;

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

	function handleLeaveFraction() {
		hoveredFraction = null;
	}

</script>

{#if fractions}
	<div class="gaugeChart-container">
		<div class="gaugeChart-header">
			<h3>Salud de la red</h3>
		</div>

		<div class="gaugeChart-canvas" bind:this={container}>
			<GaugeChart {fractions} hoverFraction={handleHoverfraction} leaveFraction={handleLeaveFraction} />
		</div>

		{#if hoveredFraction}
			<div
				class="tooltip"
				bind:this={tooltipEl}
				style="left:{tooltipPos.x + 15}px; top:{tooltipPos.y - 10}px"
			>
				<strong>{hoveredFraction.label}</strong><br />
				Porcentaje: {hoveredFraction.value.toFixed(1)} %
			</div>
		{/if}
	</div>
{:else}
	<p>cargando gauge</p>
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

	.gaugeChart-container {
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

	.gaugeChart-canvas {
		flex: 1;
		min-height: 0; /* CLAVE en flex */
		position: relative;
		padding-left: 10px;
		padding-right: 10px;

		flex: 1;
		min-height: 0; /* evita colapso dentro de flex */
	}

	.gaugeChart-header {
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
