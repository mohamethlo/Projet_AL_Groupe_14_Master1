import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import HomePage from './pages/HomePage';
import ArticleDetailPage from './pages/ArticleDetailPage';
import CategoriePage from './pages/CategoriePage';
import ActualitesPage from './pages/ActualitesPage';

const App = () => (
  <Router>
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<HomePage />} />
        <Route path="article/:id" element={<ArticleDetailPage />} />
        <Route path="categorie/:id" element={<CategoriePage />} />
        <Route path="/actualites" element={<ActualitesPage />} />
      </Route>
    </Routes>
  </Router>
);

export default App;
