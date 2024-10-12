import Http from "../Http";

const ProductService = {
  getAll: async (page) => {
    let url = `/api/products/list`;
    if (page !== undefined && page !== null) {
      url += `?page=${page}`;
    }
    return await Http.get(url);
  },

  getById: async (id) => {
    return await Http.get(`/api/products/${id}`);
  },

  createProduct: async (product) => {
    return await Http.post(`/api/products/save`, product);
  },

  updateProduct: async (productId, product) => {
    return await Http.put(`/api/products/update?id=${productId}`, product);
  },

  deleteProduct: async (productId) => {
    return await Http.delete(`/api/products/delete/${productId}`);
  },

  addProductToCategory: async (categoryId, formData) => {
    return await Http.post(
      `/api/categories/products?id=${categoryId}`,
      formData
    );
  },

  getProductsByCategory: async (categoryId, page) => {
    let url = `/api/categories/products?id=${categoryId}`;
    if (page !== undefined && page !== null) {
      url += `&page=${page}`;
    }
    return await Http.get(url);
  },

  // updateProductCategory: async (productId, oldCategoryId, newCategoryId) => {
  //   return await Http.get(
  //     `/api/products/category?id=${productId}&categoryId=${oldCategoryId}&newCategoryId=${newCategoryId}`
  //   );
  // },
};

export default ProductService;
