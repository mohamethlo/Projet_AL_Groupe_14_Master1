const Footer = () => (
  <footer id="contact" style={{ backgroundColor: '#002147' }} className="text-white pt-4 pb-3 mt-5">
    <div className="container text-center">
      <h5 className="text-uppercase mb-3">
        École Supérieure Polytechnique
      </h5>
      <p>
        <i className="fas fa-graduation-cap me-2"></i>
        Université Cheikh Anta Diop de Dakar<br />
        <i className="fas fa-map-marker-alt me-2"></i>
        Corniche Ouest, BP 5085 Dakar-Fann<br />
        <i className="fas fa-phone me-2"></i>
        <a href="tel:+221338240540" className="text-white text-decoration-none">+221 33 824 05 40</a><br />
        <i className="fas fa-envelope me-2"></i>
        <a href="mailto:esp@esp.sn" className="text-white text-decoration-none">esp@esp.sn</a><br />
        <i className="fas fa-clock me-2"></i>
        Horaires : 08h-12h et 15h-18h
      </p>

      <hr className="border-light w-50 mx-auto" />

      <p className="mt-3 mb-0">
        © 2025 - Tous droits réservés
      </p>
    </div>
  </footer>
);

export default Footer;
