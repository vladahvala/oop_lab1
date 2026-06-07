import { useEffect, useState } from "react";

const BASE_URL = "http://localhost:8080/hospital-system";
const ROLE = "DOCTOR";

export default function TreatmentsPage() {
  const [treatments, setTreatments] = useState([]);

  const [diagnosisId, setDiagnosisId] = useState("");
  const [type, setType] = useState("");
  const [description, setDescription] = useState("");

  async function loadTreatments() {
    try {
      const res = await fetch(`${BASE_URL}/treatments`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`
        }
      });

      const data = await res.json();
      setTreatments(data);
    } catch (err) {
      console.error("Error loading treatments:", err);
    }
  }

  useEffect(() => {
    loadTreatments();
  }, []);

  async function addTreatment() {
    try {
      const res = await fetch(`${BASE_URL}/treatments`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("token")}`
        },
        body: JSON.stringify({
          diagnosisId: Number(diagnosisId),
          type,
          description,
          status: "PENDING",
          assignedByRole: ROLE
        })
      });

      if (!res.ok) {
        alert(await res.text());
        return;
      }

      setDiagnosisId("");
      setType("");
      setDescription("");

      loadTreatments();
    } catch (err) {
      console.error("Error adding treatment:", err);
    }
  }

  return (
    <div style={styles.page}>
      <h2 style={styles.title}>Treatments</h2>

      {/* FORM */}
      <div style={styles.card}>
        <h3 style={styles.subtitle}>Add Treatment</h3>

        <input
          style={styles.input}
          placeholder="Diagnosis ID"
          value={diagnosisId}
          onChange={(e) => setDiagnosisId(e.target.value)}
        />

        <input
          style={styles.input}
          placeholder="Type (medicine/procedure/etc)"
          value={type}
          onChange={(e) => setType(e.target.value)}
        />

        <input
          style={styles.input}
          placeholder="Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />

        <button onClick={addTreatment} style={styles.button}>
          Add Treatment
        </button>
      </div>

      {/* LIST */}
      <h3 style={styles.subtitle}>All Treatments</h3>

      <div style={styles.grid}>
        {treatments.length === 0 ? (
          <p>No treatments</p>
        ) : (
          treatments.map((t) => (
            <div key={t.id} style={styles.cardSmall}>
              <p><b>ID:</b> {t.id}</p>
              <p><b>Diagnosis:</b> {t.diagnosisId}</p>
              <p><b>Type:</b> {t.type}</p>
              <p><b>Description:</b> {t.description}</p>
              <p>
                <b>Status:</b>{" "}
                <span style={{ color: t.status === "PENDING" ? "#d69e2e" : "#2f855a" }}>
                  {t.status}
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
  }
};