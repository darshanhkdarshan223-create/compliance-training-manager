import { useState } from "react";
import { describeTraining, recommendTraining } from "../services/aiService";
import Card from "../components/Card";

export default function Dashboard() {
  const [input, setInput] = useState("");
  const [desc, setDesc] = useState("");
  const [rec, setRec] = useState("");

  const handleAI = async () => {
    const d = await describeTraining(input);
    const r = await recommendTraining(input);

    setDesc(d.data.description);
    setRec(r.data.recommendations);
  };

  return (
    <div>
      <h2>Dashboard</h2>

      <textarea onChange={(e) => setInput(e.target.value)} />

      <br />
      <button onClick={handleAI}>Run AI</button>

      <Card title="Description">{desc}</Card>
      <Card title="Recommendations">{rec}</Card>
    </div>
  );
}