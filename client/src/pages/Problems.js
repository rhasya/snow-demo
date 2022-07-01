import {Link} from 'react-router-dom';

import {problems} from '../TempData';

const styles = {
  col1: { width: '60px', textAlign: 'right'},
  col2: {}
}

function Row({id, title}) {
  return (
      <tr>
        <td style={styles.col1}>{id}</td>
        <td style={styles.col2}><Link to={`/problem/${id}`}>{title}</Link></td>
      </tr>
  );
}

function Problems() {
  return (
    <main id="problem" className="container">
      <h1>문제들</h1>
      <table className="table">
        <thead>
          <tr>
            <th style={styles.col1}>#</th>
            <th style={styles.col2}>제목</th>
          </tr>
        </thead>
        <tbody>
          {problems.map((p) => (<Row key={p.id} {...p} />))}
        </tbody>
      </table>
    </main>
  );
}

export default Problems;