import { RouterProvider, createBrowserRouter } from 'react-router-dom';

import LandingPage from './pages/LandingPage';
import AnalyzeClient from './pages/LandingPage/children/AnalyzeClient';
import ClientList from './pages/LandingPage/children/ClientList';
import EditClient from './pages/LandingPage/children/EditClient';
import NewClient from './pages/LandingPage/children/NewClient';

function Routing() {
  const router = createBrowserRouter([
    {
      path: '/',
      element: <LandingPage />,
      children: [
        {
          path: 'clients',
          element: <ClientList />,
        },
        { path: 'edit-client/:id', element: <EditClient /> },
        { path: 'new-client', element: <NewClient /> },
        { path: 'analyze-client', element: <AnalyzeClient /> },
        { index: true, element: <ClientList /> },
      ],
    },
  ]);
  return <RouterProvider router={router} />;
}

export default Routing;
