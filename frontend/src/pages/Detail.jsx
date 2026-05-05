import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { getTrainingById } from "../services/trainingService";

export default function Detail() {
  const { id } = useParams();
  const [data, setData] = useState({});
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getTrainingById(id).then(res => {
      setData(res.data);
      setLoading(false);
    });
  }, [id]);

  if (loading) return <div className="text-center mt-20 text-gray-500">Loading...</div>;

  return (
    <div className="max-w-4xl mx-auto p-8 bg-white rounded-xl shadow-lg mt-10">
      <Link to="/list" className="text-blue-500 hover:text-blue-700 mb-6 inline-block font-medium">&larr; Back to List</Link>
      
      <div className="border-b border-gray-200 pb-6 mb-6">
        <div className="flex justify-between items-center">
          <h2 className="text-4xl font-extrabold text-gray-900">{data.title}</h2>
          <span className={`px-4 py-2 rounded-full text-sm font-semibold tracking-wide ${data.status === 'COMPLETED' ? 'bg-green-100 text-green-800' : 'bg-yellow-100 text-yellow-800'}`}>
            {data.status || 'PENDING'}
          </span>
        </div>
        <p className="text-sm text-gray-500 mt-2">Due Date: {data.dueDate || 'No Due Date Set'}</p>
      </div>
      
      <div className="prose max-w-none text-gray-700">
        <h3 className="text-xl font-bold mb-3">Description</h3>
        <p className="whitespace-pre-wrap">{data.description}</p>
      </div>
    </div>
  );
}