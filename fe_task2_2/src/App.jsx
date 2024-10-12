import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import Login from "./pages/login/Login";
import Category from "./pages/category/Category";
import Product from "./pages/product/Product";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />}></Route>
        <Route path="/categories" element={<Category />}></Route>
        <Route path="/products" element={<Product />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
