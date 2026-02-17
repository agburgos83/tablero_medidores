<script lang="ts">
	import * as d3 from 'd3';

	import type { fraction } from '$lib/domain/fraction';
	import { onMount, tick } from 'svelte';

	// Fracciones del gauge (normal, alerta, critico)
	export let fractions: fraction[];

	// Callbacks hacia el componente padre
	export let hoverFraction: (f: fraction, x: number, y: number) => void;
	export let leaveFraction: () => void;

	// Referencia al contenedor HTML
	let container: HTMLDivElement;

	// Tamaño real del contenedor
	let width = 0;
	let height = 0;

	onMount(async () => {
		await tick(); // espera a que Svelte termine el DOM
		await new Promise((r) => requestAnimationFrame(r)); // espera layout
		await new Promise((r) => requestAnimationFrame(r)); // espera grid

		width = container.clientWidth;
		height = container.clientHeight;

	});

	// Suma total de todas las fracciones
	// Ej: normal=40, alerta=35, critico=25 → total = 100
	$: total = d3.sum(fractions, (f) => f.value);

	// El gauge es media circunferencia:
	// de -90° a +90°
	const startAngle = -Math.PI / 2;
	const endAngle = Math.PI / 2;

	// Radio del gauge, depende del tamaño del contenedor
	// height * 2 porque el gauge solo usa la mitad superior
	$: radius = Math.min(width, height * 2) * 0.45;

	$: innerR = radius * 0.65;
	$: outerR = radius * 0.92;

	// Generador de arcos SVG
	// Esto NO dibuja nada aún
	// Solo sabe convertir un "slice" en un path SVG
	$: arcGen = d3.arc<any>().innerRadius(innerR).outerRadius(outerR).cornerRadius(2);

	// Generador de "torta"
	// Convierte fractions[] → slices con startAngle/endAngle
	$: pie = d3
		.pie<fraction>()
		.value((d) => d.value) // qué campo usar para el tamaño
		.startAngle(startAngle) // donde empieza el gauge
		.endAngle(endAngle) // donde termina
		.padAngle(0.015) // separacion
		.sort(null); // NO reordenar

	// arcs es la estructura real que vamos a dibujar
	// Cada elemento tiene:
	//  - data (fraction)
	//  - startAngle
	//  - endAngle
	$: arcs = pie(fractions);

	// Centro del gauge (X)
	function centerX() {
		return width / 2;
	}

	// Centro del gauge (Y)
	// Está más abajo porque es un semicírculo
	function centerY() {
		return height * 0.9;
	}

	// Colores por tipo
	function colorFor(key: string) {
		const base = d3.color('#78899F');

		if (!base) return '#999';

		if (key === 'normal') {
			return base.brighter(0.6).formatHex(); // más claro
		}

		if (key === 'alerta') {
			return base.brighter(0.4).formatHex(); // medio
		}

		if (key === 'critico') {
			return base.formatHex(); 
			// return base; 
		}

		return base.formatHex();
	}

	// Posiciona los hitboxes sobre cada arco
	function hitboxStyle(a) {
		const a0 = a.startAngle;
		const a1 = a.endAngle;

		const r1 = innerR;
		const r2 = outerR;

		const cx = centerX();
		const cy = centerY();

		const x1 = cx + Math.cos(a0) * r1;
		const y1 = cy + Math.sin(a0) * r1;

		const x2 = cx + Math.cos(a1) * r2;
		const y2 = cy + Math.sin(a1) * r2;

		const left = Math.min(x1, x2);
		const top = Math.min(y1, y2);
		const width = Math.abs(x2 - x1);
		const height = Math.abs(y2 - y1);

		return `
		left:${left}px;
		top:${top}px;
		width:${width}px;
		height:${height}px;
	`;
	}
</script>

<div class="chart-root" bind:this={container}>
  <svg viewBox={`0 0 ${width} ${height}`}>
    {#if width > 0 && height > 0}
      {#each arcs as a}
        <path
          d={arcGen(a)}
          transform={`translate(${centerX()}, ${centerY()})`}
          fill={colorFor(a.data.key)}
          style="cursor:pointer"
          on:mousemove={(e) => hoverFraction(a.data, e.clientX, e.clientY)}
          on:mouseleave={leaveFraction}
        />
      {/each}
    {/if}
  </svg>
</div>


<style>
	.chart-root {
		position: relative;
		width: 100%;
		height: 100%;
	}

	svg {
		position: absolute;
		inset: 0;
	}

	.fraction-layer {
		position: absolute;
		inset: 0;
	}

	/* Hitboxes invisibles */
	.fraction-hitbox {
		position: absolute;
		pointer-events: all;
		cursor: pointer;
	}
</style>
