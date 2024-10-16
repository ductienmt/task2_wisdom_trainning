export const CategoryTable = ({
  categories,
  isAdmin,
  handleEdit,
  handleDelete,
  handleGetProductsByCategory,
}) => {
  return (
    <div>
      <table className="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Tên sản phẩm</th>
            <th>Mô tả</th>
            <th>Thao tác</th>
          </tr>
        </thead>
        <tbody>
          {categories.map((category) => (
            <tr key={category.id}>
              <td>{category.id}</td>
              <td>{category.categoryName}</td>
              <td>{category.description}</td>
              <td>
                {isAdmin && (
                  <>
                    <button
                      className="btn btn-primary me-2"
                      onClick={() => handleEdit(category)}
                    >
                      Sửa
                    </button>
                    <button
                      className="btn btn-danger"
                      onClick={() => handleDelete(category.id)}
                    >
                      Xóa
                    </button>
                  </>
                )}
                <button
                  className="btn btn-warning ms-2"
                  onClick={() => handleGetProductsByCategory(category.id)}
                >
                  Xem sản phẩm
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
