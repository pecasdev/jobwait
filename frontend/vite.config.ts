import { defineConfig, loadEnv } from "vite";
import react from "@vitejs/plugin-react";
import viteTsConfigPaths from "vite-tsconfig-paths";

export default defineConfig(({ mode }) => {
    const env = loadEnv(mode, process.cwd(), "");
    return {
        define: {
            "process.env": env,
        },
        build: {
            outDir: "build",
        },
        server: {
            host: "127.0.0.1",
            port: 3000,
        },
        plugins: [react(), viteTsConfigPaths()],
    };
});
