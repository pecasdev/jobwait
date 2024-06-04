import * as React from "react";
import './index.css';
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css';
import {createRoot} from 'react-dom/client'

const rootElement = document.getElementById('root')

if (rootElement){
  const root = createRoot(rootElement);
  root.render(
    <React.StrictMode>
      <App />
    </React.StrictMode>
  );
}
