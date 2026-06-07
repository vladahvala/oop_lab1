import { useEffect, useState } from "react";

const BASE_URL = "http://localhost:8080/hospital-system";
const ROLE = "DOCTOR";

export default function DiagnosesPage() {
  const [diagnoses, setDiagnoses] = useState([]);
  const [patients, setPatients] = useState([]);

  const [patientId, setPatientId] = useState("");
  const [description, setDescription] = useState("");
  const [finalDiagnosis, setFinalDiagnosis] = useState(false);

  // GET patients (для вибору)
  async function loadPatients() {
    const res = await fetch(`${BASE_URL}/patients`, {
      headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
    });
    const data = await res.json();
    setPatients(data);
  }

  // GET diagnoses
  async function loadDiagnoses() {
    const res = await fetch(`${BASE_URL}/diagnoses`, {
      headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
    });
    const data = await res.json();
    setDiagnoses(data);
  }

  useEffect(() => {
    loadPatients();
    loadDiagnoses();
  }, []);

  // ADD diagnosis
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
      const msg = await res.text();
      alert(msg);
      return;
    }

    setPatientId("");
    setDescription("");
    setFinalDiagnosis(false);

    loadDiagnoses();
  }

  return (
    <div>
      <h2>Diagnoses</h2>

      {/* FORM */}
      <div style={{ marginBottom: 20 }}>
        <h3>Add Diagnosis</h3>

        {/* patient dropdown */}
        <select
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

        <br />

        <input
          placeholder="Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />

        <br />

        <label>
          <input
            type="checkbox"
            checked={finalDiagnosis}
            onChange={(e) => setFinalDiagnosis(e.target.checked)}
          />
          Final diagnosis
        </label>

        <br />

        <button onClick={addDiagnosis}>Add</button>
      </div>

      <hr />

      {/* LIST */}
      <h3>All Diagnoses</h3>

      {diagnoses.length === 0 ? (
        <p>No diagnoses</p>
      ) : (
        diagnoses.map((d) => (
          <div
            key={d.id}
            style={{
              border: "1px solid gray",
              margin: 10,
              padding: 10
            }}
          >
            <p><b>ID:</b> {d.id}</p>
            <p><b>Patient:</b> {d.patientId}</p>
            <p><b>Description:</b> {d.description}</p>
            <p><b>Final:</b> {d.finalDiagnosis ? "YES" : "NO"}</p>
          </div>
        ))
      )}
    </div>
  );
}