import { useEffect, useState } from "react";
import "./Login.css";
import axiosInstance from "../api/axiosInstance";

const Login = () => {
  const [formLogin, setFormLogin] = useState({
    username: "",
    password: "",
  });

  const handleChange = (e) => {
    setFormLogin({
      ...formLogin,
      [e.target.id]: e.target.value,
    });
  };

  const handleLogin = async () => {
    try {
      const response = await axiosInstance.post("/api/auth/login", formLogin);
      console.log(response.data);
      localStorage.setItem("accessToken", response.data.message.token);
      localStorage.setItem("username", response.data.message.username);
    } catch (error) {
      console.error(error.response.data.status);
    }
  };

  return (
    <>
      <section className="vh-100 gradient-custom">
        <div className="container py-5 h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-12 col-md-8 col-lg-6 col-xl-5">
              <div
                className="card bg-dark text-white"
                style={{ borderRadius: "1rem" }} // Sử dụng đối tượng cho style
              >
                <div className="card-body p-5 text-center">
                  <div className="mb-md-5 mt-md-4 pb-5">
                    <h2 className="fw-bold mb-2 text-uppercase">Login</h2>
                    <p className="text-white-50 mb-5">
                      Please enter your login and password!
                    </p>

                    <div
                      data-mdb-input-init
                      className="form-outline form-white mb-4"
                    >
                      <label className="form-label" htmlFor="typeEmailX">
                        Username
                      </label>
                      <input
                        type="email"
                        id="username"
                        className="form-control form-control-lg"
                        onChange={handleChange}
                        value={formLogin.username}
                      />
                    </div>

                    <div
                      data-mdb-input-init
                      className="form-outline form-white mb-4"
                    >
                      <label className="form-label" htmlFor="typePasswordX">
                        Password
                      </label>
                      <input
                        type="password"
                        id="password"
                        className="form-control form-control-lg"
                        onChange={handleChange}
                        value={formLogin.password}
                      />
                    </div>

                    <p className="small mb-5 pb-lg-2">
                      <a className="text-white-50" href="#!">
                        Forgot password?
                      </a>
                    </p>

                    <button
                      data-mdb-button-init
                      data-mdb-ripple-init
                      className="btn btn-outline-light btn-lg px-5"
                      type="button"
                      onClick={handleLogin}
                    >
                      Login
                    </button>

                    <div className="d-flex justify-content-center text-center mt-4 pt-1">
                      <a href="#!" className="text-white">
                        <i className="fab fa-facebook-f fa-lg"></i>
                      </a>
                      <a href="#!" className="text-white">
                        <i className="fab fa-twitter fa-lg mx-4 px-2"></i>
                      </a>
                      <a href="#!" className="text-white">
                        <i className="fab fa-google fa-lg"></i>
                      </a>
                    </div>
                  </div>

                  <div>
                    <p className="mb-0">
                      Don't have an account?{" "}
                      <a href="#!" className="text-white-50 fw-bold">
                        Sign Up
                      </a>
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
};

export default Login;
