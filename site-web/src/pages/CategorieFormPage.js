import { useState } from 'react';
import axios from 'axios';
import Swal from 'sweetalert2';
import { useAuth } from '../auth/AuthContext';
import { useCategorieContext } from '../context/CategorieContext'; 

const CategorieFormPage = () => 
{
  const [nom, setNom] = useState('');
  const { user } = useAuth();
  const { chargerCategories } = useCategorieContext(); 

  const handleAjouter = () => 
  {
    if (!nom.trim()) 
    {
      Swal.fire({
        icon: 'warning',
        title: 'Champ vide',
        text: 'Veuillez saisir un nom de catégorie.',
        confirmButtonColor: '#3085d6'
      });
      return;
    }

    axios.post('http://localhost:8081/api/categories', { nom }, 
    {
      headers: { Authorization: `Bearer ${user.token}` }
    }).then(() => 
    {
      setNom('');
      chargerCategories(); 
      Swal.fire({
        icon: 'success',
        title: 'Catégorie ajoutée !',
        showConfirmButton: false,
        timer: 1500
      });
    }).catch(err => 
    {
      console.error("Erreur ajout catégorie :", err);
      Swal.fire({
        icon: 'error',
        title: 'Erreur',
        text: 'Impossible d’ajouter la catégorie.'
      });
    });
  };

  return (
    <div className="container mt-4">
      <h3 className="mb-4 text-primary text-center">
        <i className="bi bi-plus-circle me-2"></i>Ajouter une catégorie
      </h3>
      <div className="card shadow-sm">
        <div className="card-body">
          <div className="d-flex">
            <input
              className="form-control me-2"
              placeholder="Nom de la catégorie"
              value={nom}
              onChange={(e) => setNom(e.target.value)}
              autoFocus
            />
            <button className="btn btn-success" onClick={handleAjouter}>
              <i className="bi bi-check-circle me-1"></i>Ajouter
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CategorieFormPage;
