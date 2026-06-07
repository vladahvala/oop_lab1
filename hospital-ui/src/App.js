import { useState } from "react";
import LoginPage from "./LoginPage";
import PatientsPage from "./PatientsPage";
import AddPatientPage from "./AddPatientPage";
import TreatmentsPage from "./TreatmentsPage";
import DiagnosesPage from "./DiagnosesPage";
import DoctorsPage from "./DoctorsPage";
import NursesPage from "./NursesPage";

export default function App() {
  const [page, setPage] = useState("login");

  // 🔥 важливо: role тепер state (реактивний)
  const [role, setRole] = useState(localStorage.getItem("role"));

  // 🔐 після логіну
  const handleLogin = () => {
    const r = localStorage.getItem("role");

    if (!r) {
      alert("Login failed");
      return;
    }

    setRole(r);
    setPage("patients"); // ❗ НЕ dashboard
  };

  // 🚪 logout
  const logout = () => {
    localStorage.clear();
    setRole(null);
    setPage("login");
  };

  if (page === "login") {
    return <LoginPage onLogin={handleLogin} />;
  }

  return (
    <div>
      <h2>🏥 Hospital System</h2>
      <h4>Role: {role}</h4>

      {/* DOCTOR UI */}
      {role === "DOCTOR" && (
        <>
          <button onClick={() => setPage("patients")}>Patients</button>
          <button onClick={() => setPage("add")}>Add Patient</button>
          <button onClick={() => setPage("diagnoses")}>Diagnoses</button>
          <button onClick={() => setPage("treatments")}>Treatments</button>
          <button onClick={() => setPage("doctors")}>Doctors</button>
          <button onClick={() => setPage("nurses")}>Nurses</button>
        </>
      )}

      {/* NURSE UI */}
      {role === "NURSE" && (
        <>
          <button onClick={() => setPage("patients")}>Patients</button>
          <button onClick={() => setPage("treatments")}>Treatments</button>
        </>
      )}

      <button onClick={logout}>Logout</button>

      <hr />

      {/* PAGES */}
      {page === "patients" && <PatientsPage />}
      {page === "add" && role === "DOCTOR" && <AddPatientPage />}
      {page === "diagnoses" && role === "DOCTOR" && <DiagnosesPage />}
      {page === "treatments" && <TreatmentsPage />}
      {page === "doctors" && role === "DOCTOR" && <DoctorsPage />}
      {page === "nurses" && role === "DOCTOR" && <NursesPage />}
    </div>
  );
}