import { useState } from "react";
import ProductForm from "../components/products/ProductForm";
import ProductList from "../components/products/ProductList";

export default function ProductsPage() {
  const [productToEdit, setProductToEdit] = useState(null);
  const [reload, setReload] = useState(false);

  const handleSave = () => {
    setProductToEdit(null);
    setReload(!reload);
  };

  return (
    <div className="container">
      <ProductForm productToEdit={productToEdit} onSave={handleSave} />
      <ProductList key={reload} onEdit={setProductToEdit} />
    </div>
  );
}
