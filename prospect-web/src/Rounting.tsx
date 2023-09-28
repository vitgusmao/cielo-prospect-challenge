import { RouterProvider, createBrowserRouter } from 'react-router-dom';

import LandingPage from './pages/LandingPage';

function Routing() {
  const router = createBrowserRouter([{ path: '/', element: <LandingPage /> }]);
  return <RouterProvider router={router} />;
}

export default Routing;
