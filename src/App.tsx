import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import RegisterPage from "./pages/RegisterPage";
import LoginPage from "./pages/LoginPage";
import CreatePostPage from "./pages/CreatePostPage";
import ExplorePage from "./pages/ExplorePage";
import "./App.css";

const App: React.FC = () => {
  return (
    <Router>
      <nav
        style={{
          padding: "10px",
          background: "#eee",
          display: "flex",
          justifyContent: "center",
          gap: "16px",
        }}
      >
        <Link to="/register">Register</Link>
        <Link to="/login">Login</Link>
        <Link to="/create-post">Create-Post</Link>
        <Link to="/Explore-Page">Explore-Page</Link>
      </nav>

      <Routes>
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/create-post" element={<CreatePostPage />} />
        <Route path="/Explore-Page" element={<ExplorePage />} />
      </Routes>
    </Router>
  );
};

export default App;
