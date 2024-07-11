export function RootRoute() {
    return (
        <span>
            <p> welcome to the job data website</p>
            <div className="flex flex-row gap-12">
                <div className="bg-blue-100 text-3xl p-12">
                    <a href="/stats">View our data</a>
                </div>
                <div className="bg-blue-100 text-3xl p-12">
                    <a href="/submit">Give us data</a>
                </div>
            </div>
            
        </span>
    );
}
