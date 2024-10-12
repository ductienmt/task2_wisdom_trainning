import Http from "../Http";

const Auth = {
  login: async (data) => {
    const res = await Http.post("/api/auth/login", data);
    return res;
  },

  register: async (data) => {
    const res = await Http.post("/api/auth/register", data);
    return res;
  },
};

export default Auth;
