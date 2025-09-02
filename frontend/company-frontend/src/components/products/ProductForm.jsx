import { useState, useEffect } from "react";
import { createProduct, updateProduct} from "../../api/productApi";
import CategorySelect from "./CategorySelect";

const emptyProduct = {
  name: "",
  description: "",
  availableQuantity: 0,
  price: 0,
  categoryId: "",
};

export default function ProductForm({ productToEdit, onSave }) {
  const [form, setForm] = useState({ ...emptyProduct });
  const [isEditing, setIsEditing] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    if (productToEdit) {
      setForm(productToEdit);
      setIsEditing(true);
    }
  }, [productToEdit]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]:
        name === "price" ||
        name === "availableQuantity" ||
        name === "categoryId"
          ? Number(value)
          : value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    try {
      if (isEditing) await updateProduct(form.id, form);
      else {
        const { id, ...productData } = form;
        await createProduct(productData);
      }
      setForm({ ...emptyProduct });
      setIsEditing(false);
      onSave();
    } catch (err) {
      setError(err.message);
    }
  };

  const handleCancel = () => {
    setForm({ ...emptyProduct });
    setIsEditing(false);
  };



  return (
    <form onSubmit={handleSubmit} className="product-form">
      <h2>{isEditing ? "Editar Producto" : "Nuevo Producto"}</h2>
      {error && <p className="error">{error}</p>}

      <div className="form-grid">
        <div className="form-field">
          <label>Nombre</label>
          <input
            name="name"
            placeholder="Nombre"
            value={form.name}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-field">
          <label>Descripción</label>
          <input
            name="description"
            placeholder="Descripción"
            value={form.description}
            onChange={handleChange}
          />
        </div>

        <div className="form-field">
          <label>Cantidad disponible</label>
          <input
            name="availableQuantity"
            type="number"
            value={form.availableQuantity}
            onChange={handleChange}
          />
        </div>

        <div className="form-field">
          <label>Precio</label>
          <input
            name="price"
            type="number"
            step="0.01"
            value={form.price}
            onChange={handleChange}
          />
        </div>

        <div className="form-field">
          <label>Categoría</label>
          <CategorySelect value={form.categoryId} onChange={handleChange} />
        </div>

      </div>
      <div className="form-buttons">
        <button type="submit" className="save">
          {isEditing ? "Actualizar" : "Guardar"}
        </button>
        {isEditing && (
          <button type="button" onClick={handleCancel} className="cancel">
            Cancelar
          </button>
        )}
      </div>
    </form>

  );

}
