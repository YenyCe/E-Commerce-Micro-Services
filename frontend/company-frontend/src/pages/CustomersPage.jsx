import { useEffect, useState } from "react";
import CustomerList from "../components/customers/CustomerList";
import CustomerForm from "../components/customers/CustomerForm";
import {API_BASE_CUSTOMERS } from "../config/config";

const emptyCustomer = {
  id: "",
  firstname: "",
  lastname: "",
  email: "",
  address: { street: "", houseNumber: "", zipCode: "" }
};

const CustomersPage = () => {
  const [customers, setCustomers] = useState([]);
  const [form, setForm] = useState({ ...emptyCustomer });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [isEditing, setIsEditing] = useState(false);

  const loadCustomers = async () => {
    const res = await fetch(API_BASE_CUSTOMERS);
    if (!res.ok) throw new Error("No se pudo obtener la lista");
    return await res.json();
  };

  useEffect(() => {
    (async () => {
      try {
        const data = await loadCustomers();
        setCustomers(data);
      } catch (err) {
        setError(err.message || "Error cargando clientes");
      } finally {
        setLoading(false);
      }
    })();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (["street", "houseNumber", "zipCode"].includes(name)) {
      setForm(prev => ({ ...prev, address: { ...prev.address, [name]: value } }));
    } else {
      setForm(prev => ({ ...prev, [name]: value }));
    }
  };

  const handleSave = async (e) => {
    e.preventDefault();
    setError("");
    const payload = { ...form };
    const url = isEditing ? `${API_BASE_CUSTOMERS}/${form.id}` : API_BASE_CUSTOMERS;
    const method = isEditing ? "PUT" : "POST";

    const res = await fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload)
    });

    if (!res.ok) {
      setError("No se pudo guardar el cliente");
      return;
    }

    const data = await loadCustomers();
    setCustomers(data);
    setForm({ ...emptyCustomer });
    setIsEditing(false);
  };

  const handleDelete = async (id) => {
    if (!window.confirm("¿Seguro que quieres eliminar este cliente?")) return;
    const res = await fetch(`${API_BASE_CUSTOMERS}/${id}`, { method: "DELETE" });
    if (!res.ok) {
      setError("No se pudo eliminar el cliente");
      return;
    }
    setCustomers(customers.filter(c => c.id !== id));
  };

  const handleEdit = (customer) => {
    setForm(customer);
    setIsEditing(true);
  };

  const handleCancel = () => {
    setForm({ ...emptyCustomer });
    setIsEditing(false);
  };

  if (loading) return <div className="container">Cargando...</div>;
  if (error) return <div className="container" style={{ color: "red" }}>{error}</div>;

  return (
    <div className="container">
      <h1>Microservice Customer</h1>
      <CustomerForm 
        form={form} 
        handleChange={handleChange} 
        handleSave={handleSave} 
        isEditing={isEditing}
        handleCancel={handleCancel}
      />
      <CustomerList 
        customers={customers} 
        handleEdit={handleEdit} 
        handleDelete={handleDelete} 
      />
    </div>
  );
};

export default CustomersPage;
