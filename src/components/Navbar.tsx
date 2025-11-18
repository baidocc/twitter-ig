// src/components/Navbar.tsx
import React from "react";
import { Link, useNavigate, useLocation } from "react-router-dom";
import LogoutButton from "./LogoutButton";
import "../design/Navbar.css";

const Navbar: React.FC = () => {
  const navigate = useNavigate();
  const location = useLocation();

  

  const isActive = (path: string) => location.pathname === path;

  return (
    <header className="navbar">
      {/* stanga – deocamdata gol, il putem folosi mai tarziu pt logo etc. */}
      <div className="navbar-left" />

      {/* mijloc – buton mare Explore Page */}
      <div className="navbar-center">
        <Link
          to="/"
          className={`nav-button nav-main ${isActive("/") ||  isActive("/Explore-Page") ? "active" : ""}`}
        >
          Explore Page
        </Link>

        <Link
          to="/create-post"
          className={`nav-button nav-main ${isActive("/") ||  isActive("/Explore-Page") ? "active" : ""}`}
        >
          Create Post
        </Link>
      </div>

      {/* dreapta – register, login, logout */}
      <div className="navbar-right">
        <Link
          to="/register"
          className={`nav-button ${isActive("/register") ? "active" : ""}`}
        >
          Register
        </Link>

        <Link
          to="/login"
          className={`nav-button ${isActive("/login") ? "active" : ""}`}
        >
          Login
        </Link>

        <LogoutButton className="nav-button" />
      </div>
    </header>
  );
};

export default Navbar;
