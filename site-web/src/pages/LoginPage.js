import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../auth/AuthContext';
import axios from 'axios';
import Swal from 'sweetalert2';

const LoginPage = () => 
{
  const [email, setEmail] = useState('');
  const [motDePasse, setMotDePasse] = useState('');
  const { login } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = (e) => 
  {
    e.preventDefault();

    if (!email || !motDePasse) 
    {
      Swal.fire({
        icon: 'warning',
        title: 'Champs manquants',
        text: 'Veuillez remplir tous les champs',
        confirmButtonColor: '#3085d6'
      });
      return;
    }

    axios.post("http://localhost:8081/auth/login", { email, motDePasse })
      .then(res => 
      {
        const token = res.data;
        login({ token, email });
        localStorage.setItem("token", token);

        Swal.fire({
          icon: 'success',
          title: 'Connexion réussie',
          timer: 1200,
          showConfirmButton: false
        });

        navigate("/editor");
      })
      .catch(() => 
      {
        Swal.fire({
          icon: 'error',
          title: 'Échec de connexion',
          text: 'Vérifiez vos identifiants.',
          confirmButtonColor: '#d33'
        });
      });
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card shadow">
            <div className="card-header bg-primary text-white text-center fw-bold">
              <i className="bi bi-person-lock me-2"></i>Connexion éditeur
            </div>
            <div className="card-body">
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label className="form-label fw-bold">Adresse email</label>
                  <input
                    type="email"
                    className="form-control"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder="email@exemple.com"
                    required
                  />
                </div>
                <div className="mb-3">
                  <label className="form-label fw-bold">Mot de passe</label>
                  <input
                    type="password"
                    className="form-control"
                    value={motDePasse}
                    onChange={(e) => setMotDePasse(e.target.value)}
                    placeholder="••••••••"
                    required
                  />
                </div>
                <div className="d-grid">
                  <button type="submit" className="btn btn-primary">
                    <i className="bi bi-box-arrow-in-right me-1"></i> Se connecter
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
