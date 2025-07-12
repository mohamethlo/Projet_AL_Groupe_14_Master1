import { useLocation, Link } from 'react-router-dom';
import { useAuth } from '../auth/AuthContext';
import { useCategorieContext } from '../context/CategorieContext'; // ✅

const Sidebar = () => {
  const location = useLocation();
  const { user } = useAuth();
  const { categories } = useCategorieContext(); // ✅

  const isActive = (path) => location.pathname === path;

  return (
    <div className="sidebar-custom p-4 h-100" style={{ backgroundColor: '#002147' }}>
      {user && (
        <>
          <h5 className="text-white mb-3">Fonctionnalités</h5>
          <ul className="list-unstyled mb-4">

          <li className="mb-2">
            <Link
              to="/editor"
              className={`category-link ${isActive('/editor') ? 'active' : ''}`}
            >
              <i className="bi bi-journal-text me-2"></i>List articles
            </Link>
          </li>
            
          <li className="mb-2">
            <Link
              to="/editor/nouveau-article"
              className={`category-link ${isActive('/editor/nouveau-article') ? 'active' : ''}`}
            >
              <i className="bi bi-plus-circle me-2"></i>Ajout article
            </Link>
          </li>

            <li className="mb-2">
              <Link
                to="/editor/categories"
                className={`category-link ${isActive('/editor/categories') ? 'active' : ''}`}
              >
                <i className="bi bi-folder2-open me-2"></i>List categories
              </Link>
            </li>
            
            <li className="mb-2">
              <Link
                to="/editor/ajouter-categorie"
                className={`category-link ${isActive('/editor/ajouter-categorie') ? 'active' : ''}`}
              >
                <i className="bi bi-plus-square me-2"></i>Ajout categorie
              </Link>
            </li>
            
          </ul>
        </>
      )}

      <h5 className="mb-3 text-white">
        <i className="fa fa-folder me-2"></i> Catégories
      </h5>
      <ul className="list-unstyled">
        {categories.map(c => {
          const active = location.pathname === `/categorie/${c.id}`;
          return (
            <li key={c.id} className="mb-2">
              <Link
                to={`/categorie/${c.id}`}
                className={`category-link ${active ? 'active' : ''}`}
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
