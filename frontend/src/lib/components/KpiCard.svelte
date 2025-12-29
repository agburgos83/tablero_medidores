<script lang="ts">
    // export let titulo: string;
    // export let valor: string | number;
    // export let colorFondo: string = "#f5f5f5";

    export let kpis: {
        totalMedidores: number;
        medidoresConFalla: number;
        consumoTotalDiario: number;
        temperaturaPromedio: number;
    };

</script>
{#if kpis}

<div class="grid grid-cols-1 md;grid-cols-4 gap-4">
    <div class="bg-white rounded-xl p-4 shadow">
        <h2 class="text-gray-600">Total Medidores</h2>
        <p class="text-2xl font-bold text-red-500">{kpis.totalMedidores}</p>
    </div>

    <div class="bg-white rounded-xl p-4 shadow">
        <h2 class="text-gray-600">Medidores con fallas</h2>
        <p class="text-2xl font-bold text-red-500">{kpis.medidoresConFalla}</p>
    </div>

    <div class="bg-white rounded-xl p-4 shadow">
        <h2 class="text-gray-600">Consumo diario</h2>
        <p class="text-2xl font-bold text-red-500">{kpis.consumoTotalDiario}</p>
    </div>

    <div class="bg-white rounded-xl p-4 shadow">
        <h2 class="text-gray-600">Temperatura promedio</h2>
        <p class="text-2xl font-bold text-red-500">{kpis.temperaturaPromedio}</p>
    </div>


</div>
{:else}
	<p>Cargando KPIs...</p>
{/if}