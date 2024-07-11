function peterContact() {
    return (
        <a href="https://github.com/pecasdev" target="_blank">
            peter
        </a>
    );
}

function danielContact() {
    return (
        <a href="https://github.com/danielperev" target="_blank">
            daniel
        </a>
    );
}

export function Footer() {
    return (
        <footer className="min-h-100%">
            <span className="absolute bottom-0">
                <p className="h4">
                    give us jobs: {peterContact()}, {danielContact()}
                </p>
            </span>
        </footer>
    );
}
