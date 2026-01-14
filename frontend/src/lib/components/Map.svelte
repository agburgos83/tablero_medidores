<script lang="ts">
	import { geoMercator, geoPath } from 'd3-geo';
	import { getSeverity, THRESHOLDS } from '$lib/domain/severity';

	export let onSelect: (m: MeterPoint) => void = () => {};

	export let map;
	export let meters: Medicion[] = [];
	let width = 0;
	let height = 0;
	let hovered: string | null = null;
	export let filter: 'todos' | 'normales' | 'alerta' | 'critico' = 'todos';

	$: visibleMeters = meterPoints ?? [];

	type Meter = {
		medidorId: number;
		latitud: number;
		longitud: number;
		direccion: string;
	};

	type MeterPoint = Meter & {
		x: number;
		y: number;
		severity: string;
		aire?: number;
		caudalCero?: number;
		flujoInverso?: number;
		nivelDeBateria?: number;
		perdida?: number;
	};

	function notNull<T>(v: T | null): v is T {
		return v !== null;
	}

	function isPoint(v: any): v is MeterPoint {
		return v !== null;
	}

	$: ready = map && width > 0 && height > 0;

	$: {
		console.log('MAP SIZE:', width, height, 'READY:', ready);
	}

	$: projection = ready ? geoMercator().fitSize([width, height], map) : null;

	$: path = projection ? geoPath(projection) : null;

	$: console.log('meterPoints', meterPoints);

	$: localidades =
		ready && path && Array.isArray(map?.features)
			? map.features.map((f) => ({
					...f,
					d: path(f)
				}))
			: [];

	$: meterPoints =
		ready && projection
			? meters
					.map((m) => {
						const p = projection([m.longitud, m.latitud]);
						if (!p) return null;

						const base: Meter = {
							medidorId: m.medidorId,
							latitud: m.latitud,
							longitud: m.longitud,
							direccion: m.direccion
						};

						const point: MeterPoint = {
							...base,
							x: p[0],
							y: p[1],
							severity: getSeverity(m),
							aire: m.aire,
							caudalCero: m.caudalCero,
							flujoInverso: m.flujoInverso,
							nivelDeBateria: m.nivelDeBateria,
							perdida: m.perdida
						};

						return point;
					})
					.filter(notNull)
			: [];

	function opacityFor(m: MeterPoint) {
		if (filter === 'todos') return 1;
		return m.severity === filter ? 1 : 0.25;
	}

	let hoveredMeter: MeterPoint | null = null;

	const metricKeys = Object.keys(THRESHOLDS) as Array<keyof typeof THRESHOLDS>;
</script>

<div class="canvas" bind:clientWidth={width} bind:clientHeight={height}>
	{#if ready}
		<svg viewBox={`0 0 ${width} ${height}`}>
			<g class="localidades">
				{#each localidades as localidad}
					<path
						d={localidad.d}
						fill="#ccc"
						stroke="#333"
						stroke-width="1"
						role="presentation"
						aria-label={localidad.properties.Nombre}
						on:mouseenter={() => (hovered = localidad.properties.Nombre)}
						on:mouseleave={() => (hovered = null)}
					/>
				{/each}
			</g>

			<g class="medidores">
				{#each visibleMeters as m}
					<circle
						cx={m.x}
						cy={m.y}
						r="5"
						fill="#1e3a5f"
						stroke="white"
						stroke-width="2"
						opacity={opacityFor(m)}
						on:mouseenter={() => (hoveredMeter = m)}
						on:mouseleave={() => (hoveredMeter = null)}
						on:click={() => { hoveredMeter = m; onSelect(m); }}
						style="cursor: pointer;"
					/>
				{/each}

				{#if hoveredMeter}
					<g
						class="tooltip"
						transform={`translate(${hoveredMeter.x + 10}, ${hoveredMeter.y - 30})`}
						pointer-events="none"
					>
						<rect
							x="0"
							y="-4"
							width="270"
							height={45 + metricKeys.length * 16}
							rx="6"
							fill=#ffffe0
							stroke="#333"
							opacity="0.95"
						/>
						<text x="8" y="12" font-size="12" fill="#000" font-weight="bolder">Medidor: {hoveredMeter.medidorId}</text>
						<text x="120" y="12" font-size="12" fill="#000" font-weight="bolder"
							>Dirección: {hoveredMeter.direccion}</text
						>

						{#each metricKeys as key, i}
                            <!-- obtener el valor real; THRESHOLDS usa "bateria" pero el medidor tiene "nivelDeBateria" -->
                            {#if (key === 'bateria' ? hoveredMeter.nivelDeBateria !== undefined : hoveredMeter[key] !== undefined)}
                                <text x="8" y={30 + i * 16} font-size="12" fill="#000">
                                    {key}: {(key === 'bateria' ? hoveredMeter.nivelDeBateria : hoveredMeter[key])}
                                    <tspan> (warn: {THRESHOLDS[key].warn}, crit: {THRESHOLDS[key].crit})</tspan>
                                </text>
                            {:else}
                                <text x="8" y={30 + i * 16} font-size="12" fill="#666">
                                    {key}: n/a (warn: {THRESHOLDS[key].warn}, crit: {THRESHOLDS[key].crit})
                                </text>
                            {/if}
                        {/each}

						<!-- severidad al final -->
						<text x="8" y={30 + metricKeys.length * 16} font-size="12" fill="#000"
							>Severidad: {hoveredMeter.severity}</text
						>
					</g>
				{/if}
			</g>
		</svg>
	{/if}
</div>

<style>
	.canvas {
		width: 100%;
		height: 100%;
		min-height: 500px;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	svg {
		width: 90%;
		height: 90%;
		display: block;
	}

	.tooltip text { font-family: system-ui, Arial, sans-serif; }
	
</style>
