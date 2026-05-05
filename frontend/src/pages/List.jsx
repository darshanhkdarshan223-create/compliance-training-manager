import { useEffect, useState } from "react";
import { getAllTrainings } from "../services/trainingService";
import { Link } from "react-router-dom";

export default function List() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getAllTrainings().then(res => {
      setData(res.data || []);
      setLoading(false);
    }).catch(() => setLoading(false));
  }, []);

  if (loading) return <div className="text-center mt-20 text-gray-500">Loading...</div>;

  return (
    <div className="max-w-6xl mx-auto p-8 mt-10">
      <div className="flex justify-between items-center mb-8">
        <h2 className="text-3xl font-bold text-gray-800">Compliance Trainings</h2>
        <Link to="/create" className="bg-blue-600 hover:bg-blue-700 text-white font-medium py-2 px-4 rounded-lg shadow transition-colors">
          + New Training
        </Link>
      </div>

      {data.length === 0 ? (
        <div className="text-center py-20 bg-white rounded-xl shadow border border-gray-100">
          <p className="text-gray-500 text-lg">No training records found.</p>
        </div>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {data.map(item => (
            <div key={item.id} className="bg-white rounded-xl shadow-md p-6 hover:shadow-lg transition-shadow border border-gray-100 flex flex-col">
              <div className="flex justify-between items-start mb-4">
                <h3 className="text-xl font-bold text-gray-900 line-clamp-2">{item.title}</h3>
                <span className={`text-xs px-2 py-1 rounded-full font-medium ${item.status === 'COMPLETED' ? 'bg-green-100 text-green-700' : 'bg-yellow-100 text-yellow-700'}`}>
                  {item.status || 'PENDING'}
                </span>
              </div>
              <p className="text-gray-600 text-sm mb-6 flex-grow line-clamp-3">{item.description}</p>
              <Link 
                to={`/detail/${item.id}`} 
                className="text-blue-600 font-medium hover:text-blue-800 text-sm mt-auto"
              >
                View Details &rarr;
              </Link>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}