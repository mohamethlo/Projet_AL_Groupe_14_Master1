import { useEffect, useState } from 'react';
import { getArticles } from '../services/api';
import ArticleCard from '../components/ArticleCard';
import { AnimatePresence, motion } from 'framer-motion';

const HomePage = () => 
{
  const [articles, setArticles] = useState([]);
  const [page, setPage] = useState(0);
  const articlesParPage = 9;

  useEffect(() => 
  {
    getArticles()
      .then(res => {
        if (Array.isArray(res.data)) 
        {
          const articlesTries = [...res.data].sort(
            (a, b) => new Date(b.datePublication) - new Date(a.datePublication)
          );
          setArticles(articlesTries);
        }
        else 
        {
          console.error("Format inattendu :", res.data);
          setArticles([]);
        }
      })
      .catch(err => {
        console.error("Erreur API articles :", err);
        setArticles([]);
      });
  }, []);

  const indexDebut = page * articlesParPage;
  const indexFin = indexDebut + articlesParPage;
  const articlesPage = articles.slice(indexDebut, indexFin);
  const nbPages = Math.ceil(articles.length / articlesParPage);

  const handlePrecedent = () => 
  {
    if (page > 0) setPage(page - 1);
  };

  const handleSuivant = () => 
  {
    if (page < nbPages - 1) setPage(page + 1);
  };

  return (
    <div className="container mt-4">
      <h3 className="mb-4 text-center">Derniers articles</h3>

      <AnimatePresence mode="wait">
        <motion.div
          key={page}
          initial={{ opacity: 0, x: 70 }}
          animate={{ opacity: 1, x: 0 }}
          exit={{ opacity: 0, x: -70 }}
          transition={{ duration: 0.5 }}
        >
          <div className="row g-3">
            {articlesPage.map((article, index) => (
              <motion.div
                key={article.id}
                className="col-md-4 d-flex justify-content-center"
                initial={{ opacity: 0, y: 30 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ delay: index * 0.08, duration: 0.4 }}
              >
                <ArticleCard article={article} />
              </motion.div>
            ))}
          </div>
        </motion.div>
      </AnimatePresence>

      <div className="d-flex justify-content-center align-items-center my-4 gap-3 mb-5">
        <button
          className="btn btn-outline-primary"
          onClick={handlePrecedent}
          disabled={page === 0}
        >
          ← Précédent
        </button>
        <span>Page {page + 1} / {nbPages}</span>
        <button
          className="btn btn-outline-primary"
          onClick={handleSuivant}
          disabled={page >= nbPages - 1}
        >
          Suivant →
        </button>
      </div>
    </div>
  );
};

export default HomePage;
