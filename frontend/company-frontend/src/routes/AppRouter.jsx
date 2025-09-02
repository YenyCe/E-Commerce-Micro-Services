import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import CustomersPage from "../pages/CustomersPage";
import ProductsPage from "../pages/ProductsPage"; // cuando lo agregues

const AppRouter = () => (
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<Navigate to="/customers" />} />
      <Route path="/customers/*" element={<CustomersPage />} />
      { <Route path="/products/*" element={<ProductsPage />} /> }
    </Routes>
  </BrowserRouter>
);

export default AppRouter;
