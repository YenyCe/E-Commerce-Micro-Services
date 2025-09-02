import { useEffect, useState } from "react";
import { getProducts, deleteProduct,getProductById } from "../../api/productApi";

export default function ProductList({ onEdit }) {
  const [products, setProducts] = useState([]);
  const [searchId, setSearchId] = useState("");
  const [error, setError] = useState("");

  const loadProducts = () => {
    getProducts()
      .then(setProducts)
      .catch(console.error);
  };

  useEffect(() => {
    loadProducts();
  }, []);

  const handleDelete = async (id) => {
    if (!window.confirm("¿Seguro que quieres eliminar este producto?")) return;
    await deleteProduct(id);
    loadProducts();
  };

  
  const handleSearch = async () => {
    if (!searchId) {
      loadProducts();
      return;
    }
    try {
      const product = await getProductById(searchId);
      setProducts([product]); // mostrar solo el producto encontrado
      setError("");
    } catch (err) {
      setError("Producto no encontrado");
      setProducts([]);
    }
  };

return (
  <div>
    {/* Lista de Productos y búsqueda */}
    <div>
      <h2>Lista de Productos</h2>

      {/*Barra de búsqueda */}
      <div style={{ marginBottom: "1rem" }}>
        <input
          type="number"
          placeholder="Buscar por ID"
          value={searchId}
          onChange={(e) => setSearchId(e.target.value)}
        />
        <button onClick={handleSearch}className="search-btn">Buscar</button>
        <button onClick={loadProducts}className="search-btn">Ver todos</button>
      </div>

      {error && <p style={{ color: "red" }}>{error}</p>}
    </div>

    {/* Tabla de Productos */}
    <table className="product-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Descripción</th>
          <th>Cantidad</th>
          <th>Precio</th>
          <th>Categoría</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        {products.map((p) => (
          <tr key={p.id}>
            <td>{p.id}</td>
            <td>{p.name}</td>
            <td>{p.description}</td>
            <td>{p.availableQuantity}</td>
            <td>${p.price}</td>
            <td>{p.categoryName}</td>
            <td>
              <button className="edit" onClick={() => onEdit(p)}>
                Editar
              </button>
              <button className="delete" onClick={() => handleDelete(p.id)}>
                Eliminar
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
);

}
