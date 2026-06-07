import { useEffect, useState } from "react";
import { getPatients, dischargePatient } from "./api";

export default function PatientsPage() {
  const [patients, setPatients] = useState([]);

  async function load() {
    const data = await getPatients();
    setPatients(data);
  }

  useEffect(() => {
    load();
  }, []);

  async function discharge(id) {
    await dischargePatient(id);
    load();
  }

  return (
    <div style={styles.page}>
      <h2 style={styles.title}>Patients</h2>

      <div style={styles.grid}>
        {patients.map(p => (
          <div key={p.id} style={styles.card}>
            <p style={styles.name}>{p.fullName}</p>

            <p style={styles.text}>
              <b>Status:</b>{" "}
              <span
                style={{
                  color: p.status === "DISCHARGED" ? "#a0aec0" : "#2b6cb0",
                  fontWeight: "bold"
                }}
              >
                {p.status}
              </span>
            </p>

            {p.status !== "DISCHARGED" && (
              <button
                onClick={() => discharge(p.id)}
                style={styles.button}
              >
                Discharge
              </button>
            )}
          </div>
        ))}
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

  grid: {
    display: "grid",
    gridTemplateColumns: "repeat(auto-fit, minmax(220px, 1fr))",
    gap: "15px"
  },

  card: {
    background: "white",
    borderRadius: "12px",
    padding: "15px",
    boxShadow: "0 4px 12px rgba(0,0,0,0.08)",
    transition: "0.2s"
  },

  name: {
    fontSize: "16px",
    fontWeight: "bold",
    marginBottom: "10px"
  },

  text: {
    fontSize: "14px",
    marginBottom: "10px"
  },

  button: {
    padding: "8px 12px",
    background: "#e53e3e",
    color: "white",
    border: "none",
    borderRadius: "8px",
    cursor: "pointer",
    fontSize: "13px"
  }
};