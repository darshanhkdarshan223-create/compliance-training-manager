import { useState } from "react";
import { createTraining } from "../services/trainingService";

export default function Form() {
  const [title, setTitle] = useState("");
  const [desc, setDesc] = useState("");

  const handleSubmit = async () => {
    await createTraining({ title, description: desc });
    alert("Created");
  };

  return (
    <div>
      <h2>Create Training</h2>
      <input placeholder="Title" onChange={(e) => setTitle(e.target.value)} />
      <br />
      <textarea placeholder="Description" onChange={(e) => setDesc(e.target.value)} />
      <br />
      <button onClick={handleSubmit}>Submit</button>
    </div>
  );
}