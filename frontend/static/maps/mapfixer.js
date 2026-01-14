import fs from "fs";

const data = JSON.parse(fs.readFileSync("Localidades - La Plata.json"));

data.features.forEach(f => {
  if (f.geometry === null && f.properties["Geometría en GeoJSON"]) {
    f.geometry = JSON.parse(f.properties["Geometría en GeoJSON"]);
    delete f.properties["Geometría en GeoJSON"];
  }
});

fs.writeFileSync("bien.geojson", JSON.stringify(data));
