import React, { useState } from "react";
import { api } from "../api";
import "../design/LoginPage.css";// <-- 1. IMPORTĂ FIȘIERUL CSS

const LoginPage: React.FC = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await api.post("/auth/login", {
        email,
        password,
      });
      setMessage(response.data);
    } catch (err: any) {
      setMessage(
        err.response?.data
          ? JSON.stringify(err.response.data)
          : "Eroare la autentificare"
      );
    }
  };

  return (
    // 4. Aplică clasele CSS și elimină stilurile inline
    <div className="login-container">
      <form onSubmit={handleLogin} className="login-form">
        <h2>Autentificare</h2>

        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />

        <input
          type="password"
          placeholder="Parola"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />

        <button type="submit">Login</button>

        {/* 5. Afișează mesajele stilizate */}
        {message && (
          <p className="form-message success">{message}</p>
        )}
        {message && (
          <p className="form-message error">{message}</p>
        )}
      </form>
    </div>
  );
};

export default LoginPage;
