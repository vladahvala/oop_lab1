import { useEffect, useState } from "react";

const BASE_URL = "http://localhost:8080/hospital-system";
const ROLE = "DOCTOR";

export default function TreatmentsPage() {
  const [treatments, setTreatments] = useState([]);

  const [diagnosisId, setDiagnosisId] = useState("");
  const [type, setType] = useState("");
  const [description, setDescription] = useState("");

  // GET all treatments
  async function loadTreatments() {
    try {
      const res = await fetch(`${BASE_URL}/treatments`, {
        headers: {
          Authorization: ROLE
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

  // ADD treatment
  async function addTreatment() {
    try {
      const res = await fetch(`${BASE_URL}/treatments`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: ROLE
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
        const msg = await res.text();
        alert(msg);
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
    <div>
      <h2>Treatments</h2>

      {/* FORM */}
      <div style={{ marginBottom: 20 }}>
        <h3>Add Treatment</h3>

        <input
          placeholder="Diagnosis ID"
          value={diagnosisId}
          onChange={(e) => setDiagnosisId(e.target.value)}
        />
        <br />

        <input
          placeholder="Type (medicine/procedure/etc)"
          value={type}
          onChange={(e) => setType(e.target.value)}
        />
        <br />

        <input
          placeholder="Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
        <br />

        <button onClick={addTreatment}>Add</button>
      </div>

      <hr />

      {/* LIST */}
      <h3>All Treatments</h3>

      {treatments.length === 0 ? (
        <p>No treatments</p>
      ) : (
        treatments.map((t) => (
          <div
            key={t.id}
            style={{
              border: "1px solid gray",
              margin: 10,
              padding: 10
            }}
          >
            <p><b>ID:</b> {t.id}</p>
            <p><b>Diagnosis:</b> {t.diagnosisId}</p>
            <p><b>Type:</b> {t.type}</p>
            <p><b>Description:</b> {t.description}</p>
            <p><b>Status:</b> {t.status}</p>
          </div>
        ))
      )}
    </div>
  );
}