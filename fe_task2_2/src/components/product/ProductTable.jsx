import { formatCurrency } from "../../shared/constants/FormatMoney";

export const ProductTable = ({
  // eslint-disable-next-line react/prop-types
  products,
  // eslint-disable-next-line react/prop-types
  isAdmin,
  // eslint-disable-next-line react/prop-types
  handleEdit,
  // eslint-disable-next-line react/prop-types
  handleDelete,
}) => {
  return (
    <table className="table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Product Name</th>
          <th>Price</th>
          <th>Description</th>
          {isAdmin && <th>Actions</th>}
        </tr>
      </thead>
      <tbody>
        {/* eslint-disable-next-line react/prop-types */}
        {products.map((product) => (
          <tr key={product.id}>
            <td>{product.id}</td>
            <td>{product.productName}</td>
            <td>{formatCurrency(product.price)}</td>
            <td>{product.description}</td>
            {isAdmin && (
              <td>
                <button
                  type="button"
                  className="btn btn-info me-2"
                  onClick={() => handleEdit(product)}
                >
                  Edit
                </button>
                <button
                  type="button"
                  className="btn btn-danger"
                  onClick={() => handleDelete(product.id)}
                >
                  Delete
                </button>
              </td>
            )}
          </tr>
        ))}
      </tbody>
    </table>
  );
};
