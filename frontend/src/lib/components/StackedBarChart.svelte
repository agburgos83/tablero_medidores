<script lang="ts">
    import { onMount } from 'svelte';
    import { Chart } from 'chart.js/auto';
  
    let canvas: HTMLCanvasElement;
    const zonas = ['Centro', 'Norte', 'Sur'];
    const dias = Array.from({ length: 7 }, (_, i) => `Día ${i + 1}`);
  
    function randomData() {
      return zonas.map(() => Array.from({ length: 7 }, () => Math.floor(Math.random() * 50) + 20));
    }
  
    onMount(() => {
      const [zonaCentro, zonaNorte, zonaSur] = randomData();
  
      new Chart(canvas, {
        type: 'bar',
        data: {
          labels: dias,
          datasets: [
            {
              label: 'Centro',
              data: zonaCentro,
              backgroundColor: '#4caf50'
            },
            {
              label: 'Norte',
              data: zonaNorte,
              backgroundColor: '#2196f3'
            },
            {
              label: 'Sur',
              data: zonaSur,
              backgroundColor: '#f44336'
            }
          ]
        },
        options: {
          responsive: true,
          plugins: {
            title: {
              display: true,
              text: 'Consumo por Zona (últimos 7 días)'
            }
          },
          scales: {
            x: {
              stacked: true
            },
            y: {
              stacked: true
            }
          }
        }
      });
    });
  </script>
  
  <canvas bind:this={canvas}></canvas>
  