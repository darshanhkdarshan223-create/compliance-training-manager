import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav style={{ padding: 10, background: "#1B4F8A", color: "white" }}>
      <Link to="/" style={{ marginRight: 10 }}>Dashboard</Link>
      <Link to="/list" style={{ marginRight: 10 }}>Trainings</Link>
      <Link to="/create">Create</Link>
    </nav>
  );
}