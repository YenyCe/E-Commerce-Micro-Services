import { API_BASE_CUSTOMERS } from "../config/config";

// Obtener todos los clientes
export const getCustomers = async () => {
  const res = await fetch(API_BASE_CUSTOMERS);
  if (!res.ok) throw new Error("No se pudo obtener la lista de clientes");
  return await res.json();
};

// Obtener un cliente por id
export const getCustomer = async (id) => {
  const res = await fetch(`${API_BASE_CUSTOMERS}/${id}`);
  if (!res.ok) throw new Error("No se pudo obtener el cliente");
  return await res.json();
};

// Crear cliente
export const createCustomer = async (customer) => {
  const res = await fetch(API_BASE_CUSTOMERS, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(customer),
  });
  if (!res.ok) throw new Error("No se pudo crear el cliente");
  return await res.json();
};

// Actualizar cliente
export const updateCustomer = async (id, customer) => {
  const res = await fetch(`${API_BASE_CUSTOMERS}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(customer),
  });
  if (!res.ok) throw new Error("No se pudo actualizar el cliente");
  return await res.json();
};

// Eliminar cliente
export const deleteCustomer = async (id) => {
  const res = await fetch(`${API_BASE_CUSTOMERS}/${id}`, {
    method: "DELETE",
  });
  if (!res.ok) throw new Error("No se pudo eliminar el cliente");
  return true;
};
