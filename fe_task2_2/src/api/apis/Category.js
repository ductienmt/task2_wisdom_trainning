import Http from "../Http";

const CategoryService = {
  getAll: async () => {
    return await Http.get("/api/categories/list");
  },

  getById: async (categoryId) => {
    return await Http.get(`/api/categories/list?id=${categoryId}`);
  },

  save: async (formData) => {
    return await Http.post("/api/categories/save", formData);
  },

  update: async (categoryId, formData) => {
    return await Http.put(`/api/categories/update?id=${categoryId}`, formData);
  },

  deleteById: async (categoryId) => {
    return await Http.delete(`/api/categories/delete?id=${categoryId}`);
  },

  
};

export default CategoryService;
