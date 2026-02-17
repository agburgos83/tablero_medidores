<script lang="ts">
	import MapMeter from './MapMeter.svelte';

	import type { medicion } from '$lib/domain/medicion';
	import type { meterPoint } from '$lib/domain/meterpoint';
	import { getSeverity } from '$lib/domain/severity';

	import { onDestroy, onMount } from 'svelte';
	import { browser } from '$app/environment';

	// 1) PROPS DEL COMPONENTE

	// Lista completa de mediciones que llega desde +page.svelte
	export let meters: medicion[] = [];

	// GeoJSON del mapa (La Plata)
	export let map;

	// 2) ESTADO LOCAL (UI)

	// Medidor actualmente seleccionado (null = no hay tooltip)
	let clickedMeter: meterPoint | null = null;

	// Posición en pantalla del tooltip
	let tooltipPos = { x: 0, y: 0 };

	// Filtro activo seleccionado por el usuario
	let selectedFilter: 'todos' | 'normales' | 'alerta' | 'critico' = 'todos';

	// 3) DERIVADAS REACTIVAS

	// Medidores visibles según el filtro activo
	$: visibleMeters = meters.filter((m) => {
		const severity = getSeverity(m);

		if (selectedFilter === 'todos') return true;
		if (selectedFilter === 'normales') return severity === 1;
		if (selectedFilter === 'alerta') return severity === 2;
		if (selectedFilter === 'critico') return severity === 3;

		return true;
	});

	// 4) EVENT HANDLERS (INTERACCIÓN)

	// Cuando se hace click en un punto del mapa
	function handleSelectMeter(m: meterPoint, x: number, y: number) {
		clickedMeter = m;
		tooltipPos = { x, y };
	}

	// Cambiar filtro y cerrar tooltip
	function setFilter(f: 'todos' | 'normales' | 'alerta' | 'critico') {
		selectedFilter = f;
		clickedMeter = null;
	}

	// Cerrar tooltip con ESC
	function handleKeydown(e: KeyboardEvent) {
		if (e.key === 'Escape') {
			clickedMeter = null;
		}
	}

	// 5) CICLO DE VIDA

	onMount(() => {
		if (browser) {
			window.addEventListener('keydown', handleKeydown);
		}
	});

	onDestroy(() => {
		if (browser) {
			window.removeEventListener('keydown', handleKeydown);
		}
	});
</script>

<div class="map-section">
	<div class="map-container" onclick={() => (clickedMeter = null)}>
		<div class="map-header">
			<h3>Mapa de medidores</h3>
		</div>

		<div class="map-canvas">
			<div class="map-filters">
				<button onclick={() => setFilter('todos')} class:selected={selectedFilter === 'todos'}
					>Todos</button
				>
				<button onclick={() => setFilter('normales')} class:selected={selectedFilter === 'normales'}
					>Normales</button
				>
				<button onclick={() => setFilter('alerta')} class:selected={selectedFilter === 'alerta'}
					>Alerta</button
				>
				<button onclick={() => setFilter('critico')} class:selected={selectedFilter === 'critico'}
					>Críticos</button
				>
			</div>

			<MapMeter {map} meters={visibleMeters} onSelectMeter={handleSelectMeter} />
		</div>

		{#if clickedMeter}
			<div class="tooltip" style="left: {tooltipPos.x + 20}px; top: {tooltipPos.y - 10}px">
				<strong>ID Medidor:</strong>
				{clickedMeter.medidorId} <br />
				<strong>Dirección:</strong>
				{clickedMeter.direccion} <br />
				Aire: {clickedMeter.aire} <br />
				Caudal cero: {clickedMeter.caudalCero} <br />
				Flujo inverso: {clickedMeter.flujoInverso} <br />
				Batería: {clickedMeter.nivelDeBateria} <br />
				Pérdida: {clickedMeter.perdida} <br />
				Severidad: {clickedMeter.severity} <br />
			</div>
		{/if}
	</div>
</div>

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
		z-index: 1000;
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

	.map-filters {
		position: absolute;
		top: 12px; /* separación respecto al tope */
		right: 12px; /* separación respecto al borde derecho */
		display: flex;
		gap: 0.5rem;
		padding: 0px;
		background: rgba(255, 255, 255, 0.9);
		border-radius: 6px;
		box-shadow: 0 2px 6px rgba(0, 0, 0, 0.08);
		z-index: 10;
	}

	.map-canvas :global(svg) {
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
	}

	.map-container {
		width: 100%;
		height: 600px;
		background: white;
		border-radius: 8px;
		box-shadow: 0 0 6px rgba(0, 0, 0, 0.1);
		display: flex;
		flex-direction: column; /* HEADER arriba, CANVAS abajo */
		overflow: hidden;
		position: relative;
	}

	.map-canvas {
		position: relative;
		flex: 1; /* ocupa TODO lo que sobra debajo del header */
		width: 100%;
	}

	.map-header {
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

	.map-section {
		display: flex;
		flex-direction: column;
		width: 100%;
	}
</style>
