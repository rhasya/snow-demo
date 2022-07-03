import { useEffect, useState } from 'react';
import axios from 'axios';

function row() {
  return (
      <tr>
        <td>col1</td>
      </tr>
  );
}

function Results() {
  const [results, setResults] = useState([]);
  useEffect(() => {
    // 현재 사용자는?
    const get = async () => {
      const res = await axios.get('http://localhost:3000/results/user1');
      setResults(res.data);
    }
    get();
  }, []);

  return (
      <main id="results" className="container pt-2">
        <table>
          <thead>
            <th>Col1</th>
          </thead>
          <tbody>
          {results.map(r => row(r))}
          </tbody>
        </table>
      </main>
  );
}
export default Results;