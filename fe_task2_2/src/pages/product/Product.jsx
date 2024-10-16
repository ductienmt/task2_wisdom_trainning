import { useEffect, useState } from "react";
import ProductService from "../../api/apis/Product";
import { Link, useLocation } from "react-router-dom";
import { useSnackbar } from "notistack";
import { ProductTable } from "../../components/product/ProductTable";

const Product = () => {
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const idCategory = searchParams.get("idCategory");
  const { enqueueSnackbar } = useSnackbar();
  const [products, setProducts] = useState([]);
  const [isAdmin, setIsAdmin] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const [formData, setFormData] = useState({
    id: "",
    productName: "",
    price: "",
    description: "",
  });

  const loadProducts = async () => {
    try {
      let response;

      if (idCategory && idCategory.length > 0) {
        response = await ProductService.getProductsByCategory(idCategory);
      } else {
        response = await ProductService.getAll();
      }

      console.log(response.data);
      setProducts(response.data.message.content);
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

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.id]: e.target.value,
    });
  };

  const handleEdit = (product) => {
    setFormData(product);
    setIsEditing(true);
  };

  const handleReset = () => {
    setFormData({
      id: "",
      productName: "",
      price: "",
      description: "",
    });
    setIsEditing(false);
  };

  const handleSubmit = async () => {
    try {
      const res = await ProductService.createProduct(formData);
      loadProducts();
      handleReset();
      enqueueSnackbar(res.data.status, { variant: "success" });
    } catch (error) {
      enqueueSnackbar(error, { variant: "error" });
      console.error(error);
    }
  };

  const handleUpdate = async () => {
    try {
      const res = await ProductService.updateProduct(formData.id, formData);
      loadProducts();
      handleReset();
      alert(res.data.status);
    } catch (error) {
      console.error(error);
    }
  };

  const handleDelete = async (productId) => {
    try {
      const res = await ProductService.deleteProduct(productId);
      loadProducts();
      enqueueSnackbar(res.data.status, { variant: "success" });
    } catch (error) {
      enqueueSnackbar(error, { variant: "error" });
      console.error(error);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("accessToken");
    localStorage.removeItem("username");
    localStorage.removeItem("role");
    window.location.href = "/login";
  };

  useEffect(() => {
    const userRole = localStorage.getItem("role");
    if (userRole === "admin") {
      setIsAdmin(true);
    } else {
      setIsAdmin(false);
    }
    loadProducts();
  }, []);

  return (
    <>
      <div className="container my-4">
        {isAdmin && (
          <div className="form-product">
            <h1>Products</h1>
            <form className="mb-4">
              <div className="form-group">
                <label htmlFor="productName">Product Name</label>
                <input
                  type="text"
                  className="form-control"
                  id="productName"
                  value={formData.productName}
                  onChange={handleChange}
                />
              </div>
              <div className="form-group">
                <label htmlFor="price">Price</label>
                <input
                  type="number"
                  className="form-control"
                  id="price"
                  value={formData.price}
                  onChange={handleChange}
                />
              </div>
              <div className="form-group">
                <label htmlFor="description">Description</label>
                <textarea
                  className="form-control"
                  id="description"
                  value={formData.description}
                  onChange={handleChange}
                ></textarea>
              </div>
              <button
                type="button"
                className={`btn btn-primary me-2 mt-2 ${
                  isEditing ? "d-none" : "d-inline-block"
                }`}
                onClick={handleSubmit}
              >
                Thêm
              </button>
              <button
                type="button"
                className={`btn btn-secondary me-2 mt-2 ${
                  isEditing ? "d-inline-block" : "d-none"
                }`}
                onClick={handleUpdate}
              >
                Cập nhật
              </button>
              <button
                type="button"
                className="btn btn-warning mt-2"
                onClick={handleReset}
              >
                Làm mới
              </button>
            </form>
          </div>
        )}
        <ProductTable
          products={products}
          isAdmin={isAdmin}
          handleEdit={handleEdit}
          handleDelete={handleDelete}
        />
        <div className="d-flex justify-content-between">
          <Link className="btn btn-primary" to={"/categories"}>
            Quay về
          </Link>
        </div>
      </div>
    </>
  );
};

export default Product;
