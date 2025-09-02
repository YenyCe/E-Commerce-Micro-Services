import { useState } from "react";
import ProductForm from "../components/products/ProductForm";
import ProductList from "../components/products/ProductList";
import CategoryCrudModal from "../components/categories/CategoryCrudModal.jsx";


export default function ProductsPage() {
  const [productToEdit, setProductToEdit] = useState(null);
  const [reload, setReload] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const [isCategoryModalOpen, setIsCategoryModalOpen] = useState(false);

  const handleSave = () => {
    setProductToEdit(null);
    setReload(!reload);
    setShowModal(false); // cerrar modal al guardar
  };
  return (
    <div className="container">
      <h1>Gestión de Productos</h1>
      <div className="top-buttons">
        <button onClick={() => { setProductToEdit(null); setShowModal(true); }} className="search-btn category-btn">
          Nuevo Producto
        </button>

        <button onClick={() => setIsCategoryModalOpen(true)} className="search-btn category-btn">
          Administrar Categorías
        </button>
      </div>

      <ProductList
        key={reload}
        onEdit={(p) => { setProductToEdit(p); setShowModal(true); }}
      />
        <CategoryCrudModal
          isOpen={isCategoryModalOpen}
          onClose={() => setIsCategoryModalOpen(false)}
        />
      {showModal && (
        <div className="modal-overlay">
          <div className="modal">
            <button className="close" onClick={() => setShowModal(false)}>X</button>
            <ProductForm productToEdit={productToEdit} onSave={handleSave} />
          </div>
        </div>
      )}
    </div>
  );
}
