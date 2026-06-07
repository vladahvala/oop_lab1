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
  const [role, setRole] = useState(localStorage.getItem("role"));

  const handleLogin = () => {
    const r = localStorage.getItem("role");

    if (!r) {
      alert("Login failed");
      return;
    }

    setRole(r);
    setPage("patients");
  };

  const logout = () => {
    localStorage.clear();
    setRole(null);
    setPage("login");
  };

  if (page === "login") {
    return <LoginPage onLogin={handleLogin} />;
  }

  return (
    <div style={styles.app}>
      {/* HEADER */}
      <header style={styles.header}>
        <div>
          <h2 style={styles.title}>🏥 Hospital System</h2>
          <p style={styles.role}>Role: {role}</p>
        </div>

        <button onClick={logout} style={styles.logout}>
          Logout
        </button>
      </header>

      {/* NAV */}
      <nav style={styles.nav}>
        {role === "DOCTOR" && (
          <>
            <button style={styles.btn} onClick={() => setPage("patients")}>Patients</button>
            <button style={styles.btn} onClick={() => setPage("add")}>Add</button>
            <button style={styles.btn} onClick={() => setPage("diagnoses")}>Diagnoses</button>
            <button style={styles.btn} onClick={() => setPage("treatments")}>Treatments</button>
            <button style={styles.btn} onClick={() => setPage("doctors")}>Doctors</button>
            <button style={styles.btn} onClick={() => setPage("nurses")}>Nurses</button>
          </>
        )}

        {role === "NURSE" && (
          <>
            <button style={styles.btn} onClick={() => setPage("patients")}>Patients</button>
            <button style={styles.btn} onClick={() => setPage("treatments")}>Treatments</button>
          </>
        )}
      </nav>

      {/* CONTENT */}
      <main style={styles.content}>
        {page === "patients" && <PatientsPage />}
        {page === "add" && role === "DOCTOR" && <AddPatientPage />}
        {page === "diagnoses" && role === "DOCTOR" && <DiagnosesPage />}
        {page === "treatments" && <TreatmentsPage />}
        {page === "doctors" && role === "DOCTOR" && <DoctorsPage />}
        {page === "nurses" && role === "DOCTOR" && <NursesPage />}
      </main>
    </div>
  );
}

const styles = {
  app: {
    fontFamily: "Arial, sans-serif",
    background: "#f5f8ff",
    minHeight: "100vh"
  },

  header: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    padding: "15px 20px",
    background: "#2b6cb0",
    color: "white"
  },

  title: {
    margin: 0
  },

  role: {
    margin: 0,
    fontSize: "14px",
    opacity: 0.9
  },

  logout: {
    background: "#e53e3e",
    color: "white",
    border: "none",
    padding: "8px 12px",
    borderRadius: "8px",
    cursor: "pointer"
  },

  nav: {
    display: "flex",
    flexWrap: "wrap",
    gap: "10px",
    padding: "15px",
    background: "white",
    borderBottom: "1px solid #ddd"
  },

  btn: {
    padding: "8px 12px",
    border: "1px solid #2b6cb0",
    background: "white",
    color: "#2b6cb0",
    borderRadius: "8px",
    cursor: "pointer"
  },

  content: {
    padding: "20px"
  }
};