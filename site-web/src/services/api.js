import axios from 'axios';

const API_URL = "http://localhost:8081/api";

export const getArticles = () => axios.get(`${API_URL}/articles`);
export const getArticleById = (id) => axios.get(`${API_URL}/articles/${id}`);
export const getArticlesByCategorie = (id) => axios.get(`${API_URL}/articles/categorie/${id}`);
export const getCategories = () => axios.get(`${API_URL}/categories`);
export const getImagesByArticle = (articleId) => axios.get(`${API_URL}/images/article/${articleId}`);
export const getCategorieById = (id) => axios.get(`${API_URL}/categories/${id}`);
export const getRecentArticles = () => axios.get(`${API_URL}/articles/recent`);


