import Navbar from './Navbar';
import Footer from './Footer';
import Sidebar from './Sidebar';
import { Outlet } from 'react-router-dom';

const Layout = () => 
(
  <>
    <Navbar />

    <div style={{ paddingTop: '64px' }}>
      <Sidebar />

      <div style={{ marginLeft: '220px', padding: '1rem', minHeight: 'calc(100vh - 104px)' }}>
        <Outlet />
      </div>
      
    </div>

    <Footer />
  </>
);

export default Layout;
