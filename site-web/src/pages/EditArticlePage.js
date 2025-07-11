import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useAuth } from '../auth/AuthContext';
import { useNavigate, useParams } from 'react-router-dom';
import Swal from 'sweetalert2';

const EditArticlePage = () => 
{
  const { id } = useParams();
  const navigate = useNavigate();
  const { user } = useAuth();

  const [titre, setTitre] = useState('');
  const [resume, setResume] = useState('');
  const [contenu, setContenu] = useState('');
  const [categorieId, setCategorieId] = useState('');
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    // Charger les catégories
    axios.get('http://localhost:8081/api/categories')
      .then(res => setCategories(res.data))
      .catch(err => console.error("Erreur chargement catégories", err));

    // Charger l’article
    axios.get(`http://localhost:8081/api/articles/${id}`)
      .then(res => {
        const art = res.data;
        setTitre(art.titre);
        setResume(art.resume);
        setContenu(art.contenu);
        setCategorieId(art.categorie?.id || '');
      })
      .catch(err => {
        console.error("Erreur chargement article", err);
        Swal.fire({
          icon: 'error',
          title: 'Erreur',
          text: "Impossible de charger l'article."
        });
      });
  }, [id]);

  const handleSubmit = (e) => 
  {
    e.preventDefault();

    axios.put(`http://localhost:8081/api/articles/${id}`, 
    {
      titre,
      resume,
      contenu,
      categorie: { id: parseInt(categorieId) },
      auteur: { email: user.email } 
    }, {
      headers: { Authorization: `Bearer ${user.token}` }
    }).then(() => {
      Swal.fire({
        icon: 'success',
        title: 'Modifié avec succès',
        showConfirmButton: false,
        timer: 1500
      });
      navigate('/editor');
    }).catch(err => {
      console.error("Erreur mise à jour article", err);
      Swal.fire({
        icon: 'error',
        title: 'Erreur',
        text: "La mise à jour de l'article a échoué."
      });
    });
  };

  return (
    <div className="container mt-4">
      <h3 className="text-warning mb-4">
        <i className="bi bi-pencil-square me-2"></i>Modifier l'article
      </h3>
      <form onSubmit={handleSubmit}>
        <div className="row g-3">
          <div className="col-md-6">
            <label className="form-label fw-bold">Titre</label>
            <input
              className="form-control"
              value={titre}
              onChange={e => setTitre(e.target.value)}
              required
            />
          </div>
          <div className="col-md-6">
            <label className="form-label fw-bold">Résumé</label>
            <input
              className="form-control"
              value={resume}
              onChange={e => setResume(e.target.value)}
              required
            />
          </div>
          <div className="col-md-6">
            <label className="form-label fw-bold">Catégorie</label>
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
          <div className="col-md-12">
            <label className="form-label fw-bold">Contenu</label>
            <textarea
              className="form-control"
              value={contenu}
              onChange={e => setContenu(e.target.value)}
              rows={6}
              required
            ></textarea>
          </div>
        </div>

        <div className="mt-4">
          <button className="btn btn-warning">
            <i className="bi bi-save me-1"></i> Mettre à jour
          </button>
        </div>
      </form>
    </div>
  );
};

export default EditArticlePage;
