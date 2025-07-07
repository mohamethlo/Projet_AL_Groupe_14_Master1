import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { motion } from 'framer-motion';
import { formatRelativeDate } from '../utils/dateUtils';


const ArticleCard = ({ article }) => 
{
  const carouselId = `carouselArticle${article.id}`;
  const [images, setImages] = useState([]);

  useEffect(() => {
    axios
      .get(`http://localhost:8081/api/images/article/${article.id}`)
      .then((res) => setImages(res.data))
      .catch((err) =>
        console.error("Erreur lors du chargement des images :", err)
      );
  }, [article.id]);

  useEffect(() => 
  {
    if (window.bootstrap) 
    {
      const el = document.querySelector(`#${carouselId}`);
      if (el) 
      {
        new window.bootstrap.Carousel(el);
      }
    }
  }, [images]);

  return (
    <Link to={`/article/${article.id}`} style={{ textDecoration: "none", color: "inherit" }}>
      <motion.div
        className="card mb-4"
        style={{ width: '25rem', cursor: 'pointer' }}
        whileHover=
        {{
          scale: 1.03,
          boxShadow: "0 12px 25px rgba(0, 0, 0, 0.3)",
        }}
        transition={{ type: 'spring', stiffness: 300 }}
      >
        <div
          id={carouselId}
          className="carousel slide"
          data-bs-ride="carousel"
          data-bs-interval="3000"
          style={{ cursor: "pointer" }}
        >
          <div className="carousel-indicators">
            {images.map((_, index) => 
            (
              <button
                key={index}
                type="button"
                data-bs-target={`#${carouselId}`}
                data-bs-slide-to={index}
                className={index === 0 ? 'active' : ''}
                aria-current={index === 0 ? 'true' : undefined}
                aria-label={`Slide ${index + 1}`}
              />
            ))}
          </div>

          <div className="carousel-inner">
            {images.map((img, index) => 
            (
              <div
                key={img.id}
                className={`carousel-item ${index === 0 ? 'active' : ''}`}
              >
                  <img
                    src={`http://localhost:8081/api/images/${img.id}`}
                    className="d-block w-100"
                    alt={`Image ${index + 1}`}
                    style={{ height: '200px', objectFit: 'cover' }}
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

        <div className="card-body">
          <h3 className="card-title">{article.titre}</h3>
          <p className="card-text">{article.resume}</p>
          <p className="text-muted" style={{ fontSize: '0.8rem' }}>
            {formatRelativeDate(article.datePublication)}
          </p>
            <div className='text-primary text-end'>Lire plus...</div>
        </div>
      </motion.div>
    </Link>

  );
};

export default ArticleCard;
