import { API_BASE_PRODUCTS } from "../config/config";

export async function getProducts() {
  const res = await fetch(API_BASE_PRODUCTS);
  if (!res.ok) throw new Error("Error al cargar productos");
  return res.json();
}

export async function createProduct(product) {
  const res = await fetch(API_BASE_PRODUCTS, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(product),
  });
  if (!res.ok) throw new Error("Error creando producto");
  return res.json();
}

export async function updateProduct(id, product) {
  const res = await fetch(`${API_BASE_PRODUCTS}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(product),
  });
  if (!res.ok) throw new Error("Error actualizando producto");
  return res.json();
}

export async function deleteProduct(id) {
  const res = await fetch(`${API_BASE_PRODUCTS}/${id}`, { method: "DELETE" });
  if (!res.ok) throw new Error("Error eliminando producto");
  return res.json();
}

// Buscar producto por id
export async function getProductById(id) {
  const res = await fetch(`${API_BASE_PRODUCTS}/${id}`);
  if (!res.ok) throw new Error("Producto no encontrado");
  return res.json();
}