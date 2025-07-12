import { Link } from 'react-router-dom';
import logo from '../images/logo_ESP.png';

const Navbar = () => (
  <nav className="navbar navbar-expand-lg navbar-dark bg-primary fixed-top">
    <div className="container-fluid">

      <Link className="navbar-brand d-flex align-items-center" to="/">
        <div className="logo-wrapper me-2">
          <img
            src={logo}
            alt="Logo ESP"
            width="40"
            height="40"
            className="rounded-circle"
          />
        </div>
        Site d'Actualité ESP
      </Link>

      <button
        className="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
      >
        <span className="navbar-toggler-icon"></span>
      </button>

      <div className="collapse navbar-collapse" id="navbarNav">
        <ul className="navbar-nav ms-auto me-3">
          <li className="nav-item">
            <Link className="nav-link" to="/">
              <i className="fas fa-home me-1"></i>Accueil
            </Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/actualites">
              <i className="fas fa-newspaper me-1"></i>Actualités
            </Link>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#contact">
              <i className="fas fa-envelope me-1"></i>Contact
            </a>
          </li>

        </ul>

        <div className="d-flex">
          <Link className="btn btn-outline-light mx-2" to="/login">
            Se connecter
          </Link>
        </div>

      </div>
    </div>
  </nav>
);

export default Navbar;
