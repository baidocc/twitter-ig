import React, { useState } from "react";
import { api } from "../api";

const RegisterPage: React.FC = () => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");

  const handleRegister = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await api.post("/auth/register", {
        username,
        email,
        password,
      });
      setMessage(response.data);
    } catch (err: any) {
      setMessage(
        err.response?.data
          ? JSON.stringify(err.response.data)
          : "Eroare la inregistrare"
      );
    }
  };

  return (
    <div
      style={{
        display: "flex",
        minHeight: "100vh",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      <form
        onSubmit={handleRegister}
        style={{
          width: "320px",
          display: "flex",
          flexDirection: "column",
          gap: "10px",
        }}
      >
        <h2>Inregistrare</h2>

        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />

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

        <button type="submit">Creeaza cont</button>

        {message && (
          <p style={{ color: "green", wordBreak: "break-word" }}>{message}</p>
        )}
      </form>
    </div>
  );
};

export default RegisterPage;
