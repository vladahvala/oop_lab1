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

    // 🔐 save token
    localStorage.setItem("token", data.token);

    // 🔐 decode JWT payload (role)
    const payload = JSON.parse(atob(data.token.split(".")[1]));
    localStorage.setItem("role", payload.role);

    console.log("LOGIN OK:", payload.role);

    onLogin();
  }

  return (
    <div>
      <h2>Login</h2>

      <select value={role} onChange={(e) => setRole(e.target.value)}>
        <option value="DOCTOR">DOCTOR</option>
        <option value="NURSE">NURSE</option>
      </select>

      <br /><br />

      <button onClick={login}>Login</button>
    </div>
  );
}