import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getArticleById, getImagesByArticle } from '../services/api';
import { formatRelativeDate } from '../utils/dateUtils';


const ArticleDetailPage = () => 
{
  const { id } = useParams();
  const navigate = useNavigate();

  const [article, setArticle] = useState(null);
  const [images, setImages] = useState([]);

  useEffect(() => 
  {
    getArticleById(id).then(res => setArticle(res.data));
    getImagesByArticle(id).then(res => setImages(res.data));
  }, [id]);

  if (!article) return <p>Chargement...</p>;

  const carouselId = `carouselDetailArticle${article.id}`;

  return (
    <div className="container mt-4">
      <h1 className="text-center mt-4 mb-5"> Voici les details de l'article</h1>
      <div className="row">

        <div className="col-md-6">
          <div className="card shadow-sm h-100">
            
            <div className="card-title">
              <h2 className="mb-4 mt-2 text-center text-primary text-bold">{article.titre}</h2>
            </div>
            <div className="card-body">
              Publié le 
              <p className="text-muted" style={{ fontSize: '0.8rem' }}>
                {formatRelativeDate(article.datePublication)}
              </p>
              <p style={{ whiteSpace: 'pre-line' }}>{article.contenu}</p>
            </div>
          </div>
        </div>

        <div className="col-md-6 mb-4">
          {images.length > 0 && (
            <div
              id={carouselId}
              className="carousel slide shadow-sm"
              data-bs-ride="carousel"
            >
              <div className="carousel-indicators">
                {images.map((_, index) => (
                  <button
                    key={index}
                    type="button"
                    data-bs-target={`#${carouselId}`}
                    data-bs-slide-to={index}
                    className={index === 0 ? 'active' : ''}
                    aria-current={index === 0 ? 'true' : undefined}
                    aria-label={`Image ${index + 1}`}
                  />
                ))}
              </div>

              <div className="carousel-inner rounded">
                {images.map((img, index) => (
                  <div
                    key={img.id}
                    className={`carousel-item ${index === 0 ? 'active' : ''}`}
                  >
                    <img
                      src={`http://localhost:8081/api/images/${img.id}`}
                      alt={img.nomFichier}
                      className="d-block w-100"
                      style={{ maxHeight: '500px', objectFit: 'cover' }}
                    />
                  </div>
                ))}
              </div>

              {images.length > 1 && (
                <>
                  <button
                    className="carousel-control-prev"
                    type="button"
                    data-bs-target={`#${carouselId}`}
                    data-bs-slide="prev"
                  >
                    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Précédent</span>
                  </button>
                  <button
                    className="carousel-control-next"
                    type="button"
                    data-bs-target={`#${carouselId}`}
                    data-bs-slide="next"
                  >
                    <span className="carousel-control-next-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Suivant</span>
                  </button>
                </>
              )}
            </div>
          )}
        </div>

        
      </div>

      <div className="mb-5 mt-4">
        <button className="btn btn-primary" onClick={() => navigate(-1)}>
          ← Retour
        </button>
      </div>

    </div>
  );
};

export default ArticleDetailPage;
