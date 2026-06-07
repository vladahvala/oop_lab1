import { useState } from "react";
import { addPatient } from "./api";

export default function AddPatientPage() {
  const [fullName, setFullName] = useState("");
  const [birthDate, setBirthDate] = useState("");

  async function submit() {
    if (!fullName || !birthDate) return alert("Fill all fields");

    await addPatient({
      fullName,
      birthDate,
      status: "ADMITTED"
    });

    alert("Patient added!");

    setFullName("");
    setBirthDate("");
  }

  return (
    <div style={styles.page}>
      <div style={styles.card}>
        <h2 style={styles.title}>Add Patient</h2>

        <input
          style={styles.input}
          placeholder="Full name"
          value={fullName}
          onChange={e => setFullName(e.target.value)}
        />

        <input
          style={styles.input}
          type="date"
          value={birthDate}
          onChange={e => setBirthDate(e.target.value)}
        />

        <button onClick={submit} style={styles.button}>
          Save
        </button>
      </div>
    </div>
  );
}

const styles = {
  page: {
    minHeight: "100vh",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    background: "#f5f8ff"
  },

  card: {
    width: "350px",
    padding: "25px",
    borderRadius: "12px",
    background: "white",
    boxShadow: "0 8px 20px rgba(0,0,0,0.08)",
    textAlign: "center"
  },

  title: {
    marginBottom: "20px",
    color: "#2b6cb0"
  },

  input: {
    width: "90%",
    padding: "10px",
    marginBottom: "12px",
    borderRadius: "8px",
    border: "1px solid #ccc",
    outline: "none",
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