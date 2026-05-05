import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav className="bg-[#1B4F8A] text-white shadow-lg">
      <div className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex items-center justify-between h-16">
          <div className="flex items-center">
            <Link to="/" className="font-bold text-xl tracking-tight">Compliance Manager</Link>
            <div className="hidden md:block ml-10">
              <div className="flex items-baseline space-x-4">
                <Link to="/" className="px-3 py-2 rounded-md text-sm font-medium hover:bg-blue-800 transition-colors">Dashboard</Link>
                <Link to="/list" className="px-3 py-2 rounded-md text-sm font-medium hover:bg-blue-800 transition-colors">Trainings</Link>
                <Link to="/create" className="px-3 py-2 rounded-md text-sm font-medium bg-blue-600 hover:bg-blue-500 transition-colors">+ Create</Link>
              </div>
            </div>
          </div>
          <div>
            <Link to="/login" className="px-4 py-2 text-sm font-medium border border-blue-400 rounded-md hover:bg-blue-800 transition-colors">Login</Link>
          </div>
        </div>
      </div>
    </nav>
  );
}