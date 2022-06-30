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
      <main id="problem" className="d-flex flex-row h-100">
        <div id="problem-left" className="w-50 p-2 overflow-auto">
          <h3>{problem.id}. {problem.title}</h3>
          <div><Link to="/problems">목록으로</Link></div>

          <div>{problem.content}</div>
        </div>
        <div id="problem-right" className="w-50 p-2">
          <textarea className="w-100 h-75"></textarea>
          <button className="btn btn-primary">제출</button>
        </div>
      </main>
  );
}

export default Problem;