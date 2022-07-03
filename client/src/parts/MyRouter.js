import {BrowserRouter, Route, Routes} from 'react-router-dom';

import App from './App';
import Nothing from './Nothing';

import Home from '../pages/Home';
import Problems from '../pages/Problems';
import Problem from '../pages/Problem';
import Results from '../pages/Results'

function MyRouter() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />}>
          <Route index element={<Home />} />
          <Route path="problems" element={<Problems />} />
          <Route path="problem/:id" element={<Problem />} />
          <Route path="results" element={<Results />} />
          <Route path="*" element={<Nothing />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default MyRouter;