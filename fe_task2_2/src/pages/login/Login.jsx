import { useEffect, useState } from "react";
import "./Login.css";
import Auth from "../../api/apis/Auth";
import { useSnackbar } from "notistack";
import { handleToken } from "../../shared/constants/HandleToken";

const Login = () => {
  const { enqueueSnackbar } = useSnackbar();
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

  const handleReset = () => {
    setFormLogin({
      username: "",
      password: "",
    });
  };

  useEffect(() => {
    if (handleToken.isTokenExpired()) {
      localStorage.removeItem("accessToken");
      localStorage.removeItem("username");
      localStorage.removeItem("role");
      localStorage.removeItem("tokenExpiry");
    }
  }, []);

  const handleLogin = async () => {
    try {
      const response = await Auth.login(formLogin);
      console.log(response.data);
      console.log(response.data.message.role[0].authority);
      handleToken.saveToken(
        response.data.message.token,
        response.data.message.username,
        response.data.message.role[0].authority
      );
      handleReset();
      enqueueSnackbar("Đăng nhập thành công!", {
        variant: "success",
        autoHideDuration: 1000,
        onExited: () => {
          window.location.href = "/categories";
        },
      });
    } catch (error) {
      enqueueSnackbar(error.response.data.status, { variant: "error" });
      handleReset();
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
                style={{ borderRadius: "1rem" }}
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
