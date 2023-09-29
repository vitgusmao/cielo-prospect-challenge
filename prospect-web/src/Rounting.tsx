import { RouterProvider, createBrowserRouter } from 'react-router-dom';

import LandingPage from './pages/LandingPage';
import ClientList from './pages/LandingPage/children/ClientList';
import QuickActions from './pages/LandingPage/children/QuickActions';

function Routing() {
  const router = createBrowserRouter([
    {
      path: '/',
      element: <LandingPage />,
      children: [
        { path: 'clients', element: <ClientList /> },
        { index: true, element: <QuickActions /> },
      ],
    },
  ]);
  return <RouterProvider router={router} />;
}

export default Routing;
