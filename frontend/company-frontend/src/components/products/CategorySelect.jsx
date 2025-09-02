import { useEffect, useState } from "react";
import { getCategories } from "../../api/categoryApi";

export default function CategorySelect({ value, onChange }) {
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    getCategories()
      .then(setCategories)
      .catch(console.error);
  }, []);

  return (
    
    <select name="categoryId" value={value} onChange={onChange}required>
      <option value="">-- Seleccione categoría --</option>
      {categories.map((c) => (
        <option key={c.id} value={c.id}>
          {c.name}
        </option>
      ))}
    </select>
  );
}
