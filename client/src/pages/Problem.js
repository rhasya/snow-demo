import {useEffect, useState} from 'react';
import {Link, useParams} from 'react-router-dom';
import axios from 'axios';

import {problems} from '../TempData';

function Problem() {
  const [problem, setProblem] = useState({});
  const [source, setSource] = useState('');
  const params = useParams();
  useEffect(() => {
    // load data
    const {id} = params;
    // find data
    findProblem(parseInt(id));
  }, [params]);

  const findProblem = async (id) => {
    for (let i = 0; i < problems.length; i++) {
      if (problems[i].id === id) {
        setProblem(problems[i]);
        break;
      }
    }
  }
  const handleSourceChange = (e) => {
    setSource(e.target.value);
  }
  const handleSubmitClick = async () => {
    if (source.trim().length === 0) {
      return;
    }
    const result = await axios.post('http://localhost:8080/api/v1/submit-source', {
      username: 'user1',
      lang: 'python',
      source: source,
      problemId: params.id,
    });
    console.log(result.data);
  }

  return (
      <main id="problem" className="d-flex flex-row h-100">
        <div id="problem-left" className="w-50 p-2 overflow-auto">
          <h3>{problem.id}. {problem.title}</h3>
          <div><Link to="/problems">목록으로</Link></div>

          <div dangerouslySetInnerHTML={{__html: problem.content}}></div>
        </div>
        <div id="problem-right" className="w-50 p-2">
          <textarea className="w-100 h-75" value={source} onChange={handleSourceChange}></textarea>
          <button className="btn btn-primary" onClick={handleSubmitClick}>제출</button>
        </div>
      </main>
  );
}

export default Problem;