import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getTrainingById } from "../services/trainingService";

export default function Detail() {
  const { id } = useParams();
  const [data, setData] = useState({});

  useEffect(() => {
    getTrainingById(id).then(res => setData(res.data));
  }, [id]);

  return (
    <div>
      <h2>{data.title}</h2>
      <p>{data.description}</p>
    </div>
  );
}