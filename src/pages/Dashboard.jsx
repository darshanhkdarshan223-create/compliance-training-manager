import { useState } from "react";
import { describeTraining, recommendTraining } from "../services/aiService";

export default function Dashboard() {
  const [input, setInput] = useState("");
  const [desc, setDesc] = useState("");
  const [rec, setRec] = useState("");
  const [loading, setLoading] = useState(false);

  const handleAI = async () => {
    setLoading(true);
    try {
      const d = await describeTraining(input);
      const r = await recommendTraining(input);
      setDesc(d.data?.description || "No description generated.");
      setRec(r.data?.recommendations || "No recommendations generated.");
    } catch (e) {
      setDesc("AI service unavailable.");
    }
    setLoading(false);
  };

  return (
    <div className="max-w-6xl mx-auto p-8 mt-10">
      <h2 className="text-3xl font-bold text-gray-800 mb-8">AI Dashboard</h2>

      <div className="bg-white rounded-xl shadow-md p-6 border border-gray-100 mb-8">
        <label className="block text-sm font-medium text-gray-700 mb-2">Describe your compliance situation</label>
        <textarea 
          className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 h-32 mb-4"
          placeholder="e.g. We need a new training module for GDPR compliance in Europe..."
          onChange={(e) => setInput(e.target.value)} 
        />
        <button 
          onClick={handleAI}
          disabled={loading || !input}
          className="bg-blue-600 hover:bg-blue-700 disabled:bg-blue-300 text-white font-semibold py-2 px-6 rounded-lg transition-colors"
        >
          {loading ? "Analyzing..." : "Generate Insights"}
        </button>
      </div>

      <div className="grid md:grid-cols-2 gap-6">
        <div className="bg-white rounded-xl shadow border border-gray-100 overflow-hidden">
          <div className="bg-blue-50 px-6 py-4 border-b border-gray-100">
            <h3 className="font-bold text-gray-800">AI Description</h3>
          </div>
          <div className="p-6">
            <p className="text-gray-600 whitespace-pre-wrap">{desc || "Run AI to generate a description."}</p>
          </div>
        </div>

        <div className="bg-white rounded-xl shadow border border-gray-100 overflow-hidden">
          <div className="bg-green-50 px-6 py-4 border-b border-gray-100">
            <h3 className="font-bold text-gray-800">Recommendations</h3>
          </div>
          <div className="p-6">
            <p className="text-gray-600 whitespace-pre-wrap">{rec || "Run AI to generate recommendations."}</p>
          </div>
        </div>
      </div>
    </div>
  );
}