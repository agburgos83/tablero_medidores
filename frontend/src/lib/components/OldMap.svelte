<script lang="ts">
	// <!-- <script> -->

	import { json, geoAlbersUsa, geoAlbers, geoPath } from 'd3';
	import { geoIdentity } from 'd3-geo';

	// ts. importado para poder tipar a geometry
	import type { Feature, Geometry } from 'geojson';

	export let map;

	const geoJsonPath = map;
	// const geoJsonPath = 'https://raw.githubusercontent.com/holtzy/D3-graph-gallery/master/DATA/world.geojson';
	// const geoJsonPath = 'https://raw.githubusercontent.com/plotly/datasets/master/geojson-counties-fips.json';

	let width, height;
	let geojson;

	json(geoJsonPath).then((data) => (geojson = data));

	$: console.log(geojson);

	$: projection = geoAlbersUsa().fitSize([width, height], geojson);

	$: pathGenerator = geoPath(projection);

	type County = Feature<Geometry> & {
		path: string;
	};

	let counties: County[] = [];

	$: if (geojson)
		counties = geojson.features.map((feature) => {
			return {
				...feature,
				path: pathGenerator(feature)
			};
		});
</script>

<main bind:clientWidth={width} bind:clientHeight={height}>
	<svg {width} {height}>
		{#each counties as county}
			<path d={county.path} />
		{/each}
	</svg>
</main>

<style>
	main {
		width: 100vm;
		heigth: 100vh;
		overflow: hidden;
	}
</style>
