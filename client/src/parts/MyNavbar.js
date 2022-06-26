import {NavLink} from 'react-router-dom'

function MyNavbar() {
  return (
    <nav className="navbar navbar-dark navbar-expand-lg bg-dark">
      <div className="container-fluid">
        <a className="navbar-brand" href="src/parts/MyNavbar#">AlgoKids</a>
        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
          <li className="nav-item">
            <NavLink className="nav-link" aria-current="page" to="/">처음</NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/problems">문제들</NavLink>
          </li>
        </ul>
      </div>
    </nav>
  );
}

export default MyNavbar