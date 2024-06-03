import * as React from 'react';
import './App.css';

async function queryAddress(address) {
  if (address){
    const response = await fetch(address,
      {method: 'POST',
        body: "poop", headers: {'Content-Type': 'application/text'}
      }
    )
    console.log(response)
  }
  
  console.log("Done!")
}

function handleSubmit(e) {
  // Prevent the browser from reloading the page
  e.preventDefault();

  // Read the form data
  const form = e.target;
  const formData = new FormData(form);

  let formJson = Object.fromEntries(formData.entries());
  queryAddress(formJson["queryInput"])

  // // You can pass formData as a fetch body directly:
  // fetch('/some-api', { method: form.method, body: formData });
}

export default function App() {
  return (
    <div>
      <h1>Fart. NOW. 😡😡😡</h1>
      <form onSubmit={handleSubmit}>
        <input name="queryInput" id="queryInput" required={true}/>
        <button type="submit">Search</button>
      </form>
    </div>
  );
}
