import { Chart, registerables } from "chart.js";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import "./App.css";
import { SubmitRoute } from "./routes/SubmitRoute";
import { RootRoute } from "./routes/RootRoute";
import { StatsRoute } from "./routes/StatsRoute";
import { Footer } from "./reusable/Footer";
import { Header } from "./reusable/Header";

Chart.register(...registerables);

export default function App() {
    return (
        <div>
            <Header/>
            <BrowserRouter>
                <Switch>
                    <Route path="/submit">
                        <SubmitRoute />
                    </Route>

                    <Route path="/stats">
                        <StatsRoute />
                    </Route>

                    <Route path="/">
                        <RootRoute />
                    </Route>
                </Switch>
            </BrowserRouter>
            <Footer/>
        </div>
    );
}
