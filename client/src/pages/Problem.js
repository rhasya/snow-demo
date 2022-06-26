import {useEffect, useState} from 'react';
import {Link, useParams} from 'react-router-dom';

import {problems} from '../TempData';


function Problem() {
  const [problem, setProblem] = useState({});
  const params = useParams();
  useEffect(() => {
    // load data
    const id = parseInt(params.id);
    // find data
    findProblem(id);
  }, []);

  const findProblem = async (id) => {
    for (let i = 0; i < problems.length; i++) {
      if (problems[i].id === id) {
        setProblem(problems[i]);
        break;
      }
    }
  }

  return (
      <main id="problem" className="container">
        <h3>{problem.id}. {problem.title}</h3>
        <div><Link to="/problems">목록으로</Link></div>

        <div>{problem.content}</div>
      </main>
  );
}

export default Problem;