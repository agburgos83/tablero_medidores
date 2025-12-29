<script lang="ts">
    import { onMount } from 'svelte';
    import { Chart } from 'chart.js';
  
    let canvas: HTMLCanvasElement;
  
    onMount(() => {
      new Chart(canvas, {
        type: 'line',
        data: {
          labels: Array.from({ length: 15 }, (_, i) => `Día ${i + 1}`),
          datasets: [
            {
              label: 'Consumo diario (MWh)',
              data: Array.from({ length: 15 }, () => Math.floor(Math.random() * 100) + 50),
              borderColor: '#0077cc',
              backgroundColor: 'rgba(0, 119, 204, 0.2)',
              tension: 0.3
            }
          ]
        },
        options: {
          responsive: true,
          plugins: {
            legend: {
              display: false
            }
          }
        }
      });
    });
  </script>

  <canvas bind:this={canvas}></canvas>
  