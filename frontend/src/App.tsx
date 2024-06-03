import * as React from 'react';
import { useState } from 'react';
import './App.css';

async function queryAddress(address) {
  if (address){
    const response = await fetch(address,
      {method: 'GET',
       headers: {'Content-Type': 'application/text'}
      }
    )
    return response.text();
  }
}

function handleSubmit(e, setResponse) {
  // Prevent the browser from reloading the page
  e.preventDefault();

  // Read the form data
  const form = e.target;
  const formData = new FormData(form);

  let formJson = Object.fromEntries(formData.entries());
  queryAddress(formJson["queryInput"]).then((response) => setResponse(response))
  // setResponse()

  // // You can pass formData as a fetch body directly:
  // fetch('/some-api', { method: form.method, body: formData });
}

export default function App() {
  const [response, setResponse] = useState("");
  return (
    <div>
      <h1>Professional Environment.</h1>
      <form onSubmit={(e) => handleSubmit(e, setResponse)}>
        <input name="queryInput" id="queryInput" required={true}/>
        <button type="submit">Search</button>
      </form>
      <h2>
        {response}
      </h2>
    </div>
  );
}
