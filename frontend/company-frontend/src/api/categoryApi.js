import { CATEGORY_API } from "../config/config";

// Obtener todas las categorías
export async function getCategories() {
  const res = await fetch(CATEGORY_API);
  if (!res.ok) throw new Error("Error al cargar categorías");
  return res.json();
}
// Crear categoría
export async function createCategory(category) {
  const res = await fetch(CATEGORY_API, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(category),
  });
  if (!res.ok) throw new Error("Error al crear categoría");
  return res.json();
}

// Actualizar categoría
export async function updateCategory(id, category) {
  const res = await fetch(`${CATEGORY_API}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(category),
  });
  if (!res.ok) throw new Error("Error al actualizar categoría");
  return res.json();
}

// Eliminar categoría
export async function deleteCategory(id) {
  const res = await fetch(`${CATEGORY_API}/${id}`, { method: "DELETE" });
  if (!res.ok) throw new Error("Error al eliminar categoría");
  return res.text(); // puede ser vacío, depende de tu backend
}