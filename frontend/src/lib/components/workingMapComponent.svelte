<script lang="ts">
	import { onMount } from 'svelte';
	import { geoIdentity, geoPath } from 'd3-geo';

	export let src: string = '/maps/partidos.geojson';
	export let filterName: string = 'La Plata';

	let container: HTMLDivElement;
	let width = 0;
	let height = 0;
	let paths: string[] = [];

	onMount(async () => {
		// esperar layout
		await new Promise(requestAnimationFrame);

		const r = container.getBoundingClientRect();
		width = r.width;
		height = r.height;

		const res = await fetch(src);
		const geojson = await res.json();

		// filtrar solo La Plata
		const features = geojson.features.filter(
			(f: any) => f.properties.nam === filterName
		);

		// proyección simple (geoIdentity) para coordenadas planas
		const projection = geoIdentity().reflectY(true).fitSize([width, height], {
			type: 'FeatureCollection',
			features
		});

		const pathGen = geoPath().projection(projection);

		paths = features.map((f: any) => pathGen(f)).filter(Boolean);
	});
</script>

<div bind:this={container} style="width:100%; height:100%;">
	{#if paths.length}
		<svg width={width} height={height} style="display:block;">
			{#each paths as d}
				<path d={d} fill="rgba(0,120,200,.2)" stroke="#003" />
			{/each}
		</svg>
	{:else}
		Cargando…
	{/if}
</div>
