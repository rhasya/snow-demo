import {NavLink} from 'react-router-dom'

function MyNavbar() {
  return (
    <nav className="navbar navbar-dark navbar-expand-lg bg-dark">
      <div className="container-fluid">
        <span className="navbar-brand">키즈코딩</span>
        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
          <li className="nav-item">
            <NavLink className="nav-link" aria-current="page" to="/">처음</NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/problems">문제들</NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/results">채점</NavLink>
          </li>
        </ul>
      </div>
    </nav>
  );
}

export default MyNavbar