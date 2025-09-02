import React from "react";

const CustomerForm = ({ form, handleChange, handleSave, isEditing, handleCancel }) => (
  <form onSubmit={handleSave} className="customer-form">
    <h2>{isEditing ? "Editar Cliente" : "Nuevo Cliente"}</h2>
    <div className="grid-2">
      <input name="id" placeholder="Id" value={form.id} onChange={handleChange} required />
      <input name="firstname" placeholder="Nombre" value={form.firstname} onChange={handleChange} required />
      <input name="lastname" placeholder="Apellido" value={form.lastname} onChange={handleChange} required />
      <input name="email" type="email" placeholder="Email" value={form.email} onChange={handleChange} required />
      <input name="street" placeholder="Calle" value={form.address.street} onChange={handleChange} />
      <input name="houseNumber" placeholder="Número" value={form.address.houseNumber} onChange={handleChange} />
      <input name="zipCode" placeholder="Código Postal" value={form.address.zipCode} onChange={handleChange} />
    </div>
    <div>
      <button type="submit" className="save">{isEditing ? "Actualizar" : "Guardar"}</button>
      {isEditing && <button type="button" onClick={handleCancel} className="cancel">Cancelar</button>}
    </div>
  </form>
);

export default CustomerForm;
