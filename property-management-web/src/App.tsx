import { Navigate, Route, Routes } from 'react-router';
import PlaceholderPage from './PlaceholderPage';
import AppLayout from './layouts/AppLayout';

const App = () => {
  return (
    <Routes>
      <Route path='/login' element={<PlaceholderPage title='Login' />} />
      <Route
        path='/reset-password'
        element={<PlaceholderPage title='Reset Password' />}
      />

      <Route element={<AppLayout />}>
        <Route index element={<Navigate to='/dashboard' replace />} />
        <Route
          path='/dashboard'
          element={<PlaceholderPage title='Dashboard' />}
        />
        <Route
          path='/properties'
          element={<PlaceholderPage title='Properties' />}
        />
        <Route
          path='/properties/:propertyId'
          element={<PlaceholderPage title='Property Details' />}
        />
        <Route path='/seasons' element={<PlaceholderPage title='Seasons' />} />
        <Route
          path='/investors'
          element={<PlaceholderPage title='Investors' />}
        />
        <Route path='/reports' element={<PlaceholderPage title='Reports' />} />
        <Route
          path='/my-account'
          element={<PlaceholderPage title='My Account' />}
        />
        <Route path='*' element={<PlaceholderPage title='Page Not Found' />} />
      </Route>
    </Routes>
  );
};

export default App;
