import React, { useState } from 'react';
import axios from 'axios';
import Swal from 'sweetalert2';
import { useAuth } from '../auth/AuthContext';
import { useNavigate } from 'react-router-dom';
import { useCategorieContext } from '../context/CategorieContext';

const NewArticlePage = () => 
{
  const [titre, setTitre] = useState('');
  const [resume, setResume] = useState('');
  const [contenu, setContenu] = useState('');
  const [categorieId, setCategorieId] = useState('');
  const [images, setImages] = useState([]);
  const { user } = useAuth();
  const navigate = useNavigate();
  const { categories } = useCategorieContext();

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!titre.trim() || !resume.trim() || !contenu.trim() || !categorieId) 
    {
      Swal.fire({
        icon: 'warning',
        title: 'Champs requis manquants',
        text: 'Veuillez remplir tous les champs obligatoires.',
        confirmButtonColor: '#3085d6'
      });
      return;
    }

    try 
    {
      const articleRes = await axios.post('http://localhost:8081/api/articles', 
      {
        titre,
        resume,
        contenu,
        categorieId: parseInt(categorieId),
        auteurEmail: user.email 
      }, {
        headers: { Authorization: `Bearer ${user.token}` }
      });


      const articleId = articleRes.data.id;

      Swal.fire({
        icon: 'success',
        title: 'Article créé avec succès !',
        showConfirmButton: false,
        timer: 1800
      });

      navigate('/editor');
    } catch (error) {
      console.error("Erreur création article", error);
      Swal.fire({
        icon: 'error',
        title: 'Erreur',
        text: "Impossible de créer l'article. Veuillez réessayer."
      });
    }
  };

  return (
    <div className="container mt-4">
      <h3 className="mb-4 text-success">
        <i className="bi bi-file-earmark-plus me-2"></i>Nouveau Article
      </h3>

      <div className="card shadow-sm">
        <div className="card-body">
          <form onSubmit={handleSubmit}>
            <div className="row">
              <div className="col-md-6 mb-3">
                <label className="form-label fw-semibold text-dark fs-6">Titre</label>
                <input
                  type="text"
                  className="form-control"
                  value={titre}
                  onChange={e => setTitre(e.target.value)}
                  placeholder="Entrez le titre de l'article"
                  required
                />
              </div>

              <div className="col-md-6 mb-3">
                <label className="form-label fw-semibold text-dark fs-6">Résumé</label>
                <input
                  type="text"
                  className="form-control"
                  value={resume}
                  onChange={e => setResume(e.target.value)}
                  placeholder="Résumé de l'article"
                  required
                />
              </div>

              <div className="col-md-6 mb-3">
                <label className="form-label fw-semibold text-dark fs-6">Catégorie</label>
                <select
                  className="form-select"
                  value={categorieId}
                  onChange={e => setCategorieId(e.target.value)}
                  required
                >
                  <option value="">-- Choisir une catégorie --</option>
                  {categories.map(c => (
                    <option key={c.id} value={c.id}>{c.nom}</option>
                  ))}
                </select>
              </div>

              <div className="col-12 mb-3">
                <label className="form-label fw-semibold text-dark fs-6">Contenu</label>
                <textarea
                  className="form-control"
                  value={contenu}
                  onChange={e => setContenu(e.target.value)}
                  rows={5}
                  placeholder="Rédigez votre contenu ici..."
                  required
                />
              </div>
            </div>

            <div className="d-flex justify-content-end">
              <button className="btn btn-success">
                <i className="bi bi-save me-2"></i>Créer l'article
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default NewArticlePage;
