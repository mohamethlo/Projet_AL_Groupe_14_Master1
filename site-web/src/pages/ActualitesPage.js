import { useEffect, useState } from "react";
import { getRecentArticles } from '../services/api';
import ArticleCard from "../components/ArticleCard";

const ActualitesPage = () => {
  const [articles, setArticles] = useState([]);

  useEffect(() => {
    getRecentArticles()
      .then((res) => setArticles(res.data))
      .catch((err) => console.error("Erreur chargement actualités :", err));
  }, []);

  return (
    <div className="container my-5">
      <h2 className="mb-4 text-center">Actualités récentes (moins de 72h)</h2>
      <div className="d-flex flex-wrap justify-content-center gap-4">
        {articles.length > 0 ? (
          articles.map((article) => <ArticleCard key={article.id} article={article} />)
        ) : (
          <p className="text-muted">Aucune actualité récente pour le moment.</p>
        )}
      </div>
    </div>
  );
};

export default ActualitesPage;
