import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import viteTsconfigPaths from 'vite-tsconfig-paths'

export default defineConfig(() => {
  return {
    build: {
      outDir: 'build',
    },
    server: {
        host: "127.0.0.1",
        port: 3000,
    },
    plugins: [react(), viteTsconfigPaths()],
  };
});
