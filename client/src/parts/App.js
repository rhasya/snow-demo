import {Outlet} from 'react-router-dom';
import MyNavbar from './MyNavbar';

import './App.css';

function App() {
  return (
    <div className="App">
      <MyNavbar/>
      <Outlet/>
    </div>
  );
}

export default App;
