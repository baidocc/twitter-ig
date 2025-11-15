import React, { useState } from "react";
import axios from "axios";

function App() {
  const [message, setMessage] = useState<string>("(niciun mesaj inca)");

  const callBackend = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/hello");
      setMessage(response.data);
    } catch (error) {
      console.error(error);
      setMessage("Eroare la apelul catre backend");
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h1>Mini Twitter - test conexiune</h1>
      <button onClick={callBackend}>Cheama backendul</button>
      <p>Raspuns backend: {message}</p>
    </div>
  );
}

export default App;
