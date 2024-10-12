import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Link, useNavigate } from "react-router-dom";
import CategoryService from "../../api/apis/Category";
import { useSnackbar } from "notistack";

const Category = () => {
  const { enqueueSnackbar } = useSnackbar();
  const [categories, setCategories] = useState([]);
  const [isAdmin, setIsAdmin] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    id: "",
    categoryName: "",
    description: "",
  });

  const loadCategories = async () => {
    try {
      const response = await CategoryService.getAll();
      console.log(response.data);
      setCategories(response.data.message);
    } catch (error) {
      if (error.response && error.response.status === 401) {
        enqueueSnackbar("Phiên đăng nhập đã hết hạn!", {
          variant: "error",
          autoHideDuration: 2000,
          onExited: () => {
            localStorage.removeItem("accessToken");
            window.location.href = "/";
          },
        });
      } else {
        console.error(error);
      }
    }
  };

  useEffect(() => {
    const userRole = localStorage.getItem("role");
    if (userRole === "admin") {
      setIsAdmin(true);
    } else {
      setIsAdmin(false);
    }

    loadCategories();
  }, []);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.id]: e.target.value,
    });
  };

  const handleEdit = (category) => {
    setFormData(category);
    setIsEditing(true);
  };

  const handleReset = () => {
    setFormData({
      id: "",
      categoryName: "",
      description: "",
    });
    setIsEditing(false);
  };

  const handleSubmit = async () => {
    try {
      const res = await CategoryService.save(formData);
      loadCategories();
      handleReset();
      alert(res.data.status);
    } catch (error) {
      console.error(error);
    }
  };

  const handleUpdate = async () => {
    try {
      const res = await CategoryService.update(formData.id, formData);
      loadCategories();
      handleReset();
      alert(res.data.status);
    } catch (error) {
      console.error(error);
    }
  };

  const handleDelete = async (id) => {
    try {
      const res = await CategoryService.deleteById(id);
      loadCategories();
      handleReset();
      alert(res.data.status);
    } catch (error) {
      console.error(error);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("accessToken");
    localStorage.removeItem("username");
    localStorage.removeItem("role");
    window.location.href = "/";
  };

  const handleGetProductsByCategory = async (categoryId) => {
    navigate(`/products?idCategory=${categoryId}`);
  };

  return (
    <div className="container my-4">
      <h2 className="mb-4">Categories</h2>

      {isAdmin && (
        <div className="form-category">
          <form className="mb-4">
            <div className="mb-3">
              <label htmlFor="categoryName" className="form-label">
                Tên danh mục
              </label>
              <input
                type="text"
                className="form-control"
                id="categoryName"
                placeholder="Nhập tên danh mục"
                value={formData.categoryName}
                onChange={handleChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="description" className="form-label">
                Mô tả
              </label>
              <textarea
                className="form-control"
                id="description"
                value={formData.description}
                onChange={handleChange}
              ></textarea>
            </div>
            <button
              type="button"
              className={`btn btn-primary me-2 ${
                isEditing ? "d-none" : "d-inline-block"
              }`}
              onClick={handleSubmit}
            >
              Thêm
            </button>
            <button
              type="button"
              className={`btn btn-secondary me-2 ${
                isEditing ? "d-inline-block" : "d-none"
              }`}
              onClick={handleUpdate}
            >
              Cập nhật
            </button>
            <button
              type="button"
              className="btn btn-warning"
              onClick={handleReset}
            >
              Làm mới
            </button>
          </form>
        </div>
      )}

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
                    className="btn btn-warning"
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
      <div className="d-flex justify-content-between">
        <button className="btn btn-success" onClick={() => handleLogout()}>
          Logout
        </button>
        <Link className="btn btn-primary" to={"/products"}>
          Xem sản phẩm
        </Link>
      </div>
    </div>
  );
};

export default Category;
