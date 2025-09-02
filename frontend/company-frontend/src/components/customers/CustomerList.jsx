import React from "react";

const CustomerList = ({ customers, handleEdit, handleDelete }) => (
  <table className="customer-table">
    <thead>
      <tr>
        <th>Id</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Email</th>
        <th>Dirección</th>
        <th>Acciones</th>
      </tr>
    </thead>
    <tbody>
      {customers.map(c => (
        <tr key={c.id}>
          <td>{c.id}</td>
          <td>{c.firstname}</td>
          <td>{c.lastname}</td>
          <td>{c.email}</td>
          <td>{c.address?.street} {c.address?.houseNumber} {c.address?.zipCode}</td>
          <td>
            <button onClick={() => handleEdit(c)} className="edit">Editar</button>
            <button onClick={() => handleDelete(c.id)} className="delete">Eliminar</button>
          </td>
        </tr>
      ))}
    </tbody>
  </table>
);

export default CustomerList;
