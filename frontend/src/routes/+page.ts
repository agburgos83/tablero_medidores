
export async function load({ fetch }) {
    try {
        const [ kpiRes, mapRes, medRes ] = await Promise.all([
            fetch('http://localhost:8080/api/kpis'),
            fetch('http://localhost:8080/api/maps/laplata'),
            fetch('http://localhost:8080/api/mediciones/latest')
        ]);

    

    if (!kpiRes.ok) throw new Error('KPIs fallaron');
    if (!mapRes.ok) throw new Error('Mapa falló');
    if (!medRes.ok) throw new Error('Mediciones fallaron')
    
    return {
        map: await mapRes.json(),
        kpis: await kpiRes.json(),
        meters: await medRes.json()
    };
    } catch (err) {
        console.error(err);
        return {
            kpis: null,
            map: null,
            meters: null
        };
    }

}
