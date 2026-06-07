const BASE_URL = "http://localhost:8080/hospital-system";

const ROLE = "DOCTOR";

export async function getPatients() {
  const res = await fetch(`${BASE_URL}/patients`, {
    headers: {
      Authorization: ROLE
    }
  });
  return res.json();
}

export async function addPatient(patient) {
  const res = await fetch(`${BASE_URL}/patients`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: ROLE
    },
    body: JSON.stringify(patient)
  });

  return res.json();
}

export async function dischargePatient(id) {
  const res = await fetch(`${BASE_URL}/patients/discharge`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: ROLE
    },
    body: JSON.stringify({ patientId: id })
  });

  return res.json();
}