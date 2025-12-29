// since there's no dynamic data here, we can prerender
// it so that it gets served as a static asset in production
// export const prerender = true;

export async function load({ fetch }) {
    try {
        const res = await fetch('http://localhost:8080/api/kpis');

        if (!res.ok) {
            throw new Error('Error al obtener KPIs');
        }

        const kpis = await res.json();

        return { kpis };

    } catch (error) {
        console.error('Error al cargar KPIs:', error);
        return { kpis: null };
    }

}
