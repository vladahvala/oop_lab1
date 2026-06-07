import { useState } from "react";

const BASE_URL = "http://localhost:8080/hospital-system";

export default function LoginPage({ onLogin }) {
  const [role, setRole] = useState("DOCTOR");

  async function login() {
    const res = await fetch(`${BASE_URL}/auth/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ role })
    });

    const data = await res.json();

    localStorage.setItem("token", data.token);

    const payload = JSON.parse(atob(data.token.split(".")[1]));
    localStorage.setItem("role", payload.role);

    console.log("LOGIN OK:", payload.role);

    onLogin();
  }

  return (
    <div style={styles.page}>
      <div style={styles.card}>
        <h2 style={styles.title}>🏥 Hospital System</h2>
        <p style={styles.subtitle}>Login to continue</p>

        <select
          value={role}
          onChange={(e) => setRole(e.target.value)}
          style={styles.select}
        >
          <option value="DOCTOR">DOCTOR</option>
          <option value="NURSE">NURSE</option>
        </select>

        <button onClick={login} style={styles.button}>
          Login
        </button>
      </div>
    </div>
  );
}

const styles = {
  page: {
    height: "100vh",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    background: "linear-gradient(135deg, #e0f2ff, #f8fbff)"
  },

  card: {
    width: "320px",
    padding: "30px",
    borderRadius: "12px",
    backgroundColor: "white",
    boxShadow: "0 10px 30px rgba(0,0,0,0.1)",
    textAlign: "center"
  },

  title: {
    marginBottom: "5px",
    color: "#2b6cb0"
  },

  subtitle: {
    marginBottom: "20px",
    color: "#666",
    fontSize: "14px"
  },

  select: {
    width: "100%",
    padding: "10px",
    marginBottom: "15px",
    borderRadius: "8px",
    border: "1px solid #ccc",
    fontSize: "14px"
  },

  button: {
    width: "100%",
    padding: "10px",
    backgroundColor: "#2b6cb0",
    color: "white",
    border: "none",
    borderRadius: "8px",
    cursor: "pointer",
    fontWeight: "bold"
  }
};