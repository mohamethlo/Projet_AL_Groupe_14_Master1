import { useEffect, useState } from 'react';
import axios from 'axios';
import Swal from 'sweetalert2';
import { useAuth } from '../auth/AuthContext';
import { useCategorieContext } from '../context/CategorieContext'; 

const CategorieListPage = () => 
{
  const [categories, setCategories] = useState([]);
  const [editNom, setEditNom] = useState('');
  const [editId, setEditId] = useState(null);
  const { user } = useAuth();
  const { chargerCategories } = useCategorieContext(); 

  useEffect(() => 
  {
    axios.get('http://localhost:8081/api/categories')
      .then(res => setCategories(res.data))
      .catch(err => console.error("Erreur chargement catégories", err));
  }, []);

  const handleUpdate = () => 
  {
    if (!editNom || !editId) return;

    axios.put(`http://localhost:8081/api/categories/${editId}`, { nom: editNom }, 
    {
      headers: { Authorization: `Bearer ${user.token}` }
    }).then(() => {
      setCategories(prev =>
        prev.map(c =>
          c.id === editId ? { ...c, nom: editNom } : c
        )
      );
      setEditNom('');
      setEditId(null);
      chargerCategories(); 

      Swal.fire({
        icon: 'success',
        title: 'Catégorie mise à jour',
        showConfirmButton: false,
        timer: 1500
      });
    }).catch(err => {
      console.error(err);
      Swal.fire({
        icon: 'error',
        title: 'Erreur',
        text: "La modification a échoué."
      });
    });
  };

  const handleDelete = (id) => 
  {
    const cat = categories.find(c => c.id === id);

    Swal.fire({
      title: `Supprimer "${cat.nom}" ?`,
      text: "Cette action est irréversible.",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Oui, supprimer',
      cancelButtonText: 'Annuler',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6'
    }).then((result) => {
      if (result.isConfirmed) 
      {
        axios.delete(`http://localhost:8081/api/categories/${id}`, 
        {
          headers: { Authorization: `Bearer ${user.token}` }
        }).then(() => {
          setCategories(prev => prev.filter(c => c.id !== id));
          chargerCategories(); 

          Swal.fire({
            icon: 'success',
            title: 'Catégorie supprimée',
            timer: 1500,
            showConfirmButton: false
          });
        })
        .catch(err => 
        {
          console.error(err);
          Swal.fire({
            icon: 'error',
            title: 'Impossible de supprimer',
            text: "Cette catégorie est liée à un ou plusieurs articles."
          });
        });
      }
    });
  };

  return (
    <div className="container mt-4">
      <h3 className="mb-4 text-primary text-center"><i className="bi bi-folder2-open me-2"></i>Liste des catégories</h3>
      <table className="table table-hover table-bordered">
        <thead className="table-primary">
          <tr>
            <th style={{ width: '10%' }}>ID</th>
            <th style={{ width: '60%' }}>Nom</th>
            <th style={{ width: '30%' }}>Actions</th>
          </tr>
        </thead>
        <tbody>
          {categories.map(c => (
            <tr key={c.id}>
              <td>{c.id}</td>
              <td>
                {editId === c.id ? (
                  <input
                    value={editNom}
                    onChange={(e) => setEditNom(e.target.value)}
                    className="form-control"
                    autoFocus
                  />
                ) : c.nom}
              </td>
              <td>
                {editId === c.id ? (
                  <button
                    className="btn btn-success btn-sm me-2"
                    onClick={handleUpdate}
                  >
                    <i className="bi bi-check-lg"></i>
                  </button>
                ) : (
                  <button
                    className="btn btn-outline-primary btn-sm me-2"
                    onClick={() => {
                      setEditId(c.id);
                      setEditNom(c.nom);
                    }}
                  >
                    <i className="bi bi-pencil"></i>
                  </button>
                )}
                <button
                  className="btn btn-outline-danger btn-sm"
                  onClick={() => handleDelete(c.id)}
                >
                  <i className="bi bi-trash"></i>
                </button>
              </td>
            </tr>
          ))}
          {categories.length === 0 && (
            <tr>
              <td colSpan="3" className="text-center text-muted">Aucune catégorie disponible</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default CategorieListPage;
