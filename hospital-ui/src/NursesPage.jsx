import { useEffect, useState } from "react";

const BASE_URL = "http://localhost:8080/hospital-system";
const ROLE = "DOCTOR";

export default function NursesPage() {
  const [nurses, setNurses] = useState([]);
  const [name, setName] = useState("");

  async function load() {
    const res = await fetch(`${BASE_URL}/nurses`, {
      headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
    });
    setNurses(await res.json());
  }

  useEffect(() => {
    load();
  }, []);

  async function add() {
    await fetch(`${BASE_URL}/nurses`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
       Authorization: `Bearer ${localStorage.getItem("token")}`
      },
      body: JSON.stringify({ fullName: name })
    });

    setName("");
    load();
  }

  return (
    <div>
      <h2>Nurses</h2>

      <input
        placeholder="Full name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />
      <button onClick={add}>Add</button>

      <hr />

      {nurses.map(n => (
        <div key={n.id}>{n.fullName}</div>
      ))}
    </div>
  );
}