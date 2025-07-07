import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getArticlesByCategorie, getCategorieById } from '../services/api';
import ArticleCard from '../components/ArticleCard';

const ARTICLES_PAR_PAGE = 6;

const CategoriePage = () => {
  const { id } = useParams();
  const [articles, setArticles] = useState([]);
  const [categorie, setCategorie] = useState(null);
  const [page, setPage] = useState(0);

  useEffect(() => {
    getArticlesByCategorie(id)
      .then(res => {
        if (Array.isArray(res.data)) {
          const sortedArticles = [...res.data].sort(
            (a, b) => new Date(b.datePublication) - new Date(a.datePublication)
          );
          setArticles(sortedArticles);
        } else {
          console.error("Format inattendu :", res.data);
          setArticles([]);
        }
      })
      .catch(err => {
        console.error("Erreur chargement des articles :", err);
        setArticles([]);
      });

    getCategorieById(id)
      .then(res => setCategorie(res.data))
      .catch(err => {
        console.error("Erreur chargement catégorie :", err);
        setCategorie(null);
      });
  }, [id]);

  const indexDebut = page * ARTICLES_PAR_PAGE;
  const indexFin = indexDebut + ARTICLES_PAR_PAGE;
  const articlesPage = articles.slice(indexDebut, indexFin);

  const nbPages = Math.ceil(articles.length / ARTICLES_PAR_PAGE);

  const handleSuivant = () => {
    if (page < nbPages - 1) {
      setPage(page + 1);
    }
  };

  const handlePrecedent = () => {
    if (page > 0) {
      setPage(page - 1);
    }
  };

  return (
    <div className="container mt-4">
      <h3 className="mb-4 text-center">
        Articles de la catégorie : {categorie ? categorie.nom : "Chargement..."}
      </h3>

      <div className="row g-3">
        {articlesPage.map(article => (
          <div key={article.id} className="col-md-4">
            <ArticleCard article={article} />
          </div>
        ))}
      </div>

      {articles.length > ARTICLES_PAR_PAGE && (
        <div className="d-flex justify-content-center mt-4">
          <button
            className="btn btn-outline-primary me-2"
            onClick={handlePrecedent}
            disabled={page === 0}
          >
            Précédent
          </button>
          <button
            className="btn btn-outline-primary"
            onClick={handleSuivant}
            disabled={page >= nbPages - 1}
          >
            Suivant
          </button>
        </div>
      )}
    </div>
  );
};

export default CategoriePage;
