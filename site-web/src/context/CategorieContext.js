import { createContext, useContext, useEffect, useState } from 'react';
import axios from 'axios';

const CategorieContext = createContext();

export const CategorieProvider = ({ children }) => 
{
  const [categories, setCategories] = useState([]);

  const chargerCategories = () => 
  {
    axios.get('http://localhost:8081/api/categories')
      .then(res => {
        if (Array.isArray(res.data)) 
        {
          setCategories(res.data);
        }
        else 
        {
          console.error("Réponse inattendue :", res.data);
          setCategories([]);
        }
      })
      .catch(err => 
      {
        console.error("Erreur de chargement des catégories :", err);
        setCategories([]);
      });
  };

  useEffect(() => 
  {
    chargerCategories();
  }, []);

  return (
    <CategorieContext.Provider value={{ categories, chargerCategories }}>
      {children}
    </CategorieContext.Provider>
  );
};

export const useCategorieContext = () => useContext(CategorieContext);
