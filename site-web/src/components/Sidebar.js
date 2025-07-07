import { useEffect, useState } from 'react';
import { useLocation, Link } from 'react-router-dom';
import { getCategories } from '../services/api';

const Sidebar = () => {
  const [categories, setCategories] = useState([]);
  const location = useLocation();

  useEffect(() => {
    getCategories()
      .then(res => {
        if (Array.isArray(res.data)) {
          setCategories(res.data);
        } else {
          console.error("Réponse inattendue (non tableau) :", res.data);
          setCategories([]);
        }
      })
      .catch(err => {
        console.error("Erreur API /categories :", err);
        setCategories([]);
      });
  }, []);

  return (
    <div className="sidebar-custom p-4 h-100">
      <h5 className="mb-3 text-white">📁 Catégories</h5>
      <ul className="list-unstyled">
        {categories.map(c => {
          const isActive = location.pathname === `/categorie/${c.id}`;
          return (
            <li key={c.id} className="mb-2">
              <Link
                to={`/categorie/${c.id}`}
                className={`category-link ${isActive ? 'active' : ''}`}
              >
                {c.nom}
              </Link>
            </li>
          );
        })}
      </ul>

    </div>
  );
};

export default Sidebar;
