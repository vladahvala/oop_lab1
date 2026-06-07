import { useEffect, useState } from "react";

const BASE_URL = "http://localhost:8080/hospital-system";
const ROLE = "DOCTOR";

export default function DoctorsPage() {
  const [doctors, setDoctors] = useState([]);
  const [name, setName] = useState("");

  async function load() {
    const res = await fetch(`${BASE_URL}/doctors`, {
      headers: { Authorization: `Bearer ${localStorage.getItem("token")}`}
    });
    setDoctors(await res.json());
  }

  useEffect(() => {
    load();
  }, []);

  async function add() {
    await fetch(`${BASE_URL}/doctors`, {
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
      <h2>Doctors</h2>

      <input
        placeholder="Full name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />
      <button onClick={add}>Add</button>

      <hr />

      {doctors.map(d => (
        <div key={d.id}>{d.fullName}</div>
      ))}
    </div>
  );
}