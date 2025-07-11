import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import HomePage from './pages/HomePage';
import ArticleDetailPage from './pages/ArticleDetailPage';
import CategoriePage from './pages/CategoriePage';
import ActualitesPage from './pages/ActualitesPage';
import LoginPage from './pages/LoginPage';
import ListArticles from './pages/ListArticles';
import NewArticlePage from './pages/NewArticlePage';
import EditArticlePage from './pages/EditArticlePage';
import CategorieListPage from './pages/CategorieListPage';
import CategorieFormPage from './pages/CategorieFormPage';

import { AuthProvider } from './auth/AuthContext';
import ProtectedRoute from './routes/ProtectedRoute';
import { CategorieProvider } from './context/CategorieContext'; 

const App = () => (
  <AuthProvider>
    <CategorieProvider>
      <Router>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<HomePage />} />
            <Route path="article/:id" element={<ArticleDetailPage />} />
            <Route path="categorie/:id" element={<CategoriePage />} />
            <Route path="actualites" element={<ActualitesPage />} />
            <Route path="login" element={<LoginPage />} />

            <Route
              path="editor"
              element={<ProtectedRoute><ListArticles /></ProtectedRoute>}
            />
            <Route
              path="editor/nouveau-article"
              element={<ProtectedRoute><NewArticlePage /></ProtectedRoute>}
            />
            <Route
              path="editor/edit/:id"
              element={<ProtectedRoute><EditArticlePage /></ProtectedRoute>}
            />
            <Route
              path="editor/categories"
              element={<ProtectedRoute><CategorieListPage /></ProtectedRoute>}
            />
            <Route
              path="editor/ajouter-categorie"
              element={<ProtectedRoute><CategorieFormPage /></ProtectedRoute>}
            />
          </Route>
        </Routes>
      </Router>
    </CategorieProvider>
  </AuthProvider>
);

export default App;
