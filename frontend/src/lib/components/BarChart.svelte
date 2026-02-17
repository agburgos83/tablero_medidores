<script lang="ts">
	import * as d3 from 'd3';
	import type { bar } from '$lib/domain/bar';

	export let barras: bar[];

	let container: HTMLDivElement;
	$: width = container?.clientWidth ?? 0;
	$: height = container?.clientHeight ?? 0;

	export let marginTop = 30;
	export let marginRight = 0;
	export let marginBottom = 50;
	export let marginLeft = 40;

	let xScale = d3
		.scaleBand()
		.range([marginLeft, width - marginRight])
		.padding(0.1);

	let yScale = d3
		.scaleLinear()
		.domain([0, 100])
		.range([height - marginBottom, marginTop]);

	$: if (width > 0) {
		xScale = d3
			.scaleBand()
			.domain(barras.map((b) => b.key))
			.range([marginLeft, width - marginRight])
			.padding(0.2);
	}

	$: if (height > 0) {
		yScale = d3
			.scaleLinear()
			.domain([0, 100])
			.range([height - marginBottom, marginTop]);
	}

	export let hoverBar: (b: bar, x: number, y: number) => void;
	export let leaveBar: () => void;
</script>

<div class="chart-root" bind:this={container}>
	<svg width="100%" height="100%" viewBox={`0 0 ${width} ${height}`}>
		<g fill="#78899F">
			{#each barras as b}
				<rect
					x={xScale(b.key) ?? 0}
					y={yScale(b.value)}
					height={yScale(0) - yScale(b.value)}
					width={xScale.bandwidth()}
				/>
			{/each}
		</g>

		<!-- x axis -->
		<g transform="translate(0,{height - marginBottom})">
			<!-- <line stroke="currentColor" x1={marginLeft - 6} x2={width} /> -->

			<!-- labels -->
			{#each barras as b}
				<text
					text-anchor="middle"
					x={(xScale(b.key) ?? 0) + xScale.bandwidth() / 2}
					y={22}
					font-size="10"
					fill="black"
					lengthAdjust="spacingAndGlyphs"
				>
					{b.label}
				</text>
			{/each}
		</g>

		<g transform="translate({marginLeft}, 0)">
			{#each yScale.ticks() as tick}
				{#if tick !== 0}
					<text
						text-anchor="end"
						dominant-baseline="middle"
						x={-9}
						y={yScale(tick)}
						font-size="10"
						fill="black"
						lengthAdjust="spacingAndGlyphs"
					>
						{Math.trunc(tick)}%
					</text>
				{/if}
			{/each}
		</g>
	</svg>

	<div class="bars-layer">
	{#each barras as b}
		<div
			class="bar-hitbox"
			style="
				left: {(xScale(b.key) ?? 0)}px;
				width: {xScale.bandwidth()}px;
				top: {yScale(b.value)}px;
				height: {yScale(0) - yScale(b.value)}px;
			"
			on:mouseenter={(e) => hoverBar(b, e.clientX, e.clientY)}
			on:mousemove={(e) => hoverBar(b, e.clientX, e.clientY)}
			on:mouseleave={leaveBar}
		/>
	{/each}
</div>

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
	pointer-events: none;
}

.bars-layer {
	position: absolute;
	inset: 0;
	pointer-events: none;
}

.bar-hitbox {
	position: absolute;
	pointer-events: all;
	cursor: pointer;
}

</style>
