import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useAuth } from '../auth/AuthContext';
import { Link } from 'react-router-dom';
import Swal from 'sweetalert2';

const ListArticles = () => 
{
  const [articles, setArticles] = useState([]);
  const { user } = useAuth();

  useEffect(() => {
    axios.get("http://localhost:8081/api/articles", 
    {
      headers: {
        Authorization: `Bearer ${user.token}`
      }
    })
    .then(res => 
    {
      const sorted = [...res.data].sort(
        (a, b) => new Date(b.datePublication) - new Date(a.datePublication)
      );
      setArticles(sorted);
    })
    .catch(err => console.error("Erreur chargement articles :", err));
  }, [user.token]);

  const handleDelete = (id) => 
  {
    Swal.fire({
      title: "Supprimer cet article ?",
      text: "Cette action est irréversible.",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      confirmButtonText: "Oui, supprimer",
      cancelButtonText: "Annuler"
    }).then((result) => {
      if (result.isConfirmed) {
        axios.delete(`http://localhost:8081/api/articles/${id}`, 
        {
          headers: { Authorization: `Bearer ${user.token}` }
        }).then(() => {
          setArticles(prev => prev.filter(a => a.id !== id));
          Swal.fire({
            icon: "success",
            title: "Supprimé",
            text: "L'article a été supprimé.",
            timer: 1500,
            showConfirmButton: false
          });
        }).catch(err => {
          console.error("Erreur suppression :", err);
          Swal.fire({
            icon: "error",
            title: "Erreur",
            text: "Impossible de supprimer l'article."
          });
        });
      }
    });
  };

  return (
    <div className="container mt-4">
      <h1 className="text-center fw-bold text-primary"> Liste des articles</h1>

      <div className="mb-3 text-end">
        <Link to="/editor/nouveau-article" className="btn btn-outline-primary">
          <i className="bi bi-plus-circle me-1"></i> Nouvel article
        </Link>
      </div>

      <table className="table table-bordered table-hover">
        <thead className="table-primary">
          <tr>
            <th>Titre</th>
            <th>Résumé</th>
            <th>Date</th>
            <th style={{ width: '160px' }}>Actions</th>
          </tr>
        </thead>
        <tbody>
          {articles.map(article => (
            <tr key={article.id}>
              <td>{article.titre}</td>
              <td>{article.resume}</td>
              <td>{new Date(article.datePublication).toLocaleString()}</td>
              <td>
                <Link to={`/editor/edit/${article.id}`} className="btn btn-warning btn-sm me-2">
                  <i className="bi bi-pencil"></i>
                </Link>
                <button onClick={() => handleDelete(article.id)} className="btn btn-danger btn-sm">
                  <i className="bi bi-trash"></i>
                </button>
              </td>
            </tr>
          ))}
          {articles.length === 0 && (
            <tr>
              <td colSpan="4" className="text-center text-muted">Aucun article disponible.</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ListArticles;
