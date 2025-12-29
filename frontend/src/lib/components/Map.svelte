<script lang="ts">
	import { onMount } from 'svelte';
	// import L from 'leaflet';

	let map: L.Map;

	let medidores = [
		{ id: 1, lat: -34.9214, lng: -57.9544, nombre: 'Medidor 001' },
		{ id: 2, lat: -34.922, lng: -57.96, nombre: 'Medidor 002' },
		{ id: 3, lat: -34.925, lng: -57.952, nombre: 'Medidor 003' }
	];

	onMount(async () => {
	if (typeof window !== 'undefined') {
		const L = await import('leaflet');
		await import('leaflet/dist/leaflet.css'); 
		console.log('Leaflet cargado');

		// asegurate que el contenedor esté disponible
		const mapContainer = document.getElementById('map');
		if (!mapContainer) return;

		if ((mapContainer as any)._leaflet_id != null) {
	(mapContainer as any)._leaflet_id = null;
}

		map = L.map(mapContainer).setView([-34.9214, -57.9544], 13);

		L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
			attribution: '&copy; OpenStreetMap contributors'
		}).addTo(map);

		L.marker([-34.9214, -57.9544]).addTo(map)
			.bindPopup('Medidor 1')
			.openPopup();
	}
});

</script>

<div id="map" class="w-full h-[500px]"></div>

<style>
	#map {
		height: 100%;
	}
</style>
