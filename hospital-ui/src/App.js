import { useState } from "react";
import PatientsPage from "./PatientsPage";
import AddPatientPage from "./AddPatientPage";
import TreatmentsPage from "./TreatmentsPage";
import DiagnosesPage from "./DiagnosesPage";
import DoctorsPage from "./DoctorsPage";
import NursesPage from "./NursesPage";

export default function App() {
  const [page, setPage] = useState("patients");

  return (
    <div>
      <h2>🏥 Hospital System</h2>

      <button onClick={() => setPage("patients")}>Patients</button>
      <button onClick={() => setPage("add")}>Add Patient</button>
      <button onClick={() => setPage("treatments")}>Treatments</button>
      <button onClick={() => setPage("diagnoses")}>Diagnoses</button>
      <button onClick={() => setPage("doctors")}>Doctors</button>
      <button onClick={() => setPage("nurses")}>Nurses</button>

      <hr />

      {page === "patients" && <PatientsPage />}
      {page === "add" && <AddPatientPage />}
      {page === "diagnoses" && <DiagnosesPage />}
      {page === "treatments" && <TreatmentsPage />}
      {page === "doctors" && <DoctorsPage />}
      {page === "nurses" && <NursesPage />}
    </div>
  );
}