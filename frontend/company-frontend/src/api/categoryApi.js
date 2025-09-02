import { CATEGORY_API } from "../config/config";



export async function getCategories() {
  const res = await fetch(CATEGORY_API);
  if (!res.ok) throw new Error("Error al cargar categorías");
  return res.json();
}
