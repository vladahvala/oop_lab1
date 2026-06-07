import { useEffect, useState } from "react";

const BASE_URL = "http://localhost:8080/hospital-system";

export default function DiagnosesPage() {
  const [diagnoses, setDiagnoses] = useState([]);
  const [patients, setPatients] = useState([]);

  const [patientId, setPatientId] = useState("");
  const [description, setDescription] = useState("");
  const [finalDiagnosis, setFinalDiagnosis] = useState(false);

  async function loadPatients() {
    const res = await fetch(`${BASE_URL}/patients`, {
      headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
    });
    setPatients(await res.json());
  }

  async function loadDiagnoses() {
    const res = await fetch(`${BASE_URL}/diagnoses`, {
      headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
    });
    setDiagnoses(await res.json());
  }

  useEffect(() => {
    loadPatients();
    loadDiagnoses();
  }, []);

  async function addDiagnosis() {
    if (!patientId || !description) {
      alert("Fill all fields");
      return;
    }

    const res = await fetch(`${BASE_URL}/diagnoses`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem("token")}`
      },
      body: JSON.stringify({
        patientId: Number(patientId),
        doctorId: 1,
        description,
        finalDiagnosis
      })
    });

    if (!res.ok) {
      alert(await res.text());
      return;
    }

    setPatientId("");
    setDescription("");
    setFinalDiagnosis(false);

    loadDiagnoses();
  }

  return (
    <div style={styles.page}>
      <h2 style={styles.title}>Diagnoses</h2>

      {/* FORM */}
      <div style={styles.card}>
        <h3 style={styles.subtitle}>Add Diagnosis</h3>

        <select
          style={styles.input}
          value={patientId}
          onChange={(e) => setPatientId(e.target.value)}
        >
          <option value="">Select patient</option>
          {patients.map((p) => (
            <option key={p.id} value={p.id}>
              {p.fullName} ({p.status})
            </option>
          ))}
        </select>

        <input
          style={styles.input}
          placeholder="Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />

        <label style={styles.checkboxRow}>
          <input
            type="checkbox"
            checked={finalDiagnosis}
            onChange={(e) => setFinalDiagnosis(e.target.checked)}
          />
          Final diagnosis
        </label>

        <button onClick={addDiagnosis} style={styles.button}>
          Add
        </button>
      </div>

      {/* LIST */}
      <h3 style={styles.subtitle}>All Diagnoses</h3>

      <div style={styles.grid}>
        {diagnoses.length === 0 ? (
          <p>No diagnoses</p>
        ) : (
          diagnoses.map((d) => (
            <div key={d.id} style={styles.cardSmall}>
              <p><b>ID:</b> {d.id}</p>
              <p><b>Patient:</b> {d.patientId}</p>
              <p><b>Description:</b> {d.description}</p>

              <p>
                <b>Final:</b>{" "}
                <span style={{ color: d.finalDiagnosis ? "#2f855a" : "#c05621" }}>
                  {d.finalDiagnosis ? "YES" : "NO"}
                </span>
              </p>
            </div>
          ))
        )}
      </div>
    </div>
  );
}

const styles = {
  page: {
    padding: "20px",
    background: "#f5f8ff",
    minHeight: "100vh"
  },

  title: {
    color: "#2b6cb0",
    marginBottom: "20px"
  },

  subtitle: {
    marginTop: "20px",
    marginBottom: "10px",
    color: "#2d3748"
  },

  card: {
    background: "white",
    padding: "15px",
    borderRadius: "12px",
    boxShadow: "0 4px 12px rgba(0,0,0,0.08)",
    maxWidth: "420px"
  },

  cardSmall: {
    background: "white",
    padding: "12px",
    borderRadius: "10px",
    boxShadow: "0 3px 10px rgba(0,0,0,0.06)"
  },

  grid: {
    display: "grid",
    gridTemplateColumns: "repeat(auto-fit, minmax(220px, 1fr))",
    gap: "12px"
  },

  input: {
    width: "90%",
    padding: "10px",
    marginBottom: "10px",
    borderRadius: "8px",
    border: "1px solid #ccc"
  },

  button: {
    width: "100%",
    padding: "10px",
    background: "#2b6cb0",
    color: "white",
    border: "none",
    borderRadius: "8px",
    cursor: "pointer"
  },

  checkboxRow: {
    display: "flex",
    gap: "8px",
    alignItems: "center",
    marginBottom: "10px"
  }
};