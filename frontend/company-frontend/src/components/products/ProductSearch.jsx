import { useState } from "react";
import { getProductById } from "../../api/productApi";

export default function ProductSearch() {
  const [id, setId] = useState("");
  const [product, setProduct] = useState(null);
  const [error, setError] = useState("");

  const handleSearch = async () => {
    setError("");
    setProduct(null);
    try {
      const result = await getProductById(id);
      setProduct(result);
    } catch (err) {
      setError("Producto no encontrado");
    }
  };

  return (
    <div className="product-search">
      <h2>Buscar producto</h2>
      <input
        type="number"
        placeholder="ID del producto"
        value={id}
        onChange={(e) => setId(e.target.value)}
      />
      <button onClick={handleSearch}>Buscar</button>

      {error && <p style={{ color: "red" }}>{error}</p>}

      {product && (
        <div className="product-result">
          <h3>{product.name}</h3>
          <p>{product.description}</p>
          <p>Cantidad: {product.availableQuantity}</p>
          <p>Precio: ${product.price}</p>
        </div>
      )}
    </div>
  );
}
