<script lang="ts">
	import { geoMercator, geoPath } from 'd3-geo';
	import { getSeverity } from '$lib/domain/severity';
	import type { meterPoint } from '$lib/domain/meterpoint';
	import type { medicion } from '$lib/domain/medicion';

	export let map;
	export let meters: medicion[];
	export let onSelectMeter: (m: meterPoint, x: number, y: number) => void;

	let width = 0;
	let height = 0;

	$: ready = map && width > 0 && height > 0;

	$: projection = ready ? geoMercator().fitSize([width, height], map) : null;

	$: path = projection ? geoPath(projection) : null;

	$: localidades =
		ready && path && Array.isArray(map?.features)
			? map.features.map((f) => ({
					...f,
					d: path(f)
				}))
			: [];

	function notNull<T>(v: T | null): v is T {
		return v !== null;
	}

	$: meterPoints =
		ready && projection
			? meters
					.map((m) => {
						const p = projection([m.longitud, m.latitud]);
						if (!p) return null;

						return {
							medidorId: m.medidorId,
							latitud: m.latitud,
							longitud: m.longitud,
							direccion: m.direccion,
							x: p[0],
							y: p[1],
							severity: getSeverity(m),
							aire: m.aire,
							caudalCero: m.caudalCero,
							flujoInverso: m.flujoInverso,
							nivelDeBateria: m.nivelDeBateria,
							perdida: m.perdida
						} satisfies meterPoint;
					})
					.filter(notNull)
			: [];
</script>

<div class="map" bind:clientWidth={width} bind:clientHeight={height}>
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
					/>
				{/each}
			</g>

			<g class="medidores">
				{#each meterPoints as m}
					<circle
						cx={m.x}
						cy={m.y}
						r="5"
						fill="#1e3a5f"
						stroke="white"
						stroke-width="2"
						style="cursor: pointer;"
						on:click|stopPropagation={() => onSelectMeter(m, m.x, m.y)}
					/>
				{/each}
			</g>
		</svg>
	{/if}
</div>

<style>
	svg {
		width: 90%;
		height: 90%;
		display: block;
	}

	.map {
		width: 100%;
		height: 100%; /* ocupa todo el contenedor */
		position: relative;
	}
</style>
