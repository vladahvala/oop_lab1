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
    <div>
      <h2>Add Patient</h2>

      <input
        placeholder="Full name"
        value={fullName}
        onChange={e => setFullName(e.target.value)}
      />

      <br />

      <input
        type="date"
        value={birthDate}
        onChange={e => setBirthDate(e.target.value)}
      />

      <br />

      <button onClick={submit}>Save</button>
    </div>
  );
}