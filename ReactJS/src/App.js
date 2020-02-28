import React from "react";
import "./App.css";
import InstructorApp from "./Components/InstructorApp";
import AppNavbar from "./AppNavbar";

function App() {
	return (
		<div className="containerr">
			<AppNavbar/>
			<main role="main" class="container mainApp">
				<div className="mt-4">
					<InstructorApp />
				</div>
			</main>
		</div>
	);
}

export default App;
