import React, { useState } from "react";
import { api } from "../api"; // acelasi api folosit si la register/login

const CreatePostPage: React.FC = () => {
  const [title, setTitle] = useState("");
  const [body, setBody] = useState("");
  const [message, setMessage] = useState("");

  // temporar, userId hardcodat (pana ai login)
  const userId = 1;

  const handleAddPost = async (e: React.FormEvent) => {
    e.preventDefault();
    setMessage("");

    try {
      await api.post("/post/create", { title, body, userId });
      setMessage("Post adaugat cu succes!");
      setTitle("");
      setBody("");
    } catch (err: any) {
      console.error("Eroare la adaugare post:", err);
      setMessage(
        err.response?.data
          ? JSON.stringify(err.response.data)
          : "Eroare la adaugarea postului"
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
        onSubmit={handleAddPost}
        style={{
          width: "400px",
          display: "flex",
          flexDirection: "column",
          gap: "10px",
        }}
      >
        <h2>Adauga un post nou</h2>

        <input
          type="text"
          placeholder="Titlu"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
        />

        <textarea
          placeholder="Continutul postarii..."
          value={body}
          onChange={(e) => setBody(e.target.value)}
          rows={5}
          required
        ></textarea>

        <button type="submit">Publica postarea</button>

        {message && (
          <p style={{ color: "green", wordBreak: "break-word" }}>{message}</p>
        )}
      </form>
    </div>
  );
};

export default CreatePostPage;
