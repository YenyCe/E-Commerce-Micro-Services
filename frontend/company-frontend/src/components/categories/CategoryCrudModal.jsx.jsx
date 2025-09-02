import { useEffect, useState } from "react";
import {
  getCategories,
  createCategory,
  deleteCategory,
  updateCategory
} from "../../api/categoryApi";

export default function CategoryCrudModal({ isOpen, onClose }) {
  const [categories, setCategories] = useState([]);
  const [form, setForm] = useState({ id: null, name: "", description: "" });
  const [error, setError] = useState("");

  const loadCategories = async () => {
    try {
      const data = await getCategories();
      setCategories(data);
    } catch (err) {
      setError(err.message);
    }
  };

  useEffect(() => {
    if (isOpen) loadCategories();
  }, [isOpen]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSave = async () => {
    if (!form.name.trim()) return;

    try {
      if (form.id) {
        // Editar
        await updateCategory(form.id, { name: form.name, description: form.description });
      } else {
        // Crear
        await createCategory({ name: form.name, description: form.description });
      }
      setForm({ id: null, name: "", description: "" });
      loadCategories();
    } catch (err) {
      setError(err.message);
    }
  };

  const handleEdit = (category) => {
    setForm({ id: category.id, name: category.name, description: category.description });
  };

  const handleDelete = async (id) => {
    if (!window.confirm("¿Eliminar categoría?")) return;
    try {
      await deleteCategory(id);
      loadCategories();
    } catch (err) {
      setError(err.message);
    }
  };

  if (!isOpen) return null;

  return (
    <div className="modal-overlay">
      <div className="modal">
        <h2>CRUD de Categorías</h2>

        {error && <p style={{ color: "red" }}>{error}</p>}

        {/* Formulario */}
        <div className="form-group">
          <input
            type="text"
            name="name"
            placeholder="Nombre"
            value={form.name}
            onChange={handleChange}
          />
          <input
            type="text"
            name="description"
            placeholder="Descripción"
            value={form.description}
            onChange={handleChange}
          />
          <button onClick={handleSave}>
            {form.id ? "Actualizar" : "Agregar"}
          </button>
          {form.id && (
            <button onClick={() => setForm({ id: null, name: "", description: "" })}>
              Cancelar
            </button>
          )}
        </div>

        {/* Tabla */}
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Descripción</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {categories.map((c) => (
              <tr key={c.id}>
                <td>{c.id}</td>
                <td>{c.name}</td>
                <td>{c.description}</td>
                <td>
                  <button onClick={() => handleEdit(c)}>Editar</button>
                  <button onClick={() => handleDelete(c.id)}>Eliminar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        <button style={{ marginTop: "1rem" }} onClick={onClose}>
          Cerrar
        </button>
      </div>

    </div>
  );
}
