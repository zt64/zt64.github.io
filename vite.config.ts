import { defineConfig } from "vite";
import devtoolsJson from "vite-plugin-devtools-json";
import tailwindcss from '@tailwindcss/vite'
import { viteSingleFile } from "vite-plugin-singlefile";

export default defineConfig({
    plugins: [
        devtoolsJson(),
        tailwindcss(),
        viteSingleFile(),
    ],
});
