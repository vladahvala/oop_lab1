import { useEffect, useState } from "react";

const BASE_URL = "http://localhost:8080/hospital-system";

export default function NursesPage() {
  const [nurses, setNurses] = useState([]);
  const [name, setName] = useState("");

  async function load() {
    const res = await fetch(`${BASE_URL}/nurses`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    });

    setNurses(await res.json());
  }

  useEffect(() => {
    load();
  }, []);

  async function add() {
    await fetch(`${BASE_URL}/nurses`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem("token")}`
      },
      body: JSON.stringify({ fullName: name })
    });

    setName("");
    load();
  }

  return (
    <div style={styles.page}>
      <h2 style={styles.title}>Nurses</h2>

      {/* FORM */}
      <div style={styles.card}>
        <input
          style={styles.input}
          placeholder="Full name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />

        <button onClick={add} style={styles.button}>
          Add Nurse
        </button>
      </div>

      <hr style={{ margin: "20px 0" }} />

      {/* LIST */}
      <div style={styles.grid}>
        {nurses.length === 0 ? (
          <p>No nurses</p>
        ) : (
          nurses.map((n) => (
            <div key={n.id} style={styles.cardSmall}>
              <p style={styles.name}>{n.fullName}</p>
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

  card: {
    background: "white",
    padding: "15px",
    borderRadius: "12px",
    boxShadow: "0 4px 12px rgba(0,0,0,0.08)",
    maxWidth: "400px"
  },

  cardSmall: {
    background: "white",
    padding: "12px",
    borderRadius: "10px",
    boxShadow: "0 3px 10px rgba(0,0,0,0.06)"
  },

  grid: {
    display: "grid",
    gridTemplateColumns: "repeat(auto-fit, minmax(180px, 1fr))",
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

  name: {
    fontWeight: "600",
    color: "#2d3748"
  }
};