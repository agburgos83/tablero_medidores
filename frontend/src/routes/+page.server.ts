export async function load({ fetch }) {
  const [kpiRes, mapRes, medRes] = await Promise.all([
    fetch('http://localhost:8080/api/kpis'),
    fetch('http://localhost:8080/api/maps/laplata'),
    fetch('http://localhost:8080/api/mediciones/latest')
  ]);

  return {
    kpis: kpiRes.ok ? await kpiRes.json() : null,
    map: mapRes.ok ? await mapRes.json() : null,
    meters: medRes.ok ? await medRes.json() : []
  };
}
