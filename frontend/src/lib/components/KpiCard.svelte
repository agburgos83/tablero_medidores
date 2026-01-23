<script lang="ts">
	export let title: string;
	export let value: number;
</script>

<div class="card">
	<h2 class="title">{title}</h2>
	<p class="value">{value}</p>
</div>

<style>
	.card {
		/* background: linear-gradient(180deg, #fff, rgb(255, 234, 253)); */
		background: linear-gradient(180deg, #fff, rgb(247, 253, 158));
		height: 110px;
		border-radius: 0.75rem;
		padding: 1rem;
		text-align: center;
		box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
	}

	.title {
		margin-bottom: -0.5rem;
		font-weight: bold;
		color: #4b5563; /* gris tipo tailwind */
	}

	.value {
		font-size: 3rem;
		font-weight: bold;
		color: #ef4444; /* rojo tipo tailwind */
	}
</style>
