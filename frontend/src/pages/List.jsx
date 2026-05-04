import { useEffect, useState } from "react";
import { getAllTrainings } from "../services/trainingService";
import { Link } from "react-router-dom";

export default function List() {
  const [data, setData] = useState([]);

  useEffect(() => {
    getAllTrainings().then(res => setData(res.data));
  }, []);

  return (
    <div>
      <h2>Training List</h2>
      {data.map(item => (
        <div key={item.id}>
          <Link to={`/detail/${item.id}`}>{item.title}</Link>
        </div>
      ))}
    </div>
  );
}