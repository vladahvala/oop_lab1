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
    <div>
      <h2>Patients</h2>

      {patients.map(p => (
        <div key={p.id} style={{ border: "1px solid #ccc", margin: 10, padding: 10 }}>
          <p><b>{p.fullName}</b></p>
          <p>Status: {p.status}</p>

          {p.status !== "DISCHARGED" && (
            <button onClick={() => discharge(p.id)}>
              Discharge
            </button>
          )}
        </div>
      ))}
    </div>
  );
}